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

	
	/** 
	 * @return OrderController
	 */
	public static synchronized OrderController getInstance()
	{
		if (instance == null)
		{
			instance = new OrderController();
		}
		return instance;
	}

	
	/** 
	 * @param response
	 */
	public void getAllOrders(IResponse<ArrayList<Order>> response)
	{
		Request request = new Request(RequestType.GET_ALL_ORDERS, null, userController.getLoggedInUser());
		clientController.sendRequest(request, response);
	}

	
	/** 
	 * @param response
	 * @param updatedOrder
	 */
	public void updateOrder(IResponse<Boolean> response, Order updatedOrder)
	{
		Request request = new Request(RequestType.UPDATE_ORDER, updatedOrder, userController.getLoggedInUser());
		clientController.sendRequest(request, response);
	}

	
	/** 
	 * @param catalogItem
	 * @param quantity
	 */
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

	
	/** 
	 * @param response
	 */
	public void sendOrderToServer(IResponse<Boolean> response)
	{
		if (order == null)
			response.executeAfterResponse(false);
		order.setCustomer(userController.getLoggedInUser());
		order.setOrderDate(Instant.now());
		Request req = new Request(RequestType.CREATE_ORDER, order, order.getCustomer());
		clientController.sendRequest(req, response);
	}

	
	/** 
	 * @param response
	 * @param order
	 */
	public void orderIsDelivered(IResponse<Boolean> response,Order order)
	{
		order.setOrderStatus(OrderStatus.Delivered);
		Request request = new Request(RequestType.UPDATE_ORDER_STATUS, order);
		clientController.sendRequest(request, response);
	}
	
	
	/** 
	 * @param product
	 */
	public void addProductToCart(CatalogItem product)
	{
		CartItem item = new CartItem();
		item.setCatalogItem(product);
		item.setQuantity(1);
		item.setOrder(order);
		order.addProduct(item);
		System.out.println("Added product to order. Cart:\n" + order.getProducts());
	}

	
	/** 
	 * @param response
	 * @param BranchId
	 */
	public void getDeliveryByBranch(IResponse<Order> response, int BranchId)
    {
        EntityRequestWithId<OrderStatus> request = new EntityRequestWithId<OrderStatus>();
        request.setEntityId(BranchId);
        request.setEntity(OrderStatus.ToBeDelivered);
		Request requests = new Request(RequestType.GET_ALL_DELIVERY_BRANCH, request);
        clientController.sendRequest(requests, response);
    }
	
	
	/** 
	 * @param iResponse
	 * @param orderStatus
	 */
	public void getAllOrdersByStatus(IResponse<ArrayList<Order>> iResponse, OrderStatus orderStatus)
    {
        Request request = new Request(RequestType.GET_ALL_ORDER_STATUS,orderStatus);
        clientController.sendRequest(request, iResponse);
    }
	public void getAllUserOrders(IResponse<ArrayList<Order>> iResponse, int userId)
	{
		Request request = new Request(RequestType.GET_ALL_USER_ORDERS, userId);
        clientController.sendRequest(request, iResponse);
	}
	/** 
	 * @param iResponse
	 * @param orderStatus
	 * @param branchId
	 */
	public void getAllOrdersByStatusAndBranch(IResponse<ArrayList<Order>> iResponse, OrderStatus orderStatus, int branchId)
	{
		  EntityRequestWithId<OrderStatus> e = new EntityRequestWithId<OrderStatus>();
	        e.setEntityId(branchId);
	        e.setEntity(orderStatus);
		Request request = new Request(RequestType.GET_ALL_ORDER_STATUS_AND_BRANCH,e);
		clientController.sendRequest(request, iResponse);
	}

	
	/** 
	 * @param response
	 * @param orderId
	 * @param orderStatus
	 */
	public void updateOrderStatus(IResponse<Boolean> response, int orderId, OrderStatus orderStatus)
	{
        EntityRequestWithId<OrderStatus> request = new EntityRequestWithId<OrderStatus>();
        request.setEntityId(orderId);
        request.setEntity(orderStatus);
		Request requests = new Request(RequestType.UPDATES_ORDER_STATUS, request);
        clientController.sendRequest(requests, response);
	}

	
	/** 
	 * @return Order
	 */
	public Order getOrder()
	{
		return order;
	}
	
	public void reset()
	{
		order = new Order();
	}


	
}