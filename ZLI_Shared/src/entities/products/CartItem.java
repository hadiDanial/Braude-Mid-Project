package entities.products;

import java.io.Serializable;
import java.util.Objects;

import entities.users.Order;

public class CartItem implements Serializable
{
	private Order order;
	private CatalogItem catalogItem;
	private int quantity;
	
	private static final long serialVersionUID = -3949814939126280842L;

	public CartItem()
	{
		super();
	}

	public CartItem(CatalogItem catalogItem, int quantity)
	{
		super();
		this.catalogItem = catalogItem;
		this.quantity = quantity;
	}
	
	
	/** 
	 * @return CatalogItem
	 */
	public CatalogItem getCatalogItem()
	{
		return catalogItem;
	}
	
	
	/** 
	 * @param catalogItem
	 */
	public void setCatalogItem(CatalogItem catalogItem)
	{
		this.catalogItem = catalogItem;
	}
	
	
	/** 
	 * @return int
	 */
	public int getQuantity()
	{
		return quantity;
	}
	
	
	/** 
	 * @param quantity
	 */
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	
	/** 
	 * @return Order
	 */
	public Order getOrder()
	{
		return order;
	}

	
	/** 
	 * @param order
	 */
	public void setOrder(Order order)
	{
		this.order = order;
	}

	
	/** 
	 * @return int
	 */
	@Override
	public int hashCode()
	{
		return Objects.hash(catalogItem);
	}

	
	/** 
	 * @param obj
	 * @return boolean
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (!(obj instanceof CartItem))
			return false;
		CartItem other = (CartItem) obj;
		return Objects.equals(catalogItem, other.catalogItem);
	}

	
	/** 
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "CartItem [catalogItem=" + catalogItem + ", quantity=" + quantity + "]";
	}

	
	
}
