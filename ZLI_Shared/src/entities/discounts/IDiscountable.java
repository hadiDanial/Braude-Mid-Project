package entities.discounts;

import java.util.HashSet;

public interface IDiscountable
{
	/**
	 * Calculates and returns the final price after applying all the discounts.
	 * @param discounts A set of the discounts to apply.
	 */
	public float getPriceAfterDiscounts(HashSet<Discount> discounts);
}
