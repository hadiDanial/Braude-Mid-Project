import entities.products.Product;
import enums.ProductType;

public class TestClass
{

	public static void main(String[] args)
	{
		Product product = new Product(0,"Test", 10, null, 5, ProductType.BridalBouquet);
		System.out.println("Back end: " + product);
	}
}
