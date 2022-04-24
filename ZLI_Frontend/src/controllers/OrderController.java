package controllers;

import java.util.ArrayList;

import entities.products.*;
import entities.users.Order;
import requests.Request;
import requests.RequestType;
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
	

	public void getAllOrders(IResponse<ArrayList<Order>> response)
	{
		Request request = new Request(RequestType.GetAllOrders, null);
		clientController.sendRequest(request, response);
	}
	
	public void updateOrder(Order updatedOrder, IResponse<Boolean> response)
	{
		Request request = new Request(RequestType.UpdateOrder, updatedOrder);
		clientController.sendRequest(request, response);
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
}