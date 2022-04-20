package entities.products;

import java.util.Arrays;

public abstract class BaseProduct
{
	private int productId;
	private String productName;
	private float originalPrice, currentPrice;
	private byte[] image;
	private int quantityInStock;
	private boolean isOnSale = false;

	private static final String NEW_PRODUCT_NAME = "New Product";
	
	public BaseProduct(String productName, float price, byte[] image, int quantityInStock)
	{
		super();
		this.productName = productName;
		this.originalPrice = price;
		this.currentPrice = originalPrice;
		this.image = image;
		this.quantityInStock = quantityInStock;
	}
	
	public BaseProduct()
	{
		super();
		quantityInStock = 0;
		productName = NEW_PRODUCT_NAME;
	}
	
	public int getProductId()
	{
		return productId;
	}
	
	public void setProductId(int productId)
	{
		this.productId = productId;
	}
	
	public String getProductName()
	{
		return productName;
	}
	
	public void setProductName(String productName)
	{
		this.productName = productName;
	}
	
	public float getOriginalPrice()
	{
		return originalPrice;
	}
	
	public void setOriginalPrice(float price)
	{
		this.originalPrice = price;
	}
	
	public float getCurrentPrice()
	{
		return currentPrice;
	}
	
	public void setCurrentPrice(float currentPrice)
	{
		this.currentPrice = currentPrice;
	}
	
	public byte[] getImage()
	{
		return image;
	}
	
	public void setImage(byte[] image)
	{
		this.image = image;
	}
	
	public int getQuantityInStock()
	{
		return quantityInStock;
	}
	
	public void setQuantityInStock(int quantityInStock)
	{
		this.quantityInStock = quantityInStock;
	}
	
	public boolean isOnSale()
	{
		return isOnSale;
	}
	
	public void setOnSale(boolean isOnSale)
	{
		this.isOnSale = isOnSale;
	}
	
	public boolean isInStock()
	{
		return quantityInStock > 0;
	}
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + productId;
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (!(obj instanceof BaseProduct))
		{
			return false;
		}
		BaseProduct other = (BaseProduct) obj;
		if (productId != other.productId)
		{
			return false;
		}
		if (productName == null)
		{
			if (other.productName != null)
			{
				return false;
			}
		} else if (!productName.equals(other.productName))
		{
			return false;
		}
		return true;
	}
	@Override
	public String toString()
	{
		return "BaseProduct [productId=" + productId + ", productName=" + productName + ", originalPrice="
				+ originalPrice + ", currentPrice=" + currentPrice + ", image=" + Arrays.toString(image)
				+ ", quantityInStock=" + quantityInStock + "]";
	}

	
}