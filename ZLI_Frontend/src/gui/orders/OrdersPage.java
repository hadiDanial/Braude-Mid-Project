package gui.orders;

import java.net.URL;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import client.ClientProperties;
import controllers.ClientController;
import controllers.OrderController;
import entities.users.Order;
import enums.Color;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class OrdersPage implements Initializable
{
	private OrderController orderController;
	private ArrayList<Order> orders;

	@FXML
	private TableView<Order> ordersTable;

	@FXML
	private AnchorPane parent;

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
		generateTableColumns();		
		setTableSettings();
		orderController.getAllOrders(arr -> {
			orders = arr;
			ordersTable.getItems().addAll(orders);
			

		});
	}

	private void setTableSettings()
	{
		parent.setPrefHeight(ClientProperties.getClientHeight());
		parent.setPrefWidth(ClientProperties.getClientWidth());
		StackPane pane = new StackPane();
		pane.getChildren().add(ordersTable);
		parent.getChildren().add(pane);
		double tableWidth = parent.getPrefWidth() * 7 / 8;
		ordersTable.setPrefWidth(tableWidth);
		ordersTable.setMinWidth(tableWidth * 0.5);
		double left = (parent.getPrefWidth() - tableWidth) / 2,
				right = ClientProperties.getClientWidth() - ((parent.getPrefWidth() - tableWidth) / 2 + tableWidth);
		System.out.println(parent.getPrefWidth() + " " + left + " " + right);
		AnchorPane.setLeftAnchor(pane, left);
		AnchorPane.setRightAnchor(pane, right);
		AnchorPane.setTopAnchor(pane, 100.0);
		pane.setAlignment(Pos.CENTER);
	}

	private void generateTableColumns()
	{
		ordersTable = new TableView<Order>();
		TableColumn<Order, Integer> orderNumberColumn = new TableColumn<Order, Integer>("#");
		orderNumberColumn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("orderId"));
		TableColumn<Order, Float> priceColumn = new TableColumn<Order, Float>("Price");
		priceColumn.setCellValueFactory(new PropertyValueFactory<Order, Float>("price"));
		TableColumn<Order, Color> colorColumn = new TableColumn<Order, Color>("Color");
		colorColumn.setCellValueFactory(new PropertyValueFactory<Order, Color>("color"));
		TableColumn<Order, String> detailsColumn = new TableColumn<Order, String>("Details");
		detailsColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("details"));
		detailsColumn.setPrefWidth(300.0);
		TableColumn<Order, String> greetingColumn = new TableColumn<Order, String>("Greeting");
		greetingColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("greeting"));
		greetingColumn.setPrefWidth(300.0);
//		TableColumn<Order, String> branchColumn = new TableColumn<Order, String>("Branch");
//		branchColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("branchName"));
		TableColumn<Order, Instant> deliveryDateColumn = new TableColumn<Order, Instant>("Delivery Date");
		deliveryDateColumn.setCellValueFactory(new PropertyValueFactory<Order, Instant>("deliveryDate"));
		TableColumn<Order, Instant> orderDateColumn = new TableColumn<Order, Instant>("Order Date");
		orderDateColumn.setCellValueFactory(new PropertyValueFactory<Order, Instant>("orderDate"));

		ordersTable.getColumns().add(orderNumberColumn);
		ordersTable.getColumns().add(priceColumn);
		ordersTable.getColumns().add(colorColumn);
		ordersTable.getColumns().add(detailsColumn);
		ordersTable.getColumns().add(greetingColumn);
//		ordersTable.getColumns().add(branchColumn);
		ordersTable.getColumns().add(deliveryDateColumn);
		ordersTable.getColumns().add(orderDateColumn);

//		TableColumn<Order, Order> editButtonColumn = new TableColumn<Order, Order>("Edit");
//		editButtonColumn.setCellValueFactory(param -> new TableCell<Order, Order>() {
//		    private final Button deleteButton = new Button("Unfriend");
//
//		    @Override
//		    protected void updateItem(Order person, boolean empty) {
//		        super.updateItem(person, empty);
//
//		        if (person == null) {
//		            setGraphic(null);
//		            return;
//		        }
//
//		        setGraphic(deleteButton);
//		        deleteButton.setOnAction(
//		            event -> getTableView().getItems().remove(person)
//		        );
//		    }
//		});
	}

}