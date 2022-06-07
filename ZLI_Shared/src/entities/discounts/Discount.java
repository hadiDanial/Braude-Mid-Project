package entities.discounts;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;

import entities.products.CatalogItem;
import utility.DateFormatter;

public abstract class Discount implements Serializable
{
	private int discountId;
	private Instant discountStartDate, discountEndDate;
	private HashSet<CatalogItem> products;
	private String discountName;
	private float discountValue;
	private String discountType;
	
	
	public static final String PERCENTAGE_DISCRIMINATOR = "P";
	public static final String VALUE_DISCRIMINATOR = "V";
	
	private static final long serialVersionUID = 4565344021091868423L;

	public static final PercentageDiscount firstPurchaseDiscount = new PercentageDiscount(Instant.MIN, Instant.MAX, "First Purchase Discount", 0.2f);
	public Discount(String discountType)
	{
		super();
		products = new HashSet<CatalogItem>();
		this.discountType = discountType;
	}
	
	
	public Discount(Instant discountStartDate, Instant discountEndDate, String discountName, float discountValue, String discountType)
	{
		super();
		this.discountStartDate = discountStartDate;
		this.discountEndDate = discountEndDate;
		this.discountName = discountName;
		this.discountValue = discountValue;
		products = new HashSet<CatalogItem>();
		this.discountType = discountType;
	}


	public abstract float applyDiscount(float price);
	
	
	/** 
	 * @return int
	 */
	public int getDiscountId()
	{
		return discountId;
	}
	
	/** 
	 * @return int
	 */
	public int getProductsSize()
	{
		return products.size();
	}
	
	/** 
	 * @param discountId
	 */
	public void setDiscountId(int discountId)
	{
		this.discountId = discountId;
	}
	
	/** 
	 * @return Instant
	 */
	public Instant getDiscountStartDate()
	{
		return discountStartDate;
	}
	
	/** 
	 * @param discountStartDate
	 */
	public void setDiscountStartDate(Instant discountStartDate)
	{
		this.discountStartDate = discountStartDate;
	}
	
	/** 
	 * @return Instant
	 */
	public Instant getDiscountEndDate()
	{
		return discountEndDate;
	}
	
	/** 
	 * @param discountEndDate
	 */
	public void setDiscountEndDate(Instant discountEndDate)
	{
		this.discountEndDate = discountEndDate;
	}
	
	/** 
	 * @return HashSet<CatalogItem>
	 */
	public HashSet<CatalogItem> getProducts()
	{
		return products;
	}
	
	/** 
	 * @param products
	 */
	public void setProducts(HashSet<CatalogItem> products)
	{
		this.products = products;
	}
	
	/** 
	 * @return String
	 */
	public String getDiscountName()
	{
		return discountName;
	}
	
	/** 
	 * @param discountName
	 */
	public void setDiscountName(String discountName)
	{
		this.discountName = discountName;
	}
	
	
	/** 
	 * @return float
	 */
	public float getDiscountValue()
	{
		return discountValue;
	}


	
	/** 
	 * @param discountValue
	 */
	public void setDiscountValue(float discountValue)
	{
		this.discountValue = discountValue;
	}
	
	
	/** 
	 * @return boolean
	 */
	protected boolean isActive()
	{
		return Instant.now().isAfter(getDiscountStartDate()) && Instant.now().isBefore(getDiscountEndDate());
	}

	
	/** 
	 * @return String
	 */
	public String getDiscountType()
	{
		return discountType;
	}

	
	/** 
	 * @param discountType
	 */
	public void setDiscountType(String discountType)
	{
		this.discountType = discountType;
	}
	
	/** 
	 * @param catalogItem
	 */
	public void addProduct(CatalogItem catalogItem)
	{
		products.add(catalogItem);
	}
	
	/** 
	 * @param catalogItem
	 */
	public void removeProduct(CatalogItem catalogItem)
	{
		products.remove(catalogItem);
	}
	
	/** 
	 * @return int
	 */
	@Override
	public int hashCode()
	{
		return Objects.hash(discountEndDate, discountId, discountName, discountStartDate, products);
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Discount other = (Discount) obj;
		return Objects.equals(discountEndDate, other.discountEndDate) && discountId == other.discountId
				&& Objects.equals(discountName, other.discountName)
				&& Objects.equals(discountStartDate, other.discountStartDate)
				&& Objects.equals(products, other.products);
	}
	
	/** 
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "Discount [discountId=" + discountId + ", discountStartDate=" + discountStartDate + ", discountEndDate="
				+ discountEndDate + ", products=" + products + ", discountName=" + discountName + "]";
	}

	
	/** 
	 * @return String
	 */
	public String getFormattedDiscountStartDate()
	{
		return DateFormatter.formatInstant(discountStartDate, true);
	}

	
	
	/** 
	 * @return String
	 */
	public String getFormattedDiscountEndDate()
	{
		return DateFormatter.formatInstant(discountEndDate, true);
	}
	
}
