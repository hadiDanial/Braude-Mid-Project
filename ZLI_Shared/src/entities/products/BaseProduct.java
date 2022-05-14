package entities.products;

import java.io.Serializable;
import java.util.Arrays;


public abstract class BaseProduct implements Serializable
{

	private int productId;
	private String productName;
	private float price;
	private byte[] image;

	private static final String NEW_PRODUCT_NAME = "New Product";
	private static final long serialVersionUID = 5869287229470090229L;
	
	public static final String PRODUCT_DISCRIMINATOR = "P";
	public static final String ITEM_DISCRIMINATOR = "I";
	
	protected final String type;
	
	public BaseProduct(String productName, float price, byte[] image, String type)
	{
		super();
		this.productName = productName;
		this.price = price;
		this.image = image;
		this.type = type;
	}
	
	public BaseProduct(String type)
	{
		super();
		productName = NEW_PRODUCT_NAME;
		this.type = type;
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
	
	public float getPrice()
	{
		return price;
	}
	
	public void setPrice(float price)
	{
		this.price = price;
	}

	public byte[] getImage()
	{
		return image;
	}
	
	public void setImage(byte[] image)
	{
		this.image = image;
	}
	
	public boolean isProduct()
	{
		return type.equals(PRODUCT_DISCRIMINATOR);
	}
	
	public boolean isItem()
	{
		return type.equals(ITEM_DISCRIMINATOR);
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
		return (isProduct() ? "Product" : "Item") + " [productId=" + productId + ", productName=" + productName + ", price="
				+ price + ", image=" + Arrays.toString(image) + "]";
	}

	
}