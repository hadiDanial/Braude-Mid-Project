package gui.discountManagement;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controllers.DiscountController;
import entities.discounts.Discount;
import entities.products.CatalogItem;
import gui.guimanagement.GUIController;
import gui.guimanagement.GUIPages;
import gui.guimanagement.SceneManager;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import utility.IResponse;

public class DiscountManagement extends GUIController {

    private DiscountController discountController;
	public static ObservableList<Discount> discountsList = FXCollections.observableArrayList();


    @FXML
    private TableView<?> discountsTable;

    @FXML
    private TableColumn<?, ?> startDateColumn;

    @FXML
    private TableColumn<?, ?> endDateColumn;

    @FXML
    private TableColumn<?, ?> numProductsColumn;

    @FXML
    private TableColumn<?, ?> discountAmountColumn;

    @FXML
    private TableColumn<?, ?> numSoldColumn;

    @FXML
    void onAddBtn(ActionEvent event) {
        SceneManager.loadNewScene(GUIPages.NewDiscount, true);
    }

    @FXML
    void onBackBtn(ActionEvent event) {
        SceneManager.loadPreviousPage();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
	{
        discountController = DiscountController.getInstance();
        initializeTableColumn();
		discountController.getAllDiscounts(new IResponse<ArrayList<Discount>>()
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
							SceneManager.displayErrorMessage("Failed to load Discounts!");
						else
						{
							discountsList.setAll((ArrayList<Discount>) message);
                            discountsTable.setRoot();						
						}
					}
				});
			}
		});
	}

    private void initializeTableColumn()
    {
	 	startDateColumn.setCellValueFactory(new PropertyValueFactory<Discount,String >("FormattedDiscountStartDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<Discount,String >("FormattedDiscountEndDate"));
        numProductsColumn.setCellValueFactory(new PropertyValueFactory<Discount,Float >("ProductsSize"));
        discountAmountColumn.setCellValueFactory(new PropertyValueFactory<Discount,Float >("DiscountValue"));
        numSoldColumn.setCellValueFactory(new PropertyValueFactory<Discount,Float >("DiscountValue"));  
    }

}
