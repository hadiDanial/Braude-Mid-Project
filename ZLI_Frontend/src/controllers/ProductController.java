package controllers;

import java.util.ArrayList;

import entities.products.*;
import requests.Request;
import requests.RequestType;
import utility.IResponse;

public class ProductController
{
	ArrayList<CatalogItem> catalog;

	private static ProductController instance;
	private UserController userController;

	private ProductController()
	{
		catalog = new ArrayList<CatalogItem>();
		userController = UserController.getInstance();
	}

	public static ProductController getInstance()
	{
		if (instance == null)
		{
			instance = new ProductController();
		}
		return instance;
	}

	public boolean createProduct()
	{
		// TODO - implement ProductController.createProduct
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param productId
	 */
	public Product getProduct(int productId)
	{
		// TODO - implement ProductController.getProduct
		throw new UnsupportedOperationException();
	}

	public void getAllProducts(IResponse<ArrayList<CatalogItem>> response)
	{
		Request req = new Request(RequestType.GetAllProducts, null, userController.getLoggedInUser());
		ClientController.getInstance().sendRequest(req, executeResponseAndSaveCatalog(response));
	}


	private IResponse<ArrayList<CatalogItem>> executeResponseAndSaveCatalog(IResponse<ArrayList<CatalogItem>> response)
	{
		return new IResponse<ArrayList<CatalogItem>>()
		{

			@Override
			public void executeAfterResponse(Object message)
			{
				ProductController.getInstance().setCatalog((ArrayList<CatalogItem>)message);
				response.executeAfterResponse(message);
			}
		};
	}

	protected void setCatalog(ArrayList<CatalogItem> catalog)
	{
		this.catalog = catalog;
	}

	/**
	 * Gets the catalog by branch
	 */
	public void getCatalogByBranch(int branchId, IResponse<ArrayList<CatalogItem>> response)
	{
		Request req = new Request(RequestType.GetCatalogByBranch, branchId, userController.getLoggedInUser());
		ClientController.getInstance().sendRequest(req, executeResponseAndSaveCatalog(response));
	}

	/**
	 * 
	 * @param productToEdit
	 * @param editedProduct
	 */
	public boolean editProduct(Product productToEdit, Product editedProduct)
	{
		// TODO - implement ProductController.editProduct
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param product
	 * @param quantity
	 */
	public boolean setQuantity(Product product, int quantity)
	{
		// TODO - implement ProductController.setQuantity
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param product
	 * @param discountedPrice
	 */
	public boolean setProductDiscountPrice(Product product, float discountedPrice)
	{
		// TODO - implement ProductController.setProductDiscountPrice
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param product
	 * @param onSale
	 */
	public boolean setProductDiscountStatus(Product product, boolean onSale)
	{
		// TODO - implement ProductController.setProductDiscountStatus
		throw new UnsupportedOperationException();
	}

}