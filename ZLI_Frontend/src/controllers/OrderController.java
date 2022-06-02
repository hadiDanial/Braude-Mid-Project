package controllers;

import java.util.ArrayList;

import entities.products.*;
import entities.users.Order;
import enums.OrderStatus;
import requests.Request;
import requests.RequestType;
import utility.IResponse;

public class OrderController
{
	private static OrderController instance;
	private ClientController clientController;
	private UserController userController;
	private Order order;

	private OrderController()
	{
		clientController = ClientController.getInstance();
		userController = UserController.getInstance();
	}

	public static synchronized OrderController getInstance()
	{
		if (instance == null)
		{
			instance = new OrderController();
		}
		return instance;
	}

	public void getAllOrders(IResponse<ArrayList<Order>> response)
	{
		Request request = new Request(RequestType.GetAllOrders, null, userController.getLoggedInUser());
		clientController.sendRequest(request, response);
	}

	public void updateOrder(IResponse<Boolean> response, Order updatedOrder)
	{
		Request request = new Request(RequestType.UpdateOrder, updatedOrder, userController.getLoggedInUser());
		clientController.sendRequest(request, response);
	}

	public void addCatalogItemToOrder(CatalogItem catalogItem, int quantity)
	{
		if (order == null)
			order = new Order();
		CartItem cartItem = new CartItem();
		cartItem.setCatalogItem(catalogItem);
		cartItem.setQuantity(quantity);
		order.addProduct(cartItem);
		order.setBranch(catalogItem.getBranch());
	}

	public void addOrderDetails()
	{
		// TODO - implement OrderController.addOrderDetails
		throw new UnsupportedOperationException();
	}

	public void sendOrderToServer(IResponse<Boolean> response)
	{
		if (order == null)
			response.executeAfterResponse(false);
		order.setCustomer(userController.getLoggedInUser());
		Request req = new Request(RequestType.CreateOrder, order, order.getCustomer());
		clientController.sendRequest(req, response);
	}

	public void orderIsDelivered(IResponse<Boolean> response,Order order)
	{
		order.setOrderStatus(OrderStatus.Delivered);
		Request request = new Request(RequestType.UpdateOrderStatus, order);
		clientController.sendRequest(request, response);
	}
}