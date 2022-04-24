package controllers;

import java.time.Instant;
import java.util.HashMap;

import entities.other.Branch;
import entities.users.Order;
import enums.Color;
import enums.OrderStatus;
import requests.UpdateOrderRequest;

public class OrderController {
    // TODO: make it make sense
    public static Order createNewOrderFromDBArrStr(HashMap<String, String> orderData) {
        Order order = new Order(Integer.parseInt(orderData.get("orderNumber")), orderData.get("greetingCard"),
                Instant.parse(orderData.get("date")), Float.parseFloat(orderData.get("price")),
                OrderStatus.Confirmed,
                new Branch(orderData.get("shop")), Color.valueOf(orderData.get("color")),
                orderData.get("dOrder"), Instant.parse(orderData.get("orderDate")));
        return order;
    }

    public static Object getAllOrders() {
        return null;
    }

    public static Object updateOrder(UpdateOrderRequest message) {
        return null;
    }

}
