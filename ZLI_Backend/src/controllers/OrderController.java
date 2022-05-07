package controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;

import database.DatabaseConnection;
import database.IObjectToPreparedStatementParameters;
import database.IResultSetToObject;
import entities.other.Branch;
import entities.users.Order;
import enums.ColorEnum;
import enums.OrderStatus;

public class OrderController
{

	private static OrderController instance;
	private final DatabaseConnection databaseConnection;
	private static final String TABLE_NAME = "Orders";
	private static final String ID_FIELD_NAME = "orderNumber";
	private IResultSetToObject<Order> rsToOrder;
	private static final String[] allColumnNames =
	{ "price", "greetingCard", "color", "dOrder", "shop", "date", "orderDate" };

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
					order.setOrderId(rs.getInt("orderNumber"));
					order.setTotalCost(rs.getFloat("price"));
					order.setGreetingCard(rs.getString("greetingCard"));
					order.setColor(ColorEnum.valueOf(rs.getString("color")));
					order.setOrderDetails(rs.getString("dOrder"));
					order.setBranch(new Branch(rs.getString("shop")));
					order.setDeliveryDate(rs.getTimestamp("date").toInstant());
					order.setOrderDate(rs.getTimestamp("orderDate").toInstant());
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
		int res = databaseConnection.insertToDatabase(TABLE_NAME, allColumnNames, new IObjectToPreparedStatementParameters<Order>()
		{

			@Override
			public void convertObjectToPSQuery(PreparedStatement statementToPrepare) throws SQLException
			{
				// 	{ "price", "greetingCard", "color", "dOrder", "shop", "date", "orderDate" };
				statementToPrepare.setFloat(1, order.getTotalCost());
				statementToPrepare.setString(2, order.getGreetingCard());
				statementToPrepare.setString(3, order.getColor().name());
				statementToPrepare.setString(4, order.getOrderDetails());
				statementToPrepare.setString(5, order.getBranchName());
				statementToPrepare.setTimestamp(6, Timestamp.from(order.getDeliveryDate()));
				statementToPrepare.setTimestamp(7, Timestamp.from(order.getOrderDate()));
			}
		});
		return res == 1;
	}

	public ArrayList<Order> getAllOrders()
	{
		return databaseConnection.getAll(TABLE_NAME, rsToOrder);
	}

	public Order getOrder(int orderId)
	{
		return databaseConnection.getByID(orderId, TABLE_NAME, ID_FIELD_NAME, rsToOrder);
	}

	public boolean updateOrder(Order orderToUpdate)
	{
		ArrayList<String> keys = new ArrayList<String>();
		keys.add("date");
		keys.add("color");
		return databaseConnection.updateById(orderToUpdate.getOrderId(), ID_FIELD_NAME, TABLE_NAME, keys,
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
