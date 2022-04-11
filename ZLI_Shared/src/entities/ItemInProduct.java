package entities;

public class ItemInProduct
{
	private Product product;
	private Item item;
	private int itemQuantityInProduct;
	
	public ItemInProduct(Product product, Item item, int itemQuantityInProduct)
	{
		super();
		this.product = product;
		this.item = item;
		this.itemQuantityInProduct = itemQuantityInProduct;
	}

	public Product getProduct()
	{
		return product;
	}

	public void setProduct(Product product)
	{
		this.product = product;
	}

	public Item getItem()
	{
		return item;
	}

	public void setItem(Item item)
	{
		this.item = item;
	}

	public int getItemQuantityInProduct()
	{
		return itemQuantityInProduct;
	}

	public void setItemQuantityInProduct(int itemQuantityInProduct)
	{
		this.itemQuantityInProduct = itemQuantityInProduct;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result + itemQuantityInProduct;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (!(obj instanceof ItemInProduct))
		{
			return false;
		}
		ItemInProduct other = (ItemInProduct) obj;
		if (item == null)
		{
			if (other.item != null)
			{
				return false;
			}
		} else if (!item.equals(other.item))
		{
			return false;
		}
		if (itemQuantityInProduct != other.itemQuantityInProduct)
		{
			return false;
		}
		if (product == null)
		{
			if (other.product != null)
			{
				return false;
			}
		} else if (!product.equals(other.product))
		{
			return false;
		}
		return true;
	}
	
	

}