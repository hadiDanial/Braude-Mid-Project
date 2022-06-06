package controllers;

import java.time.Instant;
import java.util.ArrayList;

import entities.discounts.Discount;
import entities.products.*;
import entities.users.Order;
import enums.OrderStatus;
import requests.EntityRequestWithId;
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
		order = new Order();
		UserController.getInstance().checkIfFirstOrder(new IResponse<Boolean>()
		{
			
			@Override
			public void executeAfterResponse(Object message)
			{
				if((Boolean) message)
					order.addDiscount(Discount.firstPurchaseDiscount);
			}
		});
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
		Request request = new Request(RequestType.GET_ALL_ORDERS, null, userController.getLoggedInUser());
		clientController.sendRequest(request, response);
	}

	public void updateOrder(IResponse<Boolean> response, Order updatedOrder)
	{
		Request request = new Request(RequestType.UPDATE_ORDER, updatedOrder, userController.getLoggedInUser());
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
		order.setOrderDate(Instant.now());
		Request req = new Request(RequestType.CREATE_ORDER, order, order.getCustomer());
		clientController.sendRequest(req, response);
	}

	public void orderIsDelivered(IResponse<Boolean> response,Order order)
	{
		order.setOrderStatus(OrderStatus.Delivered);
		Request request = new Request(RequestType.UPDATE_ORDER_STATUS, order);
		clientController.sendRequest(request, response);
	}
	
	public void addProductToCart(CatalogItem product)
	{
		CartItem item = new CartItem();
		item.setCatalogItem(product);
		item.setQuantity(1);
		item.setOrder(order);
		order.addProduct(item);
		System.out.println("Added product to order. Cart:\n" + order.getProducts());
	}

	public void getDeliveryByBranch(IResponse<Order> response, int BranchId)
    {
        EntityRequestWithId<OrderStatus> request = new EntityRequestWithId<OrderStatus>();
        request.setEntityId(BranchId);
        request.setEntity(OrderStatus.ToBeDelivered);
		Request requests = new Request(RequestType.GET_ALL_DELIVERY_BRANCH, request);
        clientController.sendRequest(requests, response);
    }
	
	public void getAllOrdersByStatus(IResponse<ArrayList<Order>> iResponse, OrderStatus orderStatus)
    {
        Request request = new Request(RequestType.GET_ALL_ORDER_STATUS,orderStatus);
        clientController.sendRequest(request, iResponse);
    }

	public void updateOrderStatus(IResponse<Boolean> response, int orderId, OrderStatus orderStatus)
	{
        EntityRequestWithId<OrderStatus> request = new EntityRequestWithId<OrderStatus>();
        request.setEntityId(orderId);
        request.setEntity(orderStatus);
		Request requests = new Request(RequestType.UPDATES_ORDER_STATUS, request);
        clientController.sendRequest(requests, response);
	}

	public Order getOrder()
	{
		return order;
	}
}