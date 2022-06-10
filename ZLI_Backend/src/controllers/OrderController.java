package controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import database.DatabaseConnection;
import database.IObjectToPreparedStatementParameters;
import database.Tables;
import entities.other.Branch;
import entities.other.Location;
import entities.products.BaseProduct;
import entities.products.CartItem;
import entities.products.CatalogItem;
import entities.users.Order;
import entities.users.Delivery;
import entities.users.User;
import enums.ColorEnum;
import enums.OrderStatus;

public class OrderController
{
	private static OrderController instance;
	private final UserController userController;
	private final DatabaseConnection databaseConnection;
	private final ProductController productController;
	private static final String ID_FIELD_NAME = "orderId";

	private OrderController()
	{
		databaseConnection = DatabaseConnection.getInstance();
		productController = ProductController.getInstance();
		userController = UserController.getInstance();
	}

	
	/** 
	 * @return OrderController
	 */
	public static synchronized OrderController getInstance()
	{
		if (instance == null)
		{
			instance = new OrderController();
		}
		return instance;
	}

	/**
	 * Register a new order and save it to the database - PENDING manager approval
	 * 
	 * @param order Order to save
	 * @return true if order was saved successfully.
	 */
	public boolean createNewOrder(Order order)
	{
		boolean res;
		// Save Order
		int insertedOrderId = databaseConnection.insertAndReturnGeneratedId(Tables.ORDERS_TABLE_NAME,
				Tables.ordersColumnNames, new IObjectToPreparedStatementParameters<Order>()
				{

					@Override
					public void convertObjectToPSQuery(PreparedStatement statementToPrepare) throws SQLException
					{
						// { "userId", "branchId", "orderStatus", "totalCost", "greetingCard", "color",
						// "details", "orderDate", "deliveryDate" };
						statementToPrepare.setInt(1, order.getCustomer().getUserId());
						statementToPrepare.setInt(2, order.getBranch().getBranchId());
						statementToPrepare.setString(3, OrderStatus.Pending.name());
						statementToPrepare.setFloat(4, order.getPriceAfterDiscounts());
						statementToPrepare.setString(5, order.getGreetingCard());
						statementToPrepare.setString(6, order.getOrderDetails());
						statementToPrepare.setTimestamp(7, Timestamp.from(order.getOrderDate()));
						statementToPrepare.setTimestamp(8, Timestamp.from(order.getDeliveryDate()));
					}
				});

		if (insertedOrderId == -1)
		{
			return false;
		}
		order.setOrderId(insertedOrderId);

		// Save the order details - what products were bought and in what quantities
		ArrayList<CartItem> cart = order.getProducts();
		res = databaseConnection.insertCollection(Tables.ORDERS_PRODUCTS_TABLE_NAME, Tables.productsInOrderColumnNames,
				cart.size(), new IObjectToPreparedStatementParameters<CartItem>()
				{

					@Override
					public void convertObjectToPSQuery(PreparedStatement statementToPrepare) throws SQLException
					{
						for (int i = 0; i < cart.size(); i++)
						{
							int val = i * Tables.productsInOrderColumnNames.length;
							statementToPrepare.setInt(val + 1, order.getOrderId());
							statementToPrepare.setInt(val + 2,
									cart.get(i).getCatalogItem().getBaseProduct().getProductId());
							statementToPrepare.setInt(val + 3, cart.get(i).getQuantity());
						}
					}
				});
		if (!res)
			return false;

		// Save order delivery details if they exist
		Delivery delivery = order.getDeliveryDetails();
		if (delivery != null)
		{
			res = 1 == databaseConnection.insertToDatabase(Tables.DELIVERIES_TABLE_NAME, Tables.deliveriesColumnNames,
					new IObjectToPreparedStatementParameters<Delivery>()
					{
						@Override
						public void convertObjectToPSQuery(PreparedStatement statementToPrepare) throws SQLException
						{
							// { "orderId", "recipientName", "recipientPhoneNumber", "locationId",
							// "delivered" };
							statementToPrepare.setInt(1, order.getOrderId());
							statementToPrepare.setString(2, delivery.getRecipientName());
							statementToPrepare.setString(3, delivery.getRecipientPhoneNumber());
							statementToPrepare.setInt(4, delivery.getLocation().getLocationId());
							statementToPrepare.setBoolean(5, delivery.isDelivered());
						}
					});
		}
		// Send email to customer.
		if (res)
		{
			EmailManager.sendEmail(
					"Zerli - New Order", "Your order has been registered and will be reviewed by a manager soon.<br>"
							+ "Order #" + insertedOrderId + "<br>" + "Thank you for choosing Zerli!",
					order.getCustomer());
			notifyManager(order);
		}
		return res;
	}

	
	/** function that sends mail using the email manager to send to the branch manager 
	 * @param order used to get order details
	 */
	private void notifyManager(Order order)
	{
		int branchId = order.getBranch().getBranchId();
		ArrayList<String> tables = new ArrayList<String>();
		tables.add(Tables.BRANCHES_TABLE_NAME);
		tables.add(Tables.USERS_TABLE_NAME);
		
		String selects = "branches.managerId, users.emailAddress, users.firstName";
		String conditions = "branches.branchId=" + branchId + " AND branches.managerId=users.userId";
		ResultSet rs = databaseConnection.getJoinResultsWithSelectColumns(tables, selects, conditions);
		try
		{
			if(rs.next())
			{
				
			String content = "Hello, " + rs.getString("firstName") + "!<br>"
					+ "A new order has been added to your branch, please review it soon!<br>" + "Order #"
					+ order.getOrderId();
			EmailManager.sendEmail("New Order", content, rs.getString("emailAddress"));
			}

		} catch (SQLException e)
		{
			e.printStackTrace();
		}

	}

	
	/** function that moves all the orders from the database and put it in arraylist of orders
	 * @return ArrayList<Order>
	 */
	public ArrayList<Order> getAllOrders()
	{
		return convertRSToOrderArray(databaseConnection.getAll(Tables.ORDERS_TABLE_NAME));
	}

	
	/** function used to get order from the database
	 * @param orderId used to get the order of this ID
	 * @return Order
	 */
	public Order getOrder(int orderId)
	{
		Order o = convertRSToOrder(databaseConnection.getByID(orderId, Tables.ORDERS_TABLE_NAME, ID_FIELD_NAME), true);
		User u = UserController.getInstance().getUserById(o.getCustomer().getUserId());
		o.setCustomer(u);
		return o;
	}

	/**
	 * Update an order's status.
	 * 
	 * @param orderId     Id of the order.
	 * @param orderStatus New order status.
	 * @return True if update was successful.
	 */
	public boolean updateOrderStatus(Integer id, OrderStatus orderStatus)
	{
		Order order = getOrder(id);
		boolean res = databaseConnection.updateById(id, ID_FIELD_NAME, Tables.ORDERS_TABLE_NAME,
				Tables.ordersColumnNames[2], "'"+orderStatus.name()+"'");
		if (res)
		{
			EmailManager.sendEmail("Zerli - Order #" + id + " Status Update",
					"Your order status has been updated to:" + orderStatus.name() + "<br>"
							+ "Thank you for your patience!",
					order.getCustomer());
			if(orderStatus == OrderStatus.Delivered)
			{
				databaseConnection.updateById(id, "orderId", Tables.DELIVERIES_TABLE_NAME, "delivered", "true");
			}
			if(orderStatus == OrderStatus.Canceled)
			{
				Instant deliveryTime = order.getDeliveryDate(), now = Instant.now();
				if(now.isBefore(deliveryTime.minusSeconds(60 * 60)) && (now.isAfter(deliveryTime.minusSeconds( 60 * 60 * 3))))
				{
					 userController.updateUserCredit(order.getCustomer(), order.getTotalCost() / 2);
				}
				else if(now.isBefore(deliveryTime.minusSeconds( 60 * 60 * 3)))
				{
					userController.updateUserCredit(order.getCustomer(), getOrder(order.getOrderId()).getTotalCost());					
				}
			}
		}
		return res;
	}

	/**function check the pending orders by their status and put it in customer's cart from this branch
	 * 
	 * @param branchId
	 * @param orderStatus different types of status
	 * @return 
	 */
	public ArrayList<Order> getOrdersByStatusAndBranch(int branchId, OrderStatus orderStatus)
	{
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("branchId", branchId + "");
		map.put("orderStatus", orderStatus.name());
		ResultSet ordersRS = databaseConnection.getByConditions(Tables.ORDERS_TABLE_NAME, map);
		ArrayList<Order> pendingOrders = convertRSToOrderArray(ordersRS);
		for (Order order : pendingOrders)
		{
			ArrayList<CartItem> cart;
			ArrayList<String> tableNames = new ArrayList<String>();

			// Get CartItems
			tableNames.add(Tables.ORDERS_TABLE_NAME);
			tableNames.add(Tables.ORDERS_PRODUCTS_TABLE_NAME);
			tableNames.add(Tables.ALL_PRODUCTS_TABLE_NAME);
			String selects = "Orders.orderId, orders_products.quantity, catalog.* ";
			String conditions = " Orders.branchId = " + branchId + " AND Orders.orderId=" + order.getOrderId()
					+ " AND Orders.orderId= orders_products.orderId AND orders.orderStatus='" + orderStatus.name() + "'"
					+ " AND catalog.catalogId = orders_products.catalogId";
			// Join Order_Product + CatalogItem
			ResultSet cartRS = databaseConnection.getJoinResultsWithSelectColumns(tableNames, selects, conditions);
			cart = convertRSToCart(cartRS);

			// Get Delivery details
			tableNames.clear();
			tableNames.add(Tables.DELIVERIES_TABLE_NAME);
			tableNames.add(Tables.LOCATIONS_TABLE_NAME);
			tableNames.add(Tables.BRANCHES_TABLE_NAME);
			selects = "deliveries.*, locations.city, locations.zipCode, locations.street, "
					+ "locations.notes, branches.branchId, branches.managerId, branches.branchName";
			conditions = "Deliveries.orderId=" + order.getOrderId()
					+ " AND deliveries.locationId = locations.locationId AND locations.locationId = branches.branchId";
			ResultSet deliveryRS = databaseConnection.getJoinResultsWithSelectColumns(tableNames, selects, conditions);
			try
			{
				if (deliveryRS.next())
				{

					Delivery delivery = convertRSToOrderDelivery(deliveryRS);
					Branch branch = convertRSToBranch(deliveryRS);
					if (branch != null)
					{
						order.setBranch(branch);
					}
					if (delivery != null)
					{
						delivery.setOrderWithDelivery(order);
						order.setDeliveryDetails(delivery);
					}
				}
				deliveryRS.close();
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
			order.setProducts(cart);
		}
		return pendingOrders;
	}

	public ArrayList<Order> getAllUserOrders(int userId)
	{
		ResultSet rs = databaseConnection.getBySimpleCondition("userId", String.valueOf(userId), Tables.ORDERS_TABLE_NAME);
		return convertRSToOrderArray(rs);
	}
	/** function that gets the order by it's status type
	 * @param orderStatus 
	 * @return ArrayList<Order>
	 */
	public ArrayList<Order> getOrdersByStatus(OrderStatus orderStatus)
	{
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("orderStatus", orderStatus.name());
		ResultSet ordersRS = databaseConnection.getByConditions(Tables.ORDERS_TABLE_NAME, map);
		ArrayList<Order> pendingOrders = convertRSToOrderArray(ordersRS);
		return pendingOrders;
	}

	
	/** function that takes details of branch table and move it to branch object
	 * @param deliveryRS
	 * @return Branch
	 */
	private Branch convertRSToBranch(ResultSet deliveryRS)
	{
		Branch branch = new Branch();
		try
		{
			branch.setBranchId(deliveryRS.getInt("branchId"));
			branch.setBranchName(deliveryRS.getString("branchName"));
			return branch;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	
	/** function that moves all the orders from the database and put it in arraylist of orders
	 * @param resultSet
	 * @return ArrayList<Order>
	 */
	public ArrayList<Order> convertRSToOrderArray(ResultSet resultSet)
	{
		ArrayList<Order> orders = new ArrayList<Order>();
		try
		{
			while (resultSet.next())
			{
				orders.add(convertRSToOrder(resultSet, false));
			}
			resultSet.close();
			return orders;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}


	
	/** function that initializes order details from database and put it in input
	 * @param resultSet
	 * @param isOnlyRecordExpected
	 * @return Order
	 */
	public Order convertRSToOrder(ResultSet resultSet, boolean isOnlyRecordExpected)
	{
		String[] ordersColumnNames = Tables.ordersColumnNames;
		try
		{
			if (isOnlyRecordExpected)
			{
				if (!resultSet.next())
					return null;
			}
			Order order = new Order();
			order.setOrderId(resultSet.getInt(ID_FIELD_NAME));
			User customer = new User();
			customer.setUserId(resultSet.getInt(ordersColumnNames[0]));
			order.setCustomer(customer);
			Branch branch = new Branch();
			branch.setBranchId(resultSet.getInt(ordersColumnNames[1]));
			order.setBranch(branch);
			order.setOrderStatus(OrderStatus.valueOf(resultSet.getString(ordersColumnNames[2])));
			order.setTotalCost(resultSet.getFloat(ordersColumnNames[3]));
			order.setGreetingCard(resultSet.getString(ordersColumnNames[4]));
			order.setOrderDetails(resultSet.getString(ordersColumnNames[5]));
			order.setOrderDate(resultSet.getTimestamp(ordersColumnNames[6]).toInstant());
			order.setDeliveryDate(resultSet.getTimestamp(ordersColumnNames[7]).toInstant());
			if (isOnlyRecordExpected)
				resultSet.close();
			return order;
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	
	/** function that initializes delivery details of the order from database and put it in input
	 * @param resultSet
	 * @return Delivery
	 */
	public Delivery convertRSToOrderDelivery(ResultSet resultSet)
	{
		try
		{
			String[] deliveriesColumnNames = Tables.deliveriesColumnNames;
			Delivery delivery = new Delivery();
			delivery.setDelivered(false);
			delivery.setRecipientName(resultSet.getString(deliveriesColumnNames[1]));
			delivery.setRecipientPhoneNumber(resultSet.getString(deliveriesColumnNames[2]));
			Location loc = LocationController.convertRSToLocation(resultSet, false, false);
			delivery.setLocation(loc);
			return delivery;
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**function that moves all the cartItem from the database and put it in arraylist of cartItem
	 * @param resultSet
	 * @param deliveriesColumnNames
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<CartItem> convertRSToCart(ResultSet cartRS)
	{
		ArrayList<CartItem> cart = new ArrayList<CartItem>();
		try
		{
			while (cartRS.next())
			{
				BaseProduct product = productController.convertRSToBaseProduct(cartRS, false);
				CartItem cartItem = new CartItem();
				CatalogItem catalogItem = new CatalogItem();
				catalogItem.setBaseProduct(product);
				cartItem.setCatalogItem(catalogItem);
				cartItem.setQuantity(cartRS.getInt("quantity"));
				cart.add(cartItem);
			}
			cartRS.close();
			return cart;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	
	/** function that updates input order in the database
	 * @param orderToUpdate
	 * @return boolean
	 */
	public boolean updateOrder(Order orderToUpdate)
	{
		ArrayList<String> keys = new ArrayList<String>();
		keys.add("date");
		return databaseConnection.updateById(orderToUpdate.getOrderId(), ID_FIELD_NAME, Tables.ORDERS_TABLE_NAME, keys,
				new IObjectToPreparedStatementParameters<Order>()
				{

					@Override
					public void convertObjectToPSQuery(PreparedStatement statementToPrepare) throws SQLException
					{
						statementToPrepare.setTimestamp(1, Timestamp.from(orderToUpdate.getDeliveryDate()));
					}
				});
	}
	
	/**getter for the number of orders that this user made 
	 * @param userId
	 * @return int
	 */
	public int getNumberOfUserOrders(int userId)
	{
		int count = 0;
		ResultSet rs = databaseConnection.getBySimpleCondition("userId", String.valueOf(userId), Tables.ORDERS_TABLE_NAME);
		try
		{
			while(rs.next())
			{
				count++;
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return count;
	}


	public ArrayList<Order> getOrdersByDatesAndBranch(Date startDate, Date endDate, int branchId, OrderStatus orderStatus)
	{
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("branchId", String.valueOf(branchId));
		map.put("orderStatus", orderStatus.name());
		ResultSet rs = databaseConnection.getByConditionsBetweenDates(Tables.ORDERS_TABLE_NAME, map, "orderDate", startDate, endDate);
		return convertRSToOrderArray(rs);
	}



}
