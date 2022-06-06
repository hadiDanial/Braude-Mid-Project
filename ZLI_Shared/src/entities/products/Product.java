package entities.products;

import java.util.HashMap;
import java.util.Map;

import enums.ProductType;

public class Product extends BaseProduct
{
	private ProductType productType;
	private HashMap<Item, Integer> items;

	private static final long serialVersionUID = -1068877848209015708L;

	public Product(String productName, float price, byte[] image, ProductType productType)
	{
		super(productName, price, image, PRODUCT_DISCRIMINATOR);
		this.productType = productType;
		this.items = new HashMap<Item, Integer>();
	}

	public Product()
	{
		super(BaseProduct.PRODUCT_DISCRIMINATOR);
		this.items = new HashMap<Item, Integer>();
	}

	public ProductType getProductType()
	{
		return productType;
	}

	public void setProductType(ProductType productType)
	{
		this.productType = productType;
	}

	public HashMap<Item, Integer> getItems()
	{
		return items;
	}

	public void addItem(Item item)
	{
		if (items.containsKey(item))
			items.put(item, items.get(item) + 1);
		else
			items.put(item, 1);
	}

	public void removeItem(Item item)
	{
		if (items.containsKey(item))
		{
			int count = items.get(item);
			count--;
			if (count <= 0)
				items.remove(item);
			else
				items.put(item, count);
		}
	}

	public void setItems(HashMap<Item, Integer> items)
	{
		this.items = items;
	}

	public String getItemList()
	{
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<Item, Integer> entry : items.entrySet())
		{
			Item key = entry.getKey();
			Integer val = entry.getValue();
			sb.append(key.getProductName());
			sb.append(" x" + val);
			sb.append(", ");
		}
		if(sb.length() > 2)
			sb.delete(sb.length() - 2, sb.length());
		return sb.toString();
	}

	public int getItemQuantity(Item item)
	{
		return items.get(item);
	}
}