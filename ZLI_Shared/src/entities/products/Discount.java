//package entities.products;
//
//import java.util.ArrayList;
//
//public class Discount
//{
//	private int discountId;
//	private ArrayList<DiscountedProduct> discountedProducts;
//	private int startDate;
//	private int endDate;
//	public Discount(ArrayList<DiscountedProduct> discountedProducts, int startDate, int endDate)
//	{
//		super();
//		this.discountedProducts = discountedProducts;
//		this.startDate = startDate;
//		this.endDate = endDate;
//	}
//	public Discount(int startDate, int endDate)
//	{
//		super();
//		this.startDate = startDate;
//		this.endDate = endDate;
//	}
//	public int getDiscountId()
//	{
//		return discountId;
//	}
//	public void setDiscountId(int discountId)
//	{
//		this.discountId = discountId;
//	}
//	
//	public ArrayList<DiscountedProduct> getDiscountedProducts()
//	{
//		return discountedProducts;
//	}
//	
//	public void setDiscountedProducts(ArrayList<DiscountedProduct> discountedProducts)
//	{
//		this.discountedProducts = discountedProducts;
//	}
//	
//	public boolean addDiscountedProduct(Product product, float discountPrice)
//	{
//		DiscountedProduct discountedProduct = new DiscountedProduct(product, discountPrice);
//		if(discountedProducts.contains(discountedProduct))
//			return false;
//		else return discountedProducts.add(discountedProduct);
//	}
//	
//	public boolean removeDiscountedProduct(DiscountedProduct product)
//	{
//		if(!discountedProducts.contains(product))
//			return false;
//		else return discountedProducts.remove(product);
//	}
//	public int getStartDate()
//	{
//		return startDate;
//	}
//	public void setStartDate(int startDate)
//	{
//		this.startDate = startDate;
//	}
//	
//	public int getEndDate()
//	{
//		return endDate;
//	}
//	
//	public void setEndDate(int endDate)
//	{
//		this.endDate = endDate;
//	}
//	
//	@Override
//	public int hashCode()
//	{
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + endDate;
//		result = prime * result + startDate;
//		return result;
//	}
//	
//	@Override
//	public boolean equals(Object obj)
//	{
//		if (this == obj)
//		{
//			return true;
//		}
//		if (!(obj instanceof Discount))
//		{
//			return false;
//		}
//		Discount other = (Discount) obj;
//		if (endDate != other.endDate)
//		{
//			return false;
//		}
//		if (startDate != other.startDate)
//		{
//			return false;
//		}
//		return true;
//	}
//	
//	
//	
//
//}