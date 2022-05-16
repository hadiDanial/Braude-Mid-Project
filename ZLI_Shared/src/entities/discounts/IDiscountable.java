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
}
