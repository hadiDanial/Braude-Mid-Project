package gui.orders.openOrders;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controllers.OrderController;
import entities.users.Order;
import enums.OrderStatus;
import gui.guimanagement.GUIController;
import gui.guimanagement.SceneManager;
import javafx.collections.ObservableList;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import utility.IResponse;

public class OpenOrdersList extends GUIController
{
	private OrderController orderController;
	private ObservableList<Order> ordersList = FXCollections.observableArrayList();
	private ArrayList<Order> orders;
	
	@FXML
	private TableView<Order> ordersTable;

	@FXML
	private TableColumn<Order, String> addressColumn;

	@FXML
	private TableColumn<Order, String> orderDateColumn;
	
	@FXML
	private TableColumn<Order, String> greetingColumn;

	@FXML
	private TableColumn<Order, Integer> numOfItemsColumn;

	@FXML
	private TableColumn<Order, Float> priceColumn;

	@FXML
	private TableColumn<Order, Order> acceptColumn;

	@FXML
	private TableColumn<Order, Order> cancelColumn;


    
	/** 
	 * @param event
	 */
	@FXML
    void onBackBtn(ActionEvent event) {
        SceneManager.loadPreviousPage();
    }
	@FXML
	private TableColumn<Order, Integer> orderIdColumn;

	@FXML
	private TableColumn<Order, String> orderDetailsColumn;

	@FXML
	private Button backBtn;



    
	/** 
	 * @param location
	 * @param resources
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		orderController = OrderController.getInstance();
		orderController.getAllOrdersByStatus(new IResponse<ArrayList<Order>>()
		{

			@Override
			public void executeAfterResponse(Object message)
			{
				if (message == null)
					SceneManager.displayErrorMessage("Failed to load products!");
				else
				{
					orders = ((ArrayList<Order>) message);
					ordersList.setAll(orders);
				}
			}
		},OrderStatus.Pending);
	

		ordersTable.setItems(ordersList);
		initializeTableColumns();
	}

	private void initializeTableColumns()
	{
		orderIdColumn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("orderId"));
//		orderDetailsColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("orderDetails"));
//		addressColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("address"));
		greetingColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("GreetingCard"));
		orderDateColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("formattedOrderDate"));
//		numOfItemsColumn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("numProducts"));
//		priceColumn.setCellValueFactory(new PropertyValueFactory<Order, Float>("totalCost"));

		acceptColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<Order>(param.getValue()));
		acceptColumn.setCellFactory(param -> new TableCell<Order, Order>()
		{
			private final Button acceptButton = new Button("Accept");

			@Override
			protected void updateItem(Order order, boolean empty)
			{
				super.updateItem(order, empty);

				if (order == null)
				{
					setGraphic(null);
					return;
				}
				setGraphic(acceptButton);
				acceptButton.setOnAction(event -> {
					updateStatus(order, OrderStatus.Accepted);
				});
			}

			
		});
		cancelColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<Order>(param.getValue()));
		cancelColumn.setCellFactory(param -> new TableCell<Order, Order>()
		{
			private final Button denyButton = new Button("Deny");
			
			@Override
			protected void updateItem(Order order, boolean empty)
			{
				super.updateItem(order, empty);
				
				if (order == null)
				{
					setGraphic(null);
					return;
				}
				setGraphic(denyButton );
				denyButton.setOnAction(event -> {
					updateStatus(order, OrderStatus.Canceled);
				});
			}
		});
	}
	
	
	/** 
	 * @param order
	 * @param status
	 */
	private void updateStatus(Order order, OrderStatus status)
	{
		IResponse<Boolean> response = new IResponse<Boolean>() {

			@Override
			public void executeAfterResponse(Object message)
			{
				if((Boolean) message)
				{
					ordersList.remove(order);
				}
			}};
			orderController.updateOrderStatus(response, order.getOrderId(), status);
	}
}
