package controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;

import database.DatabaseConnection;
import database.IObjectToPreparedStatementParameters;
import database.IResultSetToObject;
import entities.other.Branch;
import entities.products.CartItem;
import entities.users.Order;
import entities.users.OrderDelivery;
import entities.users.User;
import enums.ColorEnum;
import enums.OrderStatus;

public class OrderController
{

	private static OrderController instance;
	private final DatabaseConnection databaseConnection;
	private static final String ORDERS_TABLE_NAME = "Orders";
	private static final String ORDERS_PRODUCTS_TABLE_NAME = "Orders_Products";
	private static final String ORDERS_DISCOUNTS_TABLE_NAME = "Orders_Discounts";
	private static final String DELIVERIES_TABLE_NAME = "Deliveries";
	private static final String ID_FIELD_NAME = "orderId";
	private IResultSetToObject<Order> rsToOrder;
	private static final String[] ordersColumnNames =
	{ "userId", "branchId", "orderStatus", "totalCost", "greetingCard", "color", "details", "orderDate",
			"deliveryDate" };
	private static final String[] productsInOrderColumnNames =
	{ "orderId", "catalogId", "quantity" };
	private static final String[] deliveriesrColumnNames =
	{ "orderId", "recipientName", "recipientPhoneNumber", "locationId", "delivered" };

	private OrderController()
	{
		databaseConnection = DatabaseConnection.getInstance();
		rsToOrder = new IResultSetToObject<Order>()
		{
			@Override
			public Order convertToObject(ResultSet rs)
			{
				try
				{
					Order order = new Order();
					order.setOrderId(rs.getInt(ID_FIELD_NAME));
					User customer = new User();
					customer.setUserId(rs.getInt(ordersColumnNames[0]));
					order.setCustomer(customer);
					Branch branch = new Branch();
					branch.setBranchId(rs.getInt(ordersColumnNames[1]));
					order.setBranch(branch);
					order.setOrderStatus(OrderStatus.valueOf(rs.getString(ordersColumnNames[2])));
					order.setTotalCost(rs.getFloat(ordersColumnNames[3]));
					order.setGreetingCard(rs.getString(ordersColumnNames[4]));
					order.setColor(ColorEnum.valueOf(rs.getString(ordersColumnNames[5])));
					order.setOrderDetails(rs.getString(ordersColumnNames[6]));
					order.setOrderDate(rs.getTimestamp(ordersColumnNames[7]).toInstant());
					order.setDeliveryDate(rs.getTimestamp(ordersColumnNames[8]).toInstant());
					return order;
				} catch (Exception e)
				{
					e.printStackTrace();
					return null;
				}
			}
		};
	}

	public static OrderController getInstance()
	{
		if (instance == null)
		{
			instance = new OrderController();
		}
		return instance;
	}

	public boolean createNewOrder(Order order)
	{
		boolean res;
		
		int insertedOrderId = databaseConnection.insertAndReturnGeneratedId(ORDERS_TABLE_NAME, ordersColumnNames, new IObjectToPreparedStatementParameters<Order>()
		{

			@Override
			public void convertObjectToPSQuery(PreparedStatement statementToPrepare) throws SQLException
			{
				// 	{ "userId", "branchId", "orderStatus", "totalCost", "greetingCard", "color", "details", "orderDate", "deliveryDate" };
				statementToPrepare.setInt(1, order.getCustomer().getUserId());
				statementToPrepare.setInt(2, order.getBranch().getBranchId());
				statementToPrepare.setString(3, OrderStatus.Pending.name());
				statementToPrepare.setFloat(4, order.getTotalCost());
				statementToPrepare.setString(5, order.getGreetingCard());
				statementToPrepare.setString(6, order.getColor().name());
				statementToPrepare.setString(7, order.getOrderDetails());
				statementToPrepare.setTimestamp(8, Timestamp.from(order.getOrderDate()));
				statementToPrepare.setTimestamp(9, Timestamp.from(order.getDeliveryDate()));
			}
		});
		Order insertedOrder = databaseConnection.getByID(insertedOrderId, ORDERS_TABLE_NAME, ID_FIELD_NAME, rsToOrder);
		if(insertedOrder == null)
		{
			return false;
		}
		// Save the order details - what products were bought and in what quantities
		ArrayList<CartItem> cart = order.getProducts();
		res = databaseConnection.insertCollection(ORDERS_PRODUCTS_TABLE_NAME,productsInOrderColumnNames, cart.size(), new IObjectToPreparedStatementParameters<CartItem>()
		{

			@Override
			public void convertObjectToPSQuery(PreparedStatement statementToPrepare) throws SQLException
			{
				for (int i = 0; i < cart.size(); i++)
				{
					int val = i * productsInOrderColumnNames.length;
					statementToPrepare.setInt(val + 1, insertedOrder.getOrderId());
					statementToPrepare.setInt(val + 2, cart.get(i).getCatalogItem().getBaseProduct().getProductId());
					statementToPrepare.setInt(val + 3, cart.get(i).getQuantity());					
				}
			}
		});
		if(!res) return false;
		OrderDelivery delivery = order.getDeliveryDetails(); 
		if(delivery == null)
			return true;
		res = 1 ==  databaseConnection.insertToDatabase(DELIVERIES_TABLE_NAME, deliveriesrColumnNames, new IObjectToPreparedStatementParameters<OrderDelivery>()
		{
			@Override
			public void convertObjectToPSQuery(PreparedStatement statementToPrepare) throws SQLException
			{
				// 	{ "orderId", "recipientName", "recipientPhoneNumber", "locationId", "delivered" };
				statementToPrepare.setInt(1, insertedOrder.getOrderId());
				statementToPrepare.setString(2, delivery.getRecipientName());
				statementToPrepare.setString(3, delivery.getRecipientPhoneNumber());
				statementToPrepare.setInt(4, delivery.getLocation().getLocationId());
				statementToPrepare.setBoolean(5, delivery.isDelivered());
			}
		});
		return res;
	}

	public ArrayList<Order> getAllOrders()
	{
		return databaseConnection.getAll(ORDERS_TABLE_NAME, rsToOrder);
	}

	public Order getOrder(int orderId)
	{
		return databaseConnection.getByID(orderId, ORDERS_TABLE_NAME, ID_FIELD_NAME, rsToOrder);
	}

	public boolean updateOrder(Order orderToUpdate)
	{
		ArrayList<String> keys = new ArrayList<String>();
		keys.add("date");
		keys.add("color");
		return databaseConnection.updateById(orderToUpdate.getOrderId(), ID_FIELD_NAME, ORDERS_TABLE_NAME, keys,
				new IObjectToPreparedStatementParameters<Order>()
				{

					@Override
					public void convertObjectToPSQuery(PreparedStatement statementToPrepare) throws SQLException
					{
						statementToPrepare.setTimestamp(1, Timestamp.from(orderToUpdate.getDeliveryDate()));
						statementToPrepare.setString(2, orderToUpdate.getColor().name());
					}
				});
	}

	/**
	 * 
	 */
	private void testCreateOrder()
	{
		Order o = new Order();
		o.setBranch(new Branch("TEST"));
		o.setTotalCost(100);
		o.setGreetingCard("Hello create tests");
		o.setColor(ColorEnum.Purple);
		o.setOrderDetails("This is a newly created order to test if adding works...");
		o.setDeliveryDate(Instant.now());
		o.setOrderDate(Instant.now());
		createNewOrder(o);
	}

}
