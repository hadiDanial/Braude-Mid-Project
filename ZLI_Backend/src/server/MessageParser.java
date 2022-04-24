package server;

import controllers.OrderController;
import entities.users.Order;
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

                break;
            }
            case Logout: {

                break;
            }
            default:
                break;
        }
        return new Object();
    }

}