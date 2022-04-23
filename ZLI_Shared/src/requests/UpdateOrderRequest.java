package requests;

import entities.users.Order;

public class UpdateOrderRequest
{
	private int orderId;
	private Order updatedOrder;
	public UpdateOrderRequest(int orderId, Order updatedOrder)
	{
		super();
		this.orderId = orderId;
		this.updatedOrder = updatedOrder;
	}
	public int getOrderId()
	{
		return orderId;
	}
	public void setOrderId(int orderId)
	{
		this.orderId = orderId;
	}
	public Order getUpdatedOrder()
	{
		return updatedOrder;
	}
	public void setUpdatedOrder(Order updatedOrder)
	{
		this.updatedOrder = updatedOrder;
	}
	
}
