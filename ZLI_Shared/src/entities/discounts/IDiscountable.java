package entities.discounts;

import java.util.HashSet;

public interface IDiscountable
{
	/**
	 * Calculates and returns the final price after applying all the discounts.
	 */
	public float getPriceAfterDiscounts();
	
	/**
	 * Set the discounts to apply.
	 * @param discounts A set of the discounts to apply.
	 */
	public void setDiscounts(HashSet<Discount> discounts);
	
	/**
	 * Add a discount to the discounts set.
	 * @param discount Discount to add.
	 */
	public void addDiscount(Discount discount);
}
