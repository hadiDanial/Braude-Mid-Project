package entities.discounts;

import java.security.InvalidParameterException;
import java.time.Instant;

public class PercentageDiscount extends Discount
{
	private static final long serialVersionUID = 5420333059268417370L;

	public PercentageDiscount()
	{
		super(Discount.PERCENTAGE_DISCRIMINATOR);
	}

	public PercentageDiscount(Instant discountStartDate, Instant discountEndDate, String discountName, float discountValue)
	{
		super(discountStartDate, discountEndDate, discountName, discountValue, Discount.PERCENTAGE_DISCRIMINATOR);
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