package entities.users;

import java.time.Instant;
import java.util.ArrayList;

import entities.other.Branch;
import entities.products.Item;
import entities.products.Product;
import enums.OrderStatus;

public class Order
{
	private int orderId;
	private OrderDelivery deliveryDetails;
	private String greetingCard;
	private ArrayList<Product> products;
	private ArrayList<Item> items;
	private Instant orderDate;
	private Instant deliveryDate;
	private float totalCost;
	private OrderStatus orderStatus;
	private Branch branch;

	public Order(){	}
	
	public Order(OrderDelivery deliveryDetails, String greetingCard, ArrayList<Product> products, ArrayList<Item> items,
			Instant deliveryDate, float totalCost, OrderStatus orderStatus, Branch branch)
	{
		this.deliveryDetails = deliveryDetails;
		this.greetingCard = greetingCard;
		this.products = products;
		this.items = items;
		this.deliveryDate = deliveryDate;
		this.orderDate = Instant.now();
		this.totalCost = totalCost;
		this.orderStatus = orderStatus;
		this.branch = branch;
	}
	
	public int getOrderId()
	{
		return orderId;
	}
	public void setOrderId(int orderId)
	{
		this.orderId = orderId;
	}
	public OrderDelivery getDeliveryDetails()
	{
		return deliveryDetails;
	}
	public void setDeliveryDetails(OrderDelivery deliveryDetails)
	{
		this.deliveryDetails = deliveryDetails;
	}
	public String getGreetingCard()
	{
		return greetingCard;
	}
	public void setGreetingCard(String greetingCard)
	{
		this.greetingCard = greetingCard;
	}
	public Instant getOrderDate()
	{
		return orderDate;
	}
	public void setOrderDate(Instant orderDate)
	{
		this.orderDate = orderDate;
	}
	public Instant getDeliveryDate()
	{
		return deliveryDate;
	}
	public void setDeliveryDate(Instant deliveryDate)
	{
		this.deliveryDate = deliveryDate;
	}
	public float getTotalCost()
	{
		return totalCost;
	}
	public void setTotalCost(float totalCost)
	{
		this.totalCost = totalCost;
	}
	public OrderStatus getOrderStatus()
	{
		return orderStatus;
	}
	public void setOrderStatus(OrderStatus orderStatus)
	{
		this.orderStatus = orderStatus;
	}
	public Branch getBranch()
	{
		return branch;
	}
	public void setBranch(Branch branch)
	{
		this.branch = branch;
	}
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((branch == null) ? 0 : branch.hashCode());
		result = prime * result + ((deliveryDate == null) ? 0 : deliveryDate.hashCode());
		result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
		result = prime * result + orderId;
		return result;
	}
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (!(obj instanceof Order))
		{
			return false;
		}
		Order other = (Order) obj;
		if (branch == null)
		{
			if (other.branch != null)
			{
				return false;
			}
		} else if (!branch.equals(other.branch))
		{
			return false;
		}
		if (deliveryDate == null)
		{
			if (other.deliveryDate != null)
			{
				return false;
			}
		} else if (!deliveryDate.equals(other.deliveryDate))
		{
			return false;
		}
		if (orderDate == null)
		{
			if (other.orderDate != null)
			{
				return false;
			}
		} else if (!orderDate.equals(other.orderDate))
		{
			return false;
		}
		if (orderId != other.orderId)
		{
			return false;
		}
		return true;
	}

	
}