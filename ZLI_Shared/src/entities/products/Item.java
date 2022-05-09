package entities.products;

import enums.*;

public class Item extends BaseProduct
{
	private ItemType itemType;
	private ColorEnum primaryColor;
	
	private static final long serialVersionUID = -5139650780449358616L;

	public Item(String productName, float price, byte[] image, ItemType itemType, ColorEnum primaryColor)
	{
		super(productName, price, image, ITEM_DISCRIMINATOR);
		this.itemType = itemType;
		this.primaryColor = primaryColor;
	}
	public ItemType getItemType()
	{
		return itemType;
	}
	public void setItemType(ItemType itemType)
	{
		this.itemType = itemType;
	}
	public ColorEnum getPrimaryColor()
	{
		return primaryColor;
	}
	public void setPrimaryColor(ColorEnum primaryColor)
	{
		this.primaryColor = primaryColor;
	}
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((itemType == null) ? 0 : itemType.hashCode());
		result = prime * result + ((primaryColor == null) ? 0 : primaryColor.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (!super.equals(obj))
		{
			return false;
		}
		if (!(obj instanceof Item))
		{
			return false;
		}
		Item other = (Item) obj;
		if (itemType != other.itemType)
		{
			return false;
		}
		if (primaryColor != other.primaryColor)
		{
			return false;
		}
		return true;
	}
	
	
}