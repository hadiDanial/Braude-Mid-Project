package entities.products;

import java.io.Serializable;

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
	
	public CatalogItem getCatalogItem()
	{
		return catalogItem;
	}
	
	public void setCatalogItem(CatalogItem catalogItem)
	{
		this.catalogItem = catalogItem;
	}
	
	public int getQuantity()
	{
		return quantity;
	}
	
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	public Order getOrder()
	{
		return order;
	}

	public void setOrder(Order order)
	{
		this.order = order;
	}
}
