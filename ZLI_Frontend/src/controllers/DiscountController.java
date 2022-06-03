package controllers;

import java.util.ArrayList;

import entities.discounts.Discount;
import entities.products.CatalogItem;
import requests.Request;
import requests.RequestType;
import utility.IResponse;

public class DiscountController {
    
    ArrayList<Discount> discounts;
    private static DiscountController instance;
    private UserController userController;;

    private DiscountController()
    {
        discounts=new ArrayList<Discount>();
        userController=UserController.getInstance();
    }
    public static DiscountController getInstance(){
        if (instance == null)
		{
			instance = new DiscountController();
		}
		return instance;
    }
    public void createDiscount(Discount discount,IResponse<Boolean> response)
	{
		Request req=new Request(RequestType.CREATE_DISCOUNT, discount,userController.getLoggedInUser());
		ClientController.getInstance().sendRequest(req, response);
	}
	public void addProductsToDiscount(ArrayList<CatalogItem> products,Discount discount,IResponse<Boolean> response)
	{
		for(CatalogItem p : products)
		{
			discount.addProduct(p);
		}
		Request req=new Request(RequestType.ADD_PRODUCTS_TO_DISCOUNT,discount,userController.getLoggedInUser());
		ClientController.getInstance().sendRequest(req, response);
	}
	public void removeProductsToDiscount(ArrayList<CatalogItem> products,Discount discount,IResponse<Boolean> response)
	{
		for(CatalogItem p : products)
		{
			discount.removeProduct(p);
		}
		Request req=new Request(RequestType.REMOVE_PRODUCTS_FROM_DISCOUNT,discount,userController.getLoggedInUser());
		ClientController.getInstance().sendRequest(req, response);
	}
	public void getDiscountsByBranch(int branchId,IResponse<ArrayList<Discount>> response)
	{
		Request req=new Request(RequestType.GET_DISCOUNTS_BY_BRANCH, branchId,userController.getLoggedInUser());
		ClientController.getInstance().sendRequest(req, response);
	}
    public void getAllDiscounts(IResponse<ArrayList<Discount>> response)
	{
		Request req = new Request(RequestType.GET_ALL_DISCOUNTS, null, userController.getLoggedInUser());
		ClientController.getInstance().sendRequest(req, executeResponseAndSaveDiscount(response));
	}
    private IResponse<ArrayList<Discount>> executeResponseAndSaveDiscount(IResponse<ArrayList<Discount>> response)
	{
		return new IResponse<ArrayList<Discount>>()
		{
			@Override
			public void executeAfterResponse(Object message)
			{
				DiscountController.getInstance().setDiscount((ArrayList<Discount>)message);
				response.executeAfterResponse(message);
			}
		};
	}
    protected void setDiscount(ArrayList<Discount> discounts) {
        this.discounts =discounts;
    }
    public boolean editDiscount(int discountId, Discount editeDiscount)
	{
		throw new UnsupportedOperationException();
	}

}
