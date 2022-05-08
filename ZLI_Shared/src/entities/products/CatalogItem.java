package entities.products;

import java.util.HashSet;

import entities.discounts.Discount;
import entities.discounts.IDiscountable;
import entities.other.Branch;

public class CatalogItem implements IDiscountable
{
	private BaseProduct baseProduct;
	private Branch branch;
	private int quantityInStock;

	public CatalogItem()
	{
		super();
	}

	public CatalogItem(BaseProduct baseProduct, Branch branch, int quantityInStock)
	{
		super();
		this.baseProduct = baseProduct;
		this.branch = branch;
		this.quantityInStock = quantityInStock;
	}

	@Override
	public float getPriceAfterDiscounts(HashSet<Discount> discounts)
	{
		float finalPrice = baseProduct.getPrice();
		for (Discount discount : discounts)
		{
			finalPrice = discount.applyDiscount(finalPrice);
		}
		return finalPrice;
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

}
