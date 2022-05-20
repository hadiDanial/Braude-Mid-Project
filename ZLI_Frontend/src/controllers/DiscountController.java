package controllers;

import java.util.ArrayList;

import entities.discounts.Discount;
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
    public boolean createDiscount()
	{
		throw new UnsupportedOperationException();
	}
    public Discount getProduct(int discountId)
	{
		throw new UnsupportedOperationException();
	}
    public void getAllDiscounts(IResponse<ArrayList<Discount>> response)
	{
		Request req = new Request(RequestType.GetAllOrders, null, userController.getLoggedInUser());
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
