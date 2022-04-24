package controllers;

import java.sql.ResultSet;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;

import database.DatabaseConnection;
import database.IResultSetToObject;
import entities.other.Branch;
import entities.users.Order;
import enums.ColorEnum;
import enums.OrderStatus;
import requests.UpdateOrderRequest;

public class OrderController
{

	private static OrderController instance;
	private final DatabaseConnection databaseConnection;
	private static final String TABLE_NAME = "Orders";
	private static final String ID_FIELD_NAME = "orderNumber";
	private IResultSetToObject<Order> rsToOrder;

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

	public static Order createNewOrderFromDBArrStr(HashMap<String, String> orderData)
	{
		Order order = new Order(Integer.parseInt(orderData.get("orderNumber")), orderData.get("greetingCard"),
				Instant.parse(orderData.get("date")), Float.parseFloat(orderData.get("price")), OrderStatus.Confirmed,
				new Branch(orderData.get("shop")), ColorEnum.valueOf(orderData.get("color")), orderData.get("dOrder"),
				Instant.parse(orderData.get("orderDate")));
		return order;
	}

	public boolean createNewOrder(Order order)
	{
		ArrayList<String> data = new ArrayList<String>();
		data.add("" + order.getTotalCost());
		data.add(order.getGreetingCard());
		data.add(order.getColor().toString());
		data.add(order.getOrderDetails());
		data.add(order.getBranchName());
		data.add(order.getDeliveryDate().toString());
		data.add(order.getOrderDate().toString());
		int res = databaseConnection.insertToDatabase(data, TABLE_NAME);
		return res == 1;
	}

	public ArrayList<Order> getAllOrders()
	{
		return databaseConnection.getAllFromDB(TABLE_NAME, rsToOrder);
	}
	
	public Order getOrder(int orderId)
	{
		return databaseConnection.getByID(orderId, TABLE_NAME, ID_FIELD_NAME, rsToOrder);
	}

	public boolean updateOrder(UpdateOrderRequest req)
	{
		return false;
	}

}
