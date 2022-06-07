package entities.users;

import java.io.Serializable;
import java.time.Instant;

import entities.other.Location;
import utility.DateFormatter;

public class Delivery implements Serializable
{
	public static final float deliveryPrice = 35;

	private Order orderWithDelivery;
	private String recipientName;
	private String recipientPhoneNumber;
	private Location location;
	private boolean delivered;

	private static final long serialVersionUID = -7895325901158480997L;
	
	public Delivery()
	{
		super();
	}

	public Delivery(Order orderWithDelivery, String recipientName, String recipientPhoneNumber, Location location)
	{
		super();
		this.orderWithDelivery = orderWithDelivery;
		this.recipientName = recipientName;
		this.recipientPhoneNumber = recipientPhoneNumber;
		this.location = location;
		delivered = false;
	}
	
	
	/** 
	 * @return Order
	 */
	public Order getOrderWithDelivery()
	{
		return orderWithDelivery;
	}
	
	/** 
	 * @param orderWithDelivery
	 */
	public void setOrderWithDelivery(Order orderWithDelivery)
	{
		this.orderWithDelivery = orderWithDelivery;
	}
	
	
	/** 
	 * @return Location
	 */
	public Location getLocation()
	{
		return location;
	}

	
	/** 
	 * @param location
	 */
	public void setLocation(Location location)
	{
		this.location = location;
	}

	
	/** 
	 * @return String
	 */
	public String getRecipientName()
	{
		return recipientName;
	}
	
	/** 
	 * @param recipientName
	 */
	public void setRecipientName(String recipientName)
	{
		this.recipientName = recipientName;
	}
	
	/** 
	 * @return String
	 */
	public String getRecipientPhoneNumber()
	{
		return recipientPhoneNumber;
	}
	
	/** 
	 * @param recipientPhoneNumber
	 */
	public void setRecipientPhoneNumber(String recipientPhoneNumber)
	{
		this.recipientPhoneNumber = recipientPhoneNumber;
	}
	
	/** 
	 * @return boolean
	 */
	public boolean isDelivered()
	{
		return delivered;
	}
	
	/** 
	 * @param delivered
	 */
	public void setDelivered(boolean delivered)
	{
		this.delivered = delivered;
	}
	
	/** 
	 * @return int
	 */
	public int getId()
	{
		return orderWithDelivery.getOrderId();
	}
	
	/** 
	 * @return int
	 */
	public int getItemsCount()
	{
		return orderWithDelivery.getProducts().size();
	}
	
	/** 
	 * @return float
	 */
	public float getPrice()
	{
		return orderWithDelivery.getPriceAfterDiscounts();
	}
	
	/** 
	 * @return String
	 */
	public String getFormattedDeliveryDate()
	{
		return DateFormatter.formatInstant(orderWithDelivery.getDeliveryDate(), true);
	}

	
	
	/** 
	 * @return int
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderWithDelivery == null) ? 0 : orderWithDelivery.hashCode());
		result = prime * result + ((recipientName == null) ? 0 : recipientName.hashCode());
		return result;
	}
	
	
	/** 
	 * @param obj
	 * @return boolean
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (!(obj instanceof Delivery))
		{
			return false;
		}
		Delivery other = (Delivery) obj;
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