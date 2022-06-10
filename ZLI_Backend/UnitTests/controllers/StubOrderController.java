package controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

import entities.users.Order;
import enums.OrderStatus;

class StubOrderController implements IOrderController{

	ArrayList<Order> orders = new ArrayList<Order>();
	@Override
	public boolean createNewOrder(Order order)
	{
		return orders.add(order);
	}

	@Override
	public ArrayList<Order> getAllOrders()
	{
		return orders;
	}

	@Override
	public Order getOrder(int orderId)
	{
		return null;
	}

	@Override
	public boolean updateOrderStatus(Integer id, OrderStatus orderStatus)
	{
		return false;
	}

	@Override
	public ArrayList<Order> getOrdersByStatusAndBranch(int branchId, OrderStatus orderStatus)
	{
		return null;
	}

	@Override
	public ArrayList<Order> getAllUserOrders(int userId)
	{
		return null;
	}

	@Override
	public ArrayList<Order> getOrdersByStatus(OrderStatus orderStatus)
	{
		return null;
	}

	@Override
	public boolean updateOrder(Order orderToUpdate)
	{
		return false;
	}

	@Override
	public int getNumberOfUserOrders(int userId)
	{
		return 0;
	}

	@Override
	public ArrayList<Order> getOrdersByDatesAndBranch(Date startDate, Date endDate, int branchId,
			OrderStatus orderStatus)
	{
		ArrayList<Order> ordersByDate = new ArrayList<Order>();
		for (Order order : orders)
		{
//			if(order.getBranch().getBranchId() == branchId)
//			{
//				if(startDate.toInstant().isAfter(order.getOrderDate()) && endDate.toInstant().isBefore(order.getOrderDate()))
//				{
					ordersByDate.add(order);
//				}
//			}
		}
		ordersByDate.sort(new Comparator<Order>()
		{

			@Override
			public int compare(Order o1, Order o2)
			{
				return o1.getOrderDate().compareTo(o2.getOrderDate());
			}
		});
		return ordersByDate;
	}
	
}