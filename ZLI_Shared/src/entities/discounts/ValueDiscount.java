package entities.discounts;

import java.security.InvalidParameterException;
import java.time.Instant;
import java.util.HashSet;

import entities.products.CatalogItem;

public class ValueDiscount extends Discount
{

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
