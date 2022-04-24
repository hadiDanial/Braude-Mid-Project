package database;

import java.util.ArrayList;
import java.util.HashMap;

import controllers.OrderController;
import entities.users.Order;

public class DBController {
    public static void sendNewOrderToDB(Order order) {
        // ? may change to receive Order instead of Object
        // * will change to use the true values of the Order class
        ArrayList<String> data = new ArrayList<String>();
        data.add("" + order.getOrderId());
        data.add("" + order.getTotalCost());
        data.add(order.getGreetingCard());
        data.add(order.getColor().toString());
        data.add(order.getOrderDetails());
        data.add(order.getBranchName());
        data.add(order.getDeliveryDate().toString());
        data.add(order.getOrderDate().toString());

        DataBase.getInstance().sendNewOrderToDB(data);
    }

    public static Order getOrderFromDB(String orderID) {
        HashMap<String, String> orderData = new HashMap<String, String>();
        orderData = DataBase.getInstance().getOrderFromDB(orderID);
        Order order = OrderController.createNewOrderFromDBArrStr(orderData);
        return order;
    }

    public static ArrayList<Order> getAllOrdersFromDB() {
        DataBase.getInstance().getAllOrdersFromDB();
        return null;
    }

    public static void updateColorDateOrderInDB(String orderID, String color, String date) {
        ArrayList<String> data = new ArrayList<String>();
        data.add(color);
        data.add(date);
        DataBase.getInstance().updateColorDateOrderInDB(orderID, data);
    }

    public static void updateColorOrderInDB(String orderID, String color) {
        DataBase.getInstance().updateColorOrderInDB(orderID, color);
    }

    public static void updateDateOrderInDB(String orderID, String date) {
        DataBase.getInstance().updateDateOrderInDB(orderID, date);
    }
}
