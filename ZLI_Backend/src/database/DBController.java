//package database;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import controllers.OrderController;
//import entities.users.Order;
//
//public class DBController {
//    public static void sendNewOrderToDB(Order order) {
//        // ? may change to receive Order instead of Object
//        // * will change to use the true values of the Order class
//        ArrayList<String> data = new ArrayList<String>();
//        data.add("" + order.getOrderId());
//        data.add("" + order.getTotalCost());
//        data.add(order.getGreetingCard());
//        data.add(order.getColor().toString());
//        data.add(order.getOrderDetails());
//        data.add(order.getBranchName());
//        data.add(order.getDeliveryDate().toString());
//        data.add(order.getOrderDate().toString());
//
//        DatabaseConnection.getInstance().sendNewOrderToDB(data);
//    }
//
//    public static Order getOrderFromDB(String orderID) {
//        HashMap<String, String> orderData = new HashMap<String, String>();
//        orderData = DatabaseConnection.getInstance().getOrderFromDB(orderID);
//        Order order = OrderController.createNewOrderFromDBArrStr(orderData);
//        return order;
//    }
//
//
//    public static void updateColorDateOrderInDB(String orderID, String color, String date) {
//        ArrayList<String> data = new ArrayList<String>();
//        data.add(color);
//        data.add(date);
//        DatabaseConnection.getInstance().updateOrderInDB(orderID, data);
//    }
//
//}