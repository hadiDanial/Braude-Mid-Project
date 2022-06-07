package entities.products;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;

import entities.discounts.Discount;
import entities.discounts.IDiscountable;
import entities.other.Branch;

public class CatalogItem implements IDiscountable, Serializable
{
	private BaseProduct baseProduct;
	private Branch branch;
	private int quantityInStock;
	private HashSet<Discount> discounts;

	private static final long serialVersionUID = 6014173283082159954L;

	public CatalogItem()
	{
		super();
		discounts = new HashSet<Discount>();
	}

	public CatalogItem(BaseProduct baseProduct, Branch branch, int quantityInStock)
	{
		super();
		this.baseProduct = baseProduct;
		this.branch = branch;
		this.quantityInStock = quantityInStock;
		discounts = new HashSet<Discount>();
	}

	
	/** 
	 * @return float
	 */
	@Override
	public float getPriceAfterDiscounts()
	{
		float finalPrice = baseProduct.getPrice();
		for (Discount discount : discounts)
		{
			finalPrice = discount.applyDiscount(finalPrice);
		}
		return finalPrice;
	}

	
	/** 
	 * @param discounts
	 */
	@Override
	public void setDiscounts(HashSet<Discount> discounts)
	{
		this.discounts = discounts;
	}

	
	/** 
	 * @param discount
	 */
	@Override
	public void addDiscount(Discount discount)
	{
		if(discounts == null) discounts = new HashSet<Discount>();
		discounts.add(discount);
	}
	
	/** 
	 * @return BaseProduct
	 */
	public BaseProduct getBaseProduct()
	{
		return baseProduct;
	}

	
	/** 
	 * @param baseProduct
	 */
	public void setBaseProduct(BaseProduct baseProduct)
	{
		this.baseProduct = baseProduct;
	}

	
	/** 
	 * @return Branch
	 */
	public Branch getBranch()
	{
		return branch;
	}

	
	/** 
	 * @param branch
	 */
	public void setBranch(Branch branch)
	{
		this.branch = branch;
	}

	
	/** 
	 * @return int
	 */
	public int getQuantityInStock()
	{
		return quantityInStock;
	}

	
	/** 
	 * @param quantityInStock
	 */
	public void setQuantityInStock(int quantityInStock)
	{
		this.quantityInStock = quantityInStock;
	}

	
	/** 
	 * @return String
	 */
	public String getProductName()
	{
		return baseProduct.getProductName();
	}
	
	/** 
	 * @return Float
	 */
	public Float getBasePrice()
	{
		return baseProduct.getPrice();
	}
	
	/** 
	 * @return String
	 */
	public String getDetails()
	{
		if(baseProduct.isProduct())
		{
			Product p = (Product) baseProduct;
			return "[Product]" + p.getProductType() + ", " + p.getItemList();
		}
		else
		{
			Item i = (Item) baseProduct;
			return "[Item]" + i.getItemType() + ", " + i.getPrimaryColor();
		}
	}
	
	
	/** 
	 * @return String
	 */
	@Override
	public String toString()
	{
		return baseProduct.toString();
	}

	
	/** 
	 * @return int
	 */
	@Override
	public int hashCode()
	{
		return Objects.hash(baseProduct, branch);
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
		CatalogItem other = (CatalogItem) obj;
		return this.baseProduct.getProductId() == other.getBaseProduct().getProductId()
				&& this.branch.getBranchId() == other.getBranch().getBranchId();
	}

}
