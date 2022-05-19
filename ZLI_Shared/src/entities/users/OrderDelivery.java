package entities.users;

import java.io.Serializable;

import entities.other.Location;

public class OrderDelivery implements Serializable
{
	private Order orderWithDelivery;
	private String recipientName;
	private String recipientPhoneNumber;
	private Location location;
	private boolean delivered;

	private static final long serialVersionUID = -7895325901158480997L;
	
	public OrderDelivery()
	{
		super();
	}

	public OrderDelivery(Order orderWithDelivery, String recipientName, String recipientPhoneNumber, Location location)
	{
		super();
		this.orderWithDelivery = orderWithDelivery;
		this.recipientName = recipientName;
		this.recipientPhoneNumber = recipientPhoneNumber;
		this.location = location;
		delivered = false;
	}
	
	public Order getOrderWithDelivery()
	{
		return orderWithDelivery;
	}
	public void setOrderWithDelivery(Order orderWithDelivery)
	{
		this.orderWithDelivery = orderWithDelivery;
	}
	
	public Location getLocation()
	{
		return location;
	}

	public void setLocation(Location location)
	{
		this.location = location;
	}

	public String getRecipientName()
	{
		return recipientName;
	}
	public void setRecipientName(String recipientName)
	{
		this.recipientName = recipientName;
	}
	public String getRecipientPhoneNumber()
	{
		return recipientPhoneNumber;
	}
	public void setRecipientPhoneNumber(String recipientPhoneNumber)
	{
		this.recipientPhoneNumber = recipientPhoneNumber;
	}
	public boolean isDelivered()
	{
		return delivered;
	}
	public void setDelivered(boolean delivered)
	{
		this.delivered = delivered;
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderWithDelivery == null) ? 0 : orderWithDelivery.hashCode());
		result = prime * result + ((recipientName == null) ? 0 : recipientName.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (!(obj instanceof OrderDelivery))
		{
			return false;
		}
		OrderDelivery other = (OrderDelivery) obj;
		if (orderWithDelivery == null)
		{
			if (other.orderWithDelivery != null)
			{
				return false;
			}
		} else if (!orderWithDelivery.equals(other.orderWithDelivery))
		{
			return false;
		}
		if (recipientName == null)
		{
			if (other.recipientName != null)
			{
				return false;
			}
		} else if (!recipientName.equals(other.recipientName))
		{
			return false;
		}
		return true;
	}
}