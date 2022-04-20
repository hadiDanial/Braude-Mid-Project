package controllers;

import client.Client;
import entities.products.*;

public class OrderController
{
	private static OrderController instance;
	private Client client;
	
	private OrderController() {
		client = Client.getInstance();
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

}