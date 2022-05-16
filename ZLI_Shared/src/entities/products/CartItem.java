package entities.products;

import entities.users.Order;

public class CartItem
{
	private Order order;
	private CatalogItem catalogItem;
	private int quantity;
	
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
}
