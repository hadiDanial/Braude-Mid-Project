//package entities.products;
//
//public class DiscountedProduct
//{
//	private Product discountedProduct;
//	private float discountedPrice;
//	
//	public DiscountedProduct(Product discountedProduct, float discountedPrice)
//	{
//		super();
//		this.discountedProduct = discountedProduct;
//		this.setDiscountedPrice(discountedPrice);
//	}
//	
//	public Product getDiscountedProduct()
//	{
//		return discountedProduct;
//	}
//	
//	public void setDiscountedProduct(Product discountedProduct)
//	{
//		this.discountedProduct = discountedProduct;
//	}
//	
//	public float getDiscountedPrice()
//	{
//		return discountedPrice;
//	}
//	
//	public void setDiscountedPrice(float discountedPrice)
//	{
//		if(discountedPrice > discountedProduct.getOriginalPrice())
//			discountedPrice = discountedProduct.getOriginalPrice();
//		this.discountedPrice = discountedPrice;
//		discountedProduct.setCurrentPrice(this.discountedPrice);
//	}
//}