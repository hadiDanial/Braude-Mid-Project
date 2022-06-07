package entities.products;

import java.io.Serializable;

public abstract class BaseProduct implements Serializable
{
	private int productId;
	private String productName;
	private float price;
	private byte[] image;

	private static final String NEW_PRODUCT_NAME = "New Product";

	public static final String PRODUCT_DISCRIMINATOR = "P";
	public static final String ITEM_DISCRIMINATOR = "I";

	protected final String type;

	private static final long serialVersionUID = 5869287229470090229L;

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

	
	/** 
	 * @return int
	 */
	public int getProductId()
	{
		return productId;
	}

	
	/** 
	 * @param productId
	 */
	public void setProductId(int productId)
	{
		this.productId = productId;
	}

	
	/** 
	 * @return String
	 */
	public String getProductName()
	{
		return productName;
	}

	
	/** 
	 * @param productName
	 */
	public void setProductName(String productName)
	{
		this.productName = productName;
	}

	
	/** 
	 * @return float
	 */
	public float getPrice()
	{
		return price;
	}

	
	/** 
	 * @param price
	 */
	public void setPrice(float price)
	{
		this.price = price;
	}

	
	/** 
	 * @return byte[]
	 */
	public byte[] getImage()
	{
		return image;
	}

	
	/** 
	 * @param image
	 */
	public void setImage(byte[] image)
	{
		this.image = image;
	}

	
	/** 
	 * @return boolean
	 */
	public boolean isProduct()
	{
		return type.equals(PRODUCT_DISCRIMINATOR);
	}

	
	/** 
	 * @return boolean
	 */
	public boolean isItem()
	{
		return type.equals(ITEM_DISCRIMINATOR);
	}

	
	/** 
	 * @return int
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + productId;
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
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

	
	/** 
	 * @return String
	 */
	@Override
	public String toString()
	{
		return (isProduct() ? "Product" : "Item") + " [productId=" + productId + ", productName=" + productName
				+ ", price=" + price + "]";
	}

}