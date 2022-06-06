package gui.orders.openOrders;

import java.net.URL;
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.ResourceBundle;

import controllers.OrderController;
import entities.other.Location;
import entities.users.Order;
import enums.OrderStatus;
import gui.guimanagement.GUIController;
import gui.guimanagement.SceneManager;
import javafx.collections.ObservableList;
=======
import java.util.ResourceBundle;

import entities.users.Order;
import gui.guimanagement.GUIController;
>>>>>>> e44010510e34dd0f52bcd618d24666065585e51a
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import utility.IResponse;

public class OpenOrdersList extends GUIController{
<<<<<<< HEAD

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
=======

    @FXML
    private TableView<Order> discountsTable;

    @FXML
    private TableColumn<Order, String> addressColumn;

    @FXML
    private TableColumn<Order, String> orderDateColumn;

    @FXML
    private TableColumn<Order, Integer> numOfItemsColumn;

    @FXML
    private TableColumn<Order, Float> priceColumn;

    @FXML
    private TableColumn<Order, Order> acceptColumn;

    @FXML
>>>>>>> e44010510e34dd0f52bcd618d24666065585e51a
    private TableColumn<Order, Order> cancelColumn;

    @FXML
    void onBackBtn(ActionEvent event) {
        SceneManager.loadPreviousPage();
    }

<<<<<<< HEAD

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
	
=======
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		initializeTableColumns();
>>>>>>> e44010510e34dd0f52bcd618d24666065585e51a
	}

	private void initializeTableColumns()
	{
<<<<<<< HEAD
		addressColumn.setCellValueFactory(new PropertyValueFactory<Order, Location>("OrderLocation"));
		orderDateColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("FormattedOrderDate"));
		numOfItemsColumn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("OrderQuantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Order, Float>("TotalCost"));
		acceptColumn.setCellValueFactory(new PropertyValueFactory<Order, Order>("details"));
		cancelColumn.setCellValueFactory(new PropertyValueFactory<Order, Order>("basePrice"));
=======
>>>>>>> e44010510e34dd0f52bcd618d24666065585e51a
	}

}
