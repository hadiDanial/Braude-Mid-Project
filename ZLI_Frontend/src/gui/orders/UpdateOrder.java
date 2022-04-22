package gui.orders;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.ResourceBundle;

import controllers.OrderController;
import entities.users.Order;
import enums.Color;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

public class UpdateOrder implements Initializable
{
	private OrderController orderController;
	private Order order;
	
	@FXML
	public void setOrder(Color color,Date deliveryDate)
	{
		this.order.setColor(color);
	//	this.order.setDeliveryDate(deliveryDate);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		orderController = OrderController.getInstance();
	}
	
}