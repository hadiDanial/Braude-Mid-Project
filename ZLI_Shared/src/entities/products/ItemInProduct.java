package entities.products;

import java.io.Serializable;

public class ItemInProduct implements Serializable
{
	private Product product;
	private BaseProduct item;
	private int itemQuantityInProduct;
	
	private static final long serialVersionUID = 2077203177267906933L;

	public ItemInProduct(Product product, BaseProduct item, int itemQuantityInProduct)
	{
		super();
		this.product = product;
		this.item = item;
		this.itemQuantityInProduct = itemQuantityInProduct;
	}

	
	/** 
	 * @return Product
	 */
	public Product getProduct()
	{
		return product;
	}

	
	/** 
	 * @param product
	 */
	public void setProduct(Product product)
	{
		this.product = product;
	}

	
	/** 
	 * @return BaseProduct
	 */
	public BaseProduct getItem()
	{
		return item;
	}

	
	/** 
	 * @param item
	 */
	public void setItem(BaseProduct item)
	{
		this.item = item;
	}

	
	/** 
	 * @return int
	 */
	public int getItemQuantityInProduct()
	{
		return itemQuantityInProduct;
	}

	
	/** 
	 * @param itemQuantityInProduct
	 */
	public void setItemQuantityInProduct(int itemQuantityInProduct)
	{
		this.itemQuantityInProduct = itemQuantityInProduct;
	}

	
	/** 
	 * @return int
	 */
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