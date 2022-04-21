package gui.orders;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import controllers.OrderController;
import entities.users.Order;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

public class OrdersPage implements Initializable
{
	private OrderController orderController;
	private ArrayList<Order> orders;
	
	@FXML
	private TableView<Order> ordersTable;
	   
	public OrdersPage()
	{
		
		
	}

	
	public ArrayList<Order> getOrders()
	{
		return orders;
	}

	public void setOrders(ArrayList<Order> orders)
	{
		this.orders = orders;
	}


	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		orderController = OrderController.getInstance();
		orderController.getAllOrders(arr -> {
			orders = arr;
			for (Order order : orders)
			{
				System.out.println(order);
			}
		});
	}
	
	
}