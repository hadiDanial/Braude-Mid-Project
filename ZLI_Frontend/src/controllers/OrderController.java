package controllers;

import java.time.Instant;
import java.util.ArrayList;

import entities.other.Branch;
import entities.products.*;
import entities.users.Order;
import enums.Color;
import enums.OrderStatus;
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
		// TODO - Change from dummy data to server data
		ArrayList<Order> orders = new ArrayList<Order>();
		orders.add(new Order(1, "Hello!", Instant.now().plusSeconds(60000),100, OrderStatus.Accepted, new Branch(null, null, "Haifa", null), Color.Blue, "5 roses"));
		orders.add(new Order(2, "goodbye", Instant.now().plusSeconds(600000),1200, OrderStatus.Pending, new Branch(null, null, "Karmiel", null), Color.Bridal, "2 bridal bouquets"));
		response.executeAfterResponse(orders);
		Request request = new Request(RequestType.GetAllOrders, "Hello World!");
		clientController.sendRequest(request, response);
	}

}