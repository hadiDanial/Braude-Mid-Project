package gui.orders.openOrders;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controllers.OrderController;
import entities.other.Location;
import entities.users.Order;
import enums.OrderStatus;
import gui.guimanagement.GUIController;
import gui.guimanagement.SceneManager;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import utility.IResponse;

public class OpenOrdersList extends GUIController{

    OrderController orderController;
	private ObservableList<Order> ordersList;
    
    @FXML
    private TableView<Order> pendingOrdersTable;

    @FXML
    private TableColumn<Order, Location> addressColumn;

    @FXML
    private TableColumn<Order, String> orderDateColumn;

    @FXML
    private TableColumn<Order, Integer> numOfItemsColumn;

    @FXML
    private TableColumn<Order, Float> priceColumn;

    @FXML
    private TableColumn<Order, Order> acceptColumn;

    @FXML
    private TableColumn<Order, Order> cancelColumn;

    @FXML
    void onBackBtn(ActionEvent event) {
        SceneManager.loadPreviousPage();
    }


    @Override
	public void initialize(URL location, ResourceBundle resources)
	{
		orderController = OrderController.getInstance();
		initializeTableColumns();
		pendingOrdersTable.setItems(ordersList);
		orderController.getAllOrdersByStatus(new IResponse<ArrayList<Order>>()
		{

			@Override
			public void executeAfterResponse(Object message)
			{
				if (message == null)
					SceneManager.displayErrorMessage("Failed to load products!");
				else
				{
					ordersList.setAll((ArrayList<Order>) message);
				}
			}
		},OrderStatus.Pending);
	
	}

	private void initializeTableColumns()
	{
		addressColumn.setCellValueFactory(new PropertyValueFactory<Order, Location>("OrderLocation"));
		orderDateColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("FormattedOrderDate"));
		numOfItemsColumn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("OrderQuantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Order, Float>("TotalCost"));
		acceptColumn.setCellValueFactory(new PropertyValueFactory<Order, Order>("details"));
		cancelColumn.setCellValueFactory(new PropertyValueFactory<Order, Order>("basePrice"));
	}

}
