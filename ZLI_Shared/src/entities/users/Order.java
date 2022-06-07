package entities.users;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;

import entities.discounts.Discount;
import entities.discounts.IDiscountable;
import entities.other.Branch;
import entities.other.Location;
import entities.products.CartItem;
import enums.ColorEnum;
import enums.OrderStatus;
import utility.DateFormatter;

public class Order implements Serializable, IDiscountable
{
	private int orderId;
	private Delivery deliveryDetails;
	private String greetingCard;
	private ArrayList<CartItem> products;
	private Instant orderDate;
	private Instant deliveryDate;
	private float totalCost;
	private OrderStatus orderStatus;
	private Branch branch;
	private User customer;

	// Demo only
	private String orderDetails;
	private HashSet<Discount> discounts;

	private static final long serialVersionUID = -2345501089755051123L;

	public Order()
	{
		products = new ArrayList<CartItem>();
		discounts = new HashSet<Discount>();
	}
	
	public Order(int orderId, String greetingCard, OrderStatus orderStatus,
			Branch branch, String orderDetails, Instant deliveryDate, Instant orderDate)
	{
		super();
		this.orderId = orderId;
		this.greetingCard = greetingCard;
		this.deliveryDate = deliveryDate;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
		this.branch = branch;
		this.orderDetails = orderDetails;
		products = new ArrayList<CartItem>();
		discounts = new HashSet<Discount>();
	}

	
	/** 
	 * @return float
	 */
	@Override
	public float getPriceAfterDiscounts()
	{
		float finalPrice = getTotalCost();
		for (Discount discount : discounts)
		{
			finalPrice = discount.applyDiscount(finalPrice);
		}
		return finalPrice;
	}
	
	
	/** 
	 * @param discounts
	 */
	@Override
	public void setDiscounts(HashSet<Discount> discounts)
	{
		this.discounts = discounts;
	}

	
	/** 
	 * @param discount
	 */
	@Override
	public void addDiscount(Discount discount)
	{
		if(discounts == null) discounts = new HashSet<Discount>();
		discounts.add(discount);
	}

	
	/** 
	 * @return int
	 */
	public int getOrderId()
	{
		return orderId;
	}

	
	/** 
	 * @param orderId
	 */
	public void setOrderId(int orderId)
	{
		this.orderId = orderId;
	}

	
	/** 
	 * @return Delivery
	 */
	public Delivery getDeliveryDetails()
	{
		return deliveryDetails;
	}

	
	/** 
	 * @param deliveryDetails
	 */
	public void setDeliveryDetails(Delivery deliveryDetails)
	{
		this.deliveryDetails = deliveryDetails;
	}

	
	/** 
	 * @return String
	 */
	public String getGreetingCard()
	{
		return greetingCard;
	}

	
	/** 
	 * @param greetingCard
	 */
	public void setGreetingCard(String greetingCard)
	{
		this.greetingCard = greetingCard;
	}

	
	/** 
	 * @return Instant
	 */
	public Instant getOrderDate()
	{
		return orderDate;
	}

	
	/** 
	 * @return String
	 */
	public String getFormattedOrderDate()
	{
		return DateFormatter.formatInstant(orderDate, true);
	}

	
	/** 
	 * @return String
	 */
	public String getFormattedDeliveryDate()
	{
		return DateFormatter.formatInstant(deliveryDate, true);
	}

	
	/** 
	 * @param orderDate
	 */
	public void setOrderDate(Instant orderDate)
	{
		this.orderDate = orderDate;
	}

	
	/** 
	 * @return Instant
	 */
	public Instant getDeliveryDate()
	{
		return deliveryDate;
	}

	
	/** 
	 * @param deliveryDate
	 */
	public void setDeliveryDate(Instant deliveryDate)
	{
		this.deliveryDate = deliveryDate;
	}

	
	/** 
	 * @return int
	 */
	public int getOrderQuantity()
	{
		return products.size();
	}

	
	/** 
	 * @return Location
	 */
	public Location getOrderLocation()
	{
		return deliveryDetails.getLocation();
	}

	/**
	 * Calculates the total cost of the order by summing up the prices of all the products in the cart and applying their discounts.
	 */
	public float getTotalCost()
	{
		totalCost = 0;
		for (CartItem cartItem : products)
		{
			totalCost += cartItem.getCatalogItem().getPriceAfterDiscounts() * cartItem.getQuantity();
		}
		if(deliveryDetails != null)
			totalCost += Delivery.deliveryPrice;
		return totalCost;
	}

	
	/** 
	 * @param totalCost
	 */
	public void setTotalCost(float totalCost)
	{
		this.totalCost = totalCost;
	}

	
	/** 
	 * @return OrderStatus
	 */
	public OrderStatus getOrderStatus()
	{
		return orderStatus;
	}

	
	/** 
	 * @param orderStatus
	 */
	public void setOrderStatus(OrderStatus orderStatus)
	{
		this.orderStatus = orderStatus;
	}

	
	/** 
	 * @return Branch
	 */
	public Branch getBranch()
	{
		return branch;
	}

	
	/** 
	 * @param branch
	 */
	public void setBranch(Branch branch)
	{
		this.branch = branch;
	}

	
	/** 
	 * @return String
	 */
	public String getBranchName()
	{
		return branch.getBranchName();
	}


	
	/** 
	 * @return ArrayList<CartItem>
	 */
	public ArrayList<CartItem> getProducts()
	{
		return products;
	}

	
	/** 
	 * @param products
	 */
	public void setProducts(ArrayList<CartItem> products)
	{
		this.products = products;
	}
	
	
	/** 
	 * @param cartItem
	 */
	public void addProduct(CartItem cartItem)
	{
		if(products.contains(cartItem))
		{
			CartItem existingProduct = products.get(products.indexOf(cartItem)); 
			existingProduct.setQuantity(cartItem.getQuantity() + existingProduct.getQuantity());
		}
		else
		{			
			this.products.add(cartItem);
		}
	}

	
	/** 
	 * @param cartItem
	 * @param i
	 */
	public void removeProduct(CartItem cartItem, int i)
	{
		if(products.contains(cartItem))
		{
			int quantity = cartItem.getQuantity() - i;
			cartItem.setQuantity(quantity);
			if(quantity <= 0)
				products.remove(cartItem);

		}
	}
	
	/** 
	 * @return int
	 */
	public int getNumProducts()
	{
		int count = 0;
		for (CartItem cartItem : products)
		{
			count += cartItem.getQuantity();
		}
		return count;
	}
	
	/** 
	 * @return User
	 */
	public User getCustomer()
	{
		return customer;
	}

	
	/** 
	 * @param customer
	 */
	public void setCustomer(User customer)
	{
		this.customer = customer;
	}

	
	/** 
	 * @return String
	 */
	public String getOrderDetails()
	{
		orderDetails = "";
		for (CartItem item : products)
		{
			orderDetails += item.toString() + " | ";
		}
		return orderDetails;
	}

	
	/** 
	 * @param orderDetails
	 */
	public void setOrderDetails(String orderDetails)
	{
		this.orderDetails = orderDetails;
	}
	
	/** 
	 * @return String
	 */
	public String getAddress()
	{
		if(deliveryDetails == null)
			return "Pickup, " + branch.getBranchName();// + " branch (" + branch.getLocation().getCity() + ")";
		else
			return deliveryDetails.getLocation().toString();
	}
	
	/** 
	 * @return int
	 */
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

	
	/** 
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "Order #" + orderId + ", deliveryDetails=" + deliveryDetails + ", greetingCard=" + greetingCard
				+ ", products=" + products + ", orderDate="
				+ DateFormatter.formatInstant(orderDate, true) + ", deliveryDate="
				+ DateFormatter.formatInstant(deliveryDate, true) + ", totalCost=" + totalCost + ", orderStatus="
				+ orderStatus + ", branch=" + branch.getBranchName() + ", customer=" + customer + "]";
	}
}