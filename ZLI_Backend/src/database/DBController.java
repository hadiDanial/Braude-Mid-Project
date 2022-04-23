package database;

import java.util.ArrayList;

import entities.Order;

public class DBController {
    public static void sendNewOrderToDB(Order order) {
        // ? may change to receive Order instead of Object
        // * will change to use the true values of the Order class
        ArrayList<String> data = new ArrayList<String>();
        data.add("" + order.orderId);
        data.add("" + order.totalCost);
        data.add(order.greetingCard);
        data.add(order.color);
        data.add(order.dOrder);
        data.add(order.branch);
        data.add(order.deliveryDate);
        data.add(order.orderDate);

        DataBase.getInstance().sendNewOrderToDB(data);
    }

    public static Order getOrderFromDB(String orderID) {
        ArrayList<String> orderData = new ArrayList<String>();
        orderData = DataBase.getInstance().getOrderFromDB(orderID);
        Order order = new Order(Integer.parseInt(orderData.get(0)), Float.parseFloat(orderData.get(1)),
                orderData.get(2), orderData.get(3), orderData.get(4), orderData.get(5), orderData.get(6),
                orderData.get(7));
        return order;
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
