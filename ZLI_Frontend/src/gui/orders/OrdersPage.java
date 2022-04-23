package gui.orders;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.ClientProperties;
import controllers.OrderController;
import entities.users.Order;
import enums.Color;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class OrdersPage implements Initializable
{
	private OrderController orderController;
	private ArrayList<Order> orders;
	private double tableWidth;
	
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
		parent.setPrefHeight(ClientProperties.getClientHeight());
		parent.setPrefWidth(ClientProperties.getClientWidth());
		generateTableColumns();
		setTableSettings();
		orderController.getAllOrders(arr -> {
			orders = (ArrayList<Order>) arr;
			ordersTable.getItems().addAll(orders);
		});
	}

	/**
	 * Sets the table settings - width, anchors
	 */
	private void setTableSettings()
	{
		
		StackPane pane = new StackPane();
		pane.getChildren().add(ordersTable);
		parent.getChildren().add(pane);
		ordersTable.setPrefWidth(tableWidth);
		ordersTable.setMinWidth(tableWidth * 0.5);
		double left = (parent.getPrefWidth() - tableWidth) / 2;
		double right = ClientProperties.getClientWidth() - ((parent.getPrefWidth() - tableWidth) / 2 + tableWidth);
		System.out.println(parent.getPrefWidth() + " " + left + " " + right);
		AnchorPane.setLeftAnchor(pane, left);
		AnchorPane.setRightAnchor(pane, right);
		AnchorPane.setTopAnchor(pane, 100.0);
		AnchorPane.setLeftAnchor(ordersTable, left);
		AnchorPane.setRightAnchor(ordersTable, right);
		AnchorPane.setTopAnchor(ordersTable, 100.0);
		pane.setAlignment(Pos.CENTER);
	}

	/**
	 * Generates the following table columns:<br>
	 * Order number, price, color, details, greeting, branch, delivery date, order date, and edit order button.
	 */
	private void generateTableColumns()
	{
		ordersTable = new TableView<>();
		tableWidth = parent.getPrefWidth() * 7 / 8;
		double width = 0.997 * tableWidth;
		TableColumn<Order, Integer> orderNumberColumn = new TableColumn<>("#");
		orderNumberColumn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("orderId"));
		orderNumberColumn.setPrefWidth(width * 0.05);
		orderNumberColumn.setStyle( "-fx-alignment: CENTER;");
		TableColumn<Order, Float> priceColumn = new TableColumn<>("Price");
		priceColumn.setCellValueFactory(new PropertyValueFactory<Order, Float>("totalCost"));
		priceColumn.setPrefWidth(width * 0.05);
		TableColumn<Order, Color> colorColumn = new TableColumn<>("Color");
		colorColumn.setCellValueFactory(new PropertyValueFactory<Order, Color>("color"));
		colorColumn.setPrefWidth(width * 0.1);
		TableColumn<Order, String> detailsColumn = new TableColumn<>("Details");
		detailsColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("orderDetails"));
		detailsColumn.setPrefWidth(width * 0.2);
		TableColumn<Order, String> greetingColumn = new TableColumn<>("Greeting");
		greetingColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("greetingCard"));
		greetingColumn.setPrefWidth(width * 0.2);
		TableColumn<Order, String> branchColumn = new TableColumn<>("Branch");
		branchColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("branchName"));
		branchColumn.setPrefWidth(width * 0.1);
		TableColumn<Order, String> deliveryDateColumn = new TableColumn<>("Delivery Date");
		deliveryDateColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("formattedDeliveryDate"));
		deliveryDateColumn.setPrefWidth(width * 0.1);
		TableColumn<Order, String> orderDateColumn = new TableColumn<>("Order Date");
		orderDateColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("formattedOrderDate"));
		orderDateColumn.setPrefWidth(width * 0.1);

		// Edit button column
		TableColumn<Order, Order> editBtnCol = new TableColumn<>("Edit");
		editBtnCol.setCellValueFactory(
		    param -> new ReadOnlyObjectWrapper<>(param.getValue())
		);
		editBtnCol.setCellFactory(param -> new TableCell<Order, Order>() {
		    private final Button editButton = new Button("Edit");
		    
		    @Override
		    protected void updateItem(Order order, boolean empty) {
		        super.updateItem(order, empty);

		        if (order == null) {
		            setGraphic(null);
		            return;
		        }
		        editButton.setPrefWidth(width * 0.09);
		        setGraphic(editButton);
		        editButton.setOnAction(
		            event ->
		            {
		            	try
						{
		            		// Load Update Order Page and set the order to edit
							FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/orders/OrderUpdatePage.fxml"));
							Pane pane = loader.load();
							UpdateOrder updatePage = loader.getController();
							updatePage.setOrderToUpdate(order);
							parent.getChildren().clear();
							parent.getChildren().add(pane);
						} catch (IOException e)
						{
							e.printStackTrace();
						}

		            }
		        );
		    }
		});
		editBtnCol.setPrefWidth(width * 0.1);

		// Add columns to table
		ordersTable.getColumns().add(orderNumberColumn);
		ordersTable.getColumns().add(priceColumn);
		ordersTable.getColumns().add(colorColumn);
		ordersTable.getColumns().add(detailsColumn);
		ordersTable.getColumns().add(greetingColumn);
		ordersTable.getColumns().add(branchColumn);
		ordersTable.getColumns().add(deliveryDateColumn);
		ordersTable.getColumns().add(orderDateColumn);
		ordersTable.getColumns().add(editBtnCol);
	}
}