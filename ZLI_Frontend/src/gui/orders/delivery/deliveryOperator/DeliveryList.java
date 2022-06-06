package gui.orders.delivery.deliveryOperator;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controllers.BranchController;
import controllers.OrderController;
import entities.other.Branch;
import entities.other.Location;
import entities.users.Delivery;
import gui.guimanagement.GUIController;
import gui.guimanagement.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import utility.IResponse;

public class DeliveryList extends GUIController{

	public static ObservableList<Delivery> deliveryList = FXCollections.observableArrayList();
    public static ObservableList<Branch> branchList = FXCollections.observableArrayList();
    OrderController orderController;
    BranchController branchController;

    @FXML
    private TableView<Delivery> deliveryTable;

    @FXML
    private ChoiceBox branchDropDown;

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
    private TableColumn<Delivery,Boolean > confirmColumn;

    @FXML
    void onBackBtn(ActionEvent event) {
        SceneManager.loadPreviousPage();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTableColumn();
        orderController = OrderController.getInstance();
        branchController = BranchController.getInstance();
        branchController.getAllBranches(new IResponse<ArrayList<Branch>>() {

            @Override
            public void executeAfterResponse(Object message) {
            }
        });
        branchDropDown.getItems().addAll(branchList);

		orderController.(new IResponse<ArrayList<delivery>>()
		{
			
			@Override
			public void executeAfterResponse(Object message)
			{
				Platform.runLater(new Runnable()
				{
					
					@Override
					public void run()
					{
						VBox scrollPaneContent = new VBox();
						if(message == null)
							SceneManager.displayErrorMessage("Failed to load Deliveries!");
						else
						{
							deliveryList.setAll((ArrayList<Delivery>) message);
                            deliveryTable.setItems(deliveryList);						
						}
					}
				});
			}
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
        confirmColumn.setCellValueFactory(new PropertyValueFactory<Delivery,Boolean >("DiscountValue"));  
    }

}
