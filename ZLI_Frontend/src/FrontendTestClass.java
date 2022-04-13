import java.util.Scanner;

import entities.products.Product;
import enums.ProductType;

public class FrontendTestClass
{

	public static void main(String[] args)
	{
		Product product = new Product("Test", 10, null, 5, ProductType.BridalBouquet);
		System.out.println("Front end: " + product);
		Scanner scan = new Scanner(System.in);
		scan.next();
		scan.close();
	}

}
