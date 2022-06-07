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
	
	@Override
	public void setDiscounts(HashSet<Discount> discounts)
	{
		this.discounts = discounts;
	}

	@Override
	public void addDiscount(Discount discount)
	{
		if(discounts == null) discounts = new HashSet<Discount>();
		discounts.add(discount);
	}

	public int getOrderId()
	{
		return orderId;
	}

	public void setOrderId(int orderId)
	{
		this.orderId = orderId;
	}

	public Delivery getDeliveryDetails()
	{
		return deliveryDetails;
	}

	public void setDeliveryDetails(Delivery deliveryDetails)
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

	public String getFormattedOrderDate()
	{
		return DateFormatter.formatInstant(orderDate, true);
	}

	public String getFormattedDeliveryDate()
	{
		return DateFormatter.formatInstant(deliveryDate, true);
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

	public int getOrderQuantity()
	{
		return products.size();
	}

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

	public String getBranchName()
	{
		return branch.getBranchName();
	}


	public ArrayList<CartItem> getProducts()
	{
		return products;
	}

	public void setProducts(ArrayList<CartItem> products)
	{
		this.products = products;
	}
	
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
	public int getNumProducts()
	{
		int count = 0;
		for (CartItem cartItem : products)
		{
			count += cartItem.getQuantity();
		}
		return count;
	}
	public User getCustomer()
	{
		return customer;
	}

	public void setCustomer(User customer)
	{
		this.customer = customer;
	}

	public String getOrderDetails()
	{
		orderDetails = "";
		for (CartItem item : products)
		{
			orderDetails += item.toString() + " | ";
		}
		return orderDetails;
	}

	public void setOrderDetails(String orderDetails)
	{
		this.orderDetails = orderDetails;
	}
	public String getAddress()
	{
		if(deliveryDetails == null)
			return "Pickup, " + branch.getBranchName();// + " branch (" + branch.getLocation().getCity() + ")";
		else
			return deliveryDetails.getLocation().toString();
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