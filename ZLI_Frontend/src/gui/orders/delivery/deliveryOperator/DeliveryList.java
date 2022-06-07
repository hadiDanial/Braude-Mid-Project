package gui.orders.delivery.deliveryOperator;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.junit.runner.Request;

import controllers.BranchController;
import controllers.ClientController;
import controllers.OrderController;
import entities.other.Branch;
import entities.other.Location;
import entities.users.Delivery;
import entities.users.Order;
import enums.OrderStatus;
import gui.guimanagement.GUIController;
import gui.guimanagement.SceneManager;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import requests.EntityRequestWithId;
import requests.RequestType;
import utility.IResponse;

public class DeliveryList extends GUIController{

	public static ObservableList<Delivery> deliveryList = FXCollections.observableArrayList();
    public static ObservableList<Branch> branchList = FXCollections.observableArrayList();
    private OrderController orderController;
    private BranchController branchController;
    private ClientController clientController;
    ArrayList<Branch> branchArray;

    @FXML
    private TableView<Delivery> deliveryTable;

    @FXML
    private ChoiceBox<Branch> branchDropDown;

    @FXML
    private TableColumn<Delivery,Integer > orderIDColumn;

    @FXML
    private TableColumn<Delivery, String> recipientColumn;

    @FXML
    private TableColumn<Delivery, Location> addressColumn;

    @FXML
    private TableColumn<Delivery, String> deliveryDateColumn;

    @FXML
    private TableColumn<Delivery, Integer> numOfItemsColumn;

    @FXML
    private TableColumn<Delivery, Float> preiceColumn;

    @FXML
    private TableColumn<Delivery, Delivery> confirmColumn;

    @FXML
    void onBackBtn(ActionEvent event) {
        SceneManager.loadPreviousPage();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTableColumn();
        orderController = OrderController.getInstance();
        branchController = BranchController.getInstance();
        clientController = ClientController.getInstance();
        branchController.getAllBranches(new IResponse<ArrayList<Branch>>() {
            @Override
            public void executeAfterResponse(Object message) {
            branchArray = (ArrayList<Branch>) message;
            branchList.setAll(branchArray);
            }
        });
        branchDropDown.setItems(branchList);
        branchDropDown.valueProperty().addListener((ChangeListener<Branch>) (observable, oldValue, newValue) -> {
            orderController.getDeliveryByBranch(new IResponse<Order>()
            {
                @Override
                public void executeAfterResponse(Object message)
                {
                    deliveryList = (ObservableList<Delivery>) message;
                }
            },(newValue).getBranchId());
        });
	}
    private void initializeTableColumn()
    {
        orderIDColumn.setCellValueFactory(new PropertyValueFactory<Delivery,Integer >("Id"));
        recipientColumn.setCellValueFactory(new PropertyValueFactory<Delivery,String >("RecipientName"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Delivery,Location >("Location"));
        deliveryDateColumn.setCellValueFactory(new PropertyValueFactory<Delivery,String >("FormattedDeliveryDate"));
        numOfItemsColumn.setCellValueFactory(new PropertyValueFactory<Delivery,Integer >("ItemsCount")); 
        preiceColumn.setCellValueFactory(new PropertyValueFactory<Delivery,Float >("Price"));

        confirmColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<Delivery>(param.getValue()));
        confirmColumn.setCellFactory(param -> new TableCell<Delivery, Delivery>()
		{
			private final Button acceptButton = new Button("Mark Delivered");

			@Override
			protected void updateItem(Delivery delivery, boolean empty)
			{
				super.updateItem(delivery, empty);

				if (delivery == null)
				{
					setGraphic(null);
					return;
				}
				setGraphic(acceptButton);
				acceptButton.setOnAction(event -> {
					updateStatus(delivery);
				});
			}

			
		});
    }
    private void updateStatus(Delivery delivery)
	{
		IResponse<Boolean> response = new IResponse<Boolean>() {

			@Override
			public void executeAfterResponse(Object message)
			{
				if((Boolean) message)
				{
					deliveryList.remove(delivery);
				}
			}};
//			orderController.updateOrderStatus(response, delivery.getOrderId(), status);
	}
}
