package entities.discounts;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;

import entities.products.CatalogItem;

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

	public static final PercentageDiscount firstPurchaseDiscount = new PercentageDiscount(Instant.MIN, Instant.MAX, "First Purchase Discount", 0.1f);
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
	
	public int getDiscountId()
	{
		return discountId;
	}
	public void setDiscountId(int discountId)
	{
		this.discountId = discountId;
	}
	public Instant getDiscountStartDate()
	{
		return discountStartDate;
	}
	public void setDiscountStartDate(Instant discountStartDate)
	{
		this.discountStartDate = discountStartDate;
	}
	public Instant getDiscountEndDate()
	{
		return discountEndDate;
	}
	public void setDiscountEndDate(Instant discountEndDate)
	{
		this.discountEndDate = discountEndDate;
	}
	public HashSet<CatalogItem> getProducts()
	{
		return products;
	}
	public void setProducts(HashSet<CatalogItem> products)
	{
		this.products = products;
	}
	public String getDiscountName()
	{
		return discountName;
	}
	public void setDiscountName(String discountName)
	{
		this.discountName = discountName;
	}
	
	public float getDiscountValue()
	{
		return discountValue;
	}


	public void setDiscountValue(float discountValue)
	{
		this.discountValue = discountValue;
	}
	
	protected boolean isActive()
	{
		return Instant.now().isAfter(getDiscountStartDate()) && Instant.now().isBefore(getDiscountEndDate());
	}

	public String getDiscountType()
	{
		return discountType;
	}

	public void setDiscountType(String discountType)
	{
		this.discountType = discountType;
	}


	@Override
	public int hashCode()
	{
		return Objects.hash(discountEndDate, discountId, discountName, discountStartDate, products);
	}
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
	@Override
	public String toString()
	{
		return "Discount [discountId=" + discountId + ", discountStartDate=" + discountStartDate + ", discountEndDate="
				+ discountEndDate + ", products=" + products + ", discountName=" + discountName + "]";
	}
	
}
