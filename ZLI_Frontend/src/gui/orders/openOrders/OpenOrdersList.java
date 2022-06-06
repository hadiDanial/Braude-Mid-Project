package gui.orders.openOrders;

import java.net.URL;
import java.util.ResourceBundle;

import entities.users.Order;
import gui.guimanagement.GUIController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class OpenOrdersList extends GUIController{

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
    private TableColumn<Order, Order> cancelColumn;

    @FXML
    void onBackBtn(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		initializeTableColumns();
	}

	private void initializeTableColumns()
	{
	}

}
