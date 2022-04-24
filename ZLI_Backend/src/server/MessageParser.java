package server;

import controllers.OrderController;

import requests.Request;
import requests.RequestType;
import requests.UpdateOrderRequest;

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
                return OrderController.getInstance().updateOrder((UpdateOrderRequest) req.getMessage());
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