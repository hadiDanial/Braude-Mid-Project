package controllers;

import java.util.ArrayList;

import entities.products.*;
import requests.EntityRequestWithId;
import requests.Request;
import requests.RequestType;
import utility.IResponse;

public class ProductController
{
	ArrayList<CatalogItem> catalog;
	ArrayList<Item> items;

	private static ProductController instance;
	private UserController userController;
	private ClientController clientController;
	
	private ProductController()
	{
		catalog = new ArrayList<CatalogItem>();
		userController = UserController.getInstance();
		clientController = ClientController.getInstance();
	}

	
	/** 
	 * @return ProductController
	 */
	public static synchronized ProductController getInstance()
	{
		if (instance == null)
		{
			instance = new ProductController();
		}
		return instance;
	}

	
	/** 
	 * @param response
	 */
	public void getAllCatalogItems(IResponse<ArrayList<CatalogItem>> response)
	{
		if(catalog == null || catalog.isEmpty())
		{			
			Request req = new Request(RequestType.GET_CATALOG);
			ClientController.getInstance().sendRequest(req, executeResponseAndSaveCatalog(response));
		}
		else 
		{
			response.executeAfterResponse(catalog);
		}
	}

	/**
	 * Gets the catalog by branch
	 */
	public void getCatalogByBranch(int branchId, IResponse<ArrayList<CatalogItem>> response)
	{
		Request req = new Request(RequestType.GET_CATALOG_BY_BRANCH, branchId);
		ClientController.getInstance().sendRequest(req, executeResponseAndSaveCatalog(response));
	}

	
	/** 
	 * @param response
	 */
	public void getAllItems(IResponse<ArrayList<Item>> response)
	{
		if(items == null || items.isEmpty())
		{			
			Request req = new Request(RequestType.GET_ALL_ITEMS);
			ClientController.getInstance().sendRequest(req, executeResponseAndSaveItems(response));
		}
		else 
		{
			response.executeAfterResponse(catalog);
		}
	}


	
	/** 
	 * @param response
	 * @return IResponse<ArrayList<CatalogItem>>
	 */
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
	
	/** 
	 * @param response
	 * @return IResponse<ArrayList<Item>>
	 */
	private IResponse<ArrayList<Item>> executeResponseAndSaveItems(IResponse<ArrayList<Item>> response)
	{
		return new IResponse<ArrayList<Item>>()
		{
			
			@Override
			public void executeAfterResponse(Object message)
			{
				ProductController.getInstance().setItems((ArrayList<Item>)message);
				response.executeAfterResponse(message);
			}
		};
	}

	
	/** 
	 * @param catalog
	 */
	protected void setCatalog(ArrayList<CatalogItem> catalog)
	{
		this.catalog = catalog;
	}

	
	/** 
	 * @param message
	 */
	protected void setItems(ArrayList<Item> message)
	{
		this.items = message;
	}

	
	/** 
	 * @param newProduct
	 * @param response
	 */
	public void createProduct(BaseProduct newProduct, IResponse<Boolean> response)
	{
		Request req = new Request(RequestType.ADD_PRODUCT, newProduct);
		clientController.sendRequest(req, response);
	}

	
	/** 
	 * @param productId
	 * @param activeProduct
	 * @param response
	 */
	public void updateProduct(int productId, BaseProduct activeProduct, IResponse<Boolean> response)
	{
		EntityRequestWithId<BaseProduct> e = new EntityRequestWithId<BaseProduct>();
		e.setEntity(activeProduct);
		e.setEntityId(productId);
		Request req = new Request(RequestType.UPDATE_PRODUCT, e);
		clientController.sendRequest(req, response);
	}
	
	public void reset()
	{
		if(items != null)
		this.items.clear();
		if(catalog != null)
		this.catalog.clear();
	}

}