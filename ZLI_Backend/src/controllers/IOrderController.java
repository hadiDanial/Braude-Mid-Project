package controllers;

import java.util.ArrayList;
import java.util.Date;

import entities.users.Order;
import enums.OrderStatus;

public interface IOrderController
{
	/**
	 * Register a new order and save it to the database - PENDING manager approval
	 * 
	 * @param order Order to save
	 * @return true if order was saved successfully.
	 */
	boolean createNewOrder(Order order);

	/** function that moves all the orders from the database and put it in arraylist of orders
	 * @return ArrayList<Order>
	 */
	ArrayList<Order> getAllOrders();

	/** function used to get order from the database
	 * @param orderId used to get the order of this ID
	 * @return Order
	 */
	Order getOrder(int orderId);

	/**
	 * Update an order's status.
	 * 
	 * @param orderId     Id of the order.
	 * @param orderStatus New order status.
	 * @return True if update was successful.
	 */
	boolean updateOrderStatus(Integer id, OrderStatus orderStatus);

	/**function check the pending orders by their status and put it in customer's cart from this branch
	 * 
	 * @param branchId
	 * @param orderStatus different types of status
	 * @return 
	 */
	ArrayList<Order> getOrdersByStatusAndBranch(int branchId, OrderStatus orderStatus);

	ArrayList<Order> getAllUserOrders(int userId);

	/** function that gets the order by it's status type
	 * @param orderStatus 
	 * @return ArrayList<Order>
	 */
	ArrayList<Order> getOrdersByStatus(OrderStatus orderStatus);

	/** function that updates input order in the database
	 * @param orderToUpdate
	 * @return boolean
	 */
	boolean updateOrder(Order orderToUpdate);

	/**getter for the number of orders that this user made 
	 * @param userId
	 * @return int
	 */
	int getNumberOfUserOrders(int userId);

	ArrayList<Order> getOrdersByDatesAndBranch(Date startDate, Date endDate, int branchId, OrderStatus orderStatus);

}