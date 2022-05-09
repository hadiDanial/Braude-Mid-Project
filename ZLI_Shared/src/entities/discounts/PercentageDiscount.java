package entities.discounts;

import java.security.InvalidParameterException;
import java.time.Instant;

public class PercentageDiscount extends Discount
{
	public PercentageDiscount()
	{
		super();
	}

	public PercentageDiscount(Instant discountStartDate, Instant discountEndDate, String discountName, float discountValue)
	{
		super(discountStartDate, discountEndDate, discountName, discountValue);
		if (discountValue < 0 || discountValue > 1)
			throw new InvalidParameterException("Discount Percentage must be between 0 and 1: (" + discountValue + ").");		
	}

	@Override
	public float applyDiscount(float price)
	{
		if(isActive())
		{
			return (price - price * getDiscountValue());
		}
		else
		{
			return price;			
		}
	}
}