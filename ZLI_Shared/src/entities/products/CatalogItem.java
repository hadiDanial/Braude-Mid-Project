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

	@Override
	public void setDiscounts(HashSet<Discount> discounts)
	{
		this.discounts = discounts;
	}

	@Override
	public void addDiscount(Discount discount)
	{
		if(discounts == null) discounts = new HashSet<Discount>();
		discounts.add(discount);
	}
	public BaseProduct getBaseProduct()
	{
		return baseProduct;
	}

	public void setBaseProduct(BaseProduct baseProduct)
	{
		this.baseProduct = baseProduct;
	}

	public Branch getBranch()
	{
		return branch;
	}

	public void setBranch(Branch branch)
	{
		this.branch = branch;
	}

	public int getQuantityInStock()
	{
		return quantityInStock;
	}

	public void setQuantityInStock(int quantityInStock)
	{
		this.quantityInStock = quantityInStock;
	}

	@Override
	public String toString()
	{
		return baseProduct.toString();
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(baseProduct, branch);
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
		CatalogItem other = (CatalogItem) obj;
		return this.baseProduct.getProductId() == other.getBaseProduct().getProductId()
				&& this.branch.getBranchId() == other.getBranch().getBranchId();
	}

}
