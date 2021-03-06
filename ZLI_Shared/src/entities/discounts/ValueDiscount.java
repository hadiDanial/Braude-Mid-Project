package entities.discounts;

import java.security.InvalidParameterException;
import java.time.Instant;

public class ValueDiscount extends Discount
{
	private static final long serialVersionUID = 2598713454207636142L;

	public ValueDiscount()
	{
		super(Discount.VALUE_DISCRIMINATOR);
	}

	public ValueDiscount(Instant discountStartDate, Instant discountEndDate, String discountName, float discountValue)
	{
		super(discountStartDate, discountEndDate, discountName, discountValue, Discount.VALUE_DISCRIMINATOR);
		if (discountValue < 0)
			throw new InvalidParameterException("Discount Value must be non-negative: (" + discountValue + ").");
	}

	
	/** 
	 * @param price
	 * @return float
	 */
	@Override
	public float applyDiscount(float price)
	{
		if(isActive())
		{
			return (price - getDiscountValue());
		}
		else
		{
			return price;			
		}
	}
}