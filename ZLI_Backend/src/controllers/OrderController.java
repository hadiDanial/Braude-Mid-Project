package controllers;

import java.time.Instant;
import java.util.HashMap;

import entities.other.Branch;
import entities.users.Order;
import enums.Color;
import enums.OrderStatus;

public class OrderController {
    // TODO: make it make sense
    public static Order createNewOrderFromDBArrStr(HashMap<String, String> orderData) {
        Order order = new Order(Integer.parseInt(orderData.get("orderNumber")), orderData.get("greetingCard"),
                Instant.now(), Float.parseFloat(orderData.get("price")), OrderStatus.Confirmed,
                new Branch(orderData.get("shop")), Color.Mixed,
                orderData.get(6));
        return order;
    }
}
