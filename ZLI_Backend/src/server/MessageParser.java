package server;

import controllers.DiscountController;
import controllers.OrderController;
import controllers.ProductController;
import controllers.UserController;
import entities.discounts.Discount;
import entities.products.BaseProduct;
import entities.users.Order;
import entities.users.User;
import exceptions.UnauthenticatedUserException;
import exceptions.UnauthorizedRoleException;
import requests.Request;
import requests.RequestType;
import requests.UpdateEntityRequest;

public class MessageParser {

    /**
     * Handle the request - activate the correct function based on the request type.
     * @param req Request containing a request type, a message, and the user that sent the request.
     * @return
     */
    @SuppressWarnings("unchecked")
	public static Object handleRequest(Request req) {
        RequestType type = req.getRequestType();
       
        // Check if the user that sent this request is allowed to perform the required action.
        try
		{
			checkPermissions(type, req.getUser());
		} catch (UnauthenticatedUserException e)
		{
			System.out.println(e.getMessage());
			return e;
		} catch (UnauthorizedRoleException e)
		{
			System.out.println(e.getMessage());
			return e;
		}
        
        switch (type) {
            case GetAllOrders: {
                return OrderController.getInstance().getAllOrders();
            }
            case UpdateOrder: {
                return OrderController.getInstance().updateOrder((Order) req.getMessage());
            }
            case Login: {
            	User loginRequest = (User)req.getMessage();
            	return UserController.getInstance().login(loginRequest.getUsername(), loginRequest.getPassword());                
            }
            case Logout: {
            	// Message = User Id
            	return UserController.getInstance().logout((Integer) req.getMessage());
            }
            case CreateDiscount: {
                return DiscountController.getInstance().createDiscount((Discount)req.getMessage());
            }
            case GetAllDiscounts: {
                return DiscountController.getInstance().getAllDiscounts();
            }
            case AddProductsDiscount: {
                DiscountController.getInstance().addProductsDiscount((Discount)req.getMessage());
            }
            case AddProduct: {
            	return ProductController.getInstance().addProduct((BaseProduct) req.getMessage());
            }
            case UpdateProduct:
            {
            	return ProductController.getInstance().updateBaseProduct((UpdateEntityRequest<BaseProduct>) req.getMessage());
            }
            case GetAllProducts:
            {
            	return ProductController.getInstance().getAllProducts();
            }
            default:
            	break;
        }
        return null;
    }

    /**
     * Check if the user that sent this request is allowed to perform the required action.
     * @param type Requested action type.
     * @param user User that sent the request.
     * @throws UnauthenticatedUserException Thrown when the action was requested without a user (unless the action is Login).
     * @throws UnauthorizedRoleException Thrown if the user's role isn't allowed for this particular request type.
     */
	private static void checkPermissions(RequestType type, User user) throws UnauthenticatedUserException, UnauthorizedRoleException
	{
		if(type == RequestType.Login) 
			return;
		if(type != RequestType.Login && user == null)
			throw new UnauthenticatedUserException("Cannot use system without being authenticated.");
		if(!type.isAuthorized(user.getRole()))
			throw new UnauthorizedRoleException("User role " + user.getRole() + " cannot perform " + type.name() + " action!");
	}

}