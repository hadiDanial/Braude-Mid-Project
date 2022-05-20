package server;

import controllers.DiscountController;
import controllers.OrderController;
import controllers.UserController;
import entities.discounts.Discount;
import entities.users.Order;
import entities.users.User;
import requests.Request;
import requests.RequestType;

public class MessageParser {

    /**
     * @param msg
     */
    public static Object handleRequest(Request req) {
        RequestType type = req.getRequestType();
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
            default:
                break;
            case AddProductsDiscount: {
                DiscountController.getInstance().addProductsDiscount((Discount)req.getMessage());
            }
             
        }
        return new Object();
    }

}