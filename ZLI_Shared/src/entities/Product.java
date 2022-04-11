package entities;

import enums.ProductType;

public class Product extends BaseProduct
{

	private ProductType productType;

	public Product(int productId, String productName, float price, byte[] image, int quantityInStock,
			ProductType productType)
	{
		super(productId, productName, price, image, quantityInStock);
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