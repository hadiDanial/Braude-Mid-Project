package entities;

import java.time.LocalDateTime;

import org.json.simple.JSONObject;

public class Order {
    // TODO: implemint the order class
    // ! this class is not final

    public int orderId;
    public float totalCost;
    public String greetingCard;
    public String color;
    public String dOrder;
    public String branch;
    public String deliveryDate;
    public String orderDate;

    public Order(int orderId, float totalCost, String greetingCard, String color, String dOrder, String branch,
            String deliveryDate, String orderDate) {
        this.orderId = orderId;
        this.totalCost = totalCost;
        this.greetingCard = greetingCard;
        this.color = color;
        this.dOrder = dOrder;
        this.branch = branch;
        this.deliveryDate = deliveryDate;
        this.orderDate = orderDate;
    }

    public JSONObject generateJsonObject() {
        // TODO: use correct words
        JSONObject obj = new JSONObject();
        obj.put("type", "Order");
        obj.put("orderID", orderId);
        obj.put("totalCost", totalCost);
        obj.put("greetingCard", greetingCard);
        obj.put("color", color);
        obj.put("dOrder", dOrder);
        obj.put("branch", branch);
        obj.put("deliveryDate", deliveryDate);
        obj.put("orderDate", orderDate);
        return obj;
    }

}
