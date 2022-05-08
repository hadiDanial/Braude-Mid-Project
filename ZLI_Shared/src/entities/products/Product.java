package entities.products;

import enums.ProductType;

public class Product extends BaseProduct
{
	private ProductType productType;

	private static final long serialVersionUID = -1068877848209015708L;

	public Product(String productName, float price, byte[] image, ProductType productType)
	{
		super(productName, price, image, PRODUCT_DISCRIMINATOR);
		this.productType = productType;
	}

	public ProductType getProductType()
	{
		return productType;
	}

	public void setProductType(ProductType productType)
	{
		this.productType = productType;
	}

}