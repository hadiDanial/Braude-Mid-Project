package server;

import controllers.OrderController;
import database.DBController;

import ocsf.server.ConnectionToClient;
import requests.Request;
import requests.RequestType;
import requests.UpdateOrderRequest;

public class MessageParser {

    /**
     *
     * @param msg
     * @throws Exception
     */
    public static Object handleRequest(Request req) {
        RequestType type = req.getRequestType();
        switch (type) {
            case GetAllOrders: {
                return OrderController.getAllOrders();
            }
            case UpdateOrder: {
                return OrderController.updateOrder((UpdateOrderRequest) req.getMessage());
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