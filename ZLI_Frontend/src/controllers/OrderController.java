package controllers;

import java.time.Instant;
import java.util.ArrayList;

import entities.other.Branch;
import entities.products.*;
import entities.users.Order;
import enums.ColorEnum;
import enums.OrderStatus;
import requests.Request;
import requests.RequestType;
import requests.UpdateOrderRequest;
import utility.IResponse;

public class OrderController
{
	private static OrderController instance;
	private ClientController clientController;
	
	private OrderController() {
		clientController = ClientController.getInstance();
	}
	
	public static OrderController getInstance()
	{
		if(instance == null)
		{
			instance = new OrderController();
		}
		return instance;
	}
	/**
	 * 
	 * @param product
	 */
	public boolean addProductToOrder(Product product)
	{
		// TODO - implement OrderController.addProductToOrder
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param item
	 */
	public boolean addItemToOrder(Item item)
	{
		// TODO - implement OrderController.addItemToOrder
		throw new UnsupportedOperationException();
	}

	public void addOrderDetails()
	{
		// TODO - implement OrderController.addOrderDetails
		throw new UnsupportedOperationException();
	}

	public void sendOrderToServer()
	{
		// TODO - implement OrderController.sendOrderToServer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param baseProduct
	 */
	public boolean isProduct(BaseProduct baseProduct)
	{
		// TODO - implement OrderController.isProduct
		throw new UnsupportedOperationException();
	}

	public void getAllOrders(IResponse<ArrayList<Order>> response)
	{
		Request request = new Request(RequestType.GetAllOrders, null);
		clientController.sendRequest(request, response);
	}
	
	public void updateOrder(int orderId, Order updatedOrder, IResponse<Boolean> response)
	{
		UpdateOrderRequest updateOrderRequest = new UpdateOrderRequest(orderId, updatedOrder);
		Request request = new Request(RequestType.UpdateOrder, updateOrderRequest);
		clientController.sendRequest(request, response);
	}

}