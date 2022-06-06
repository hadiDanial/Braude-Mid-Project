package gui.surveys.branchEmployee;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controllers.SurveyController;
import entities.surveys.Survey;
import gui.guimanagement.GUIController;
import gui.guimanagement.SceneManager;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class SurveysList extends GUIController{

    public static ObservableList<Survey> surveyList = FXCollections.observableArrayList();
    SurveyController surveyController;

    @FXML
    private TableView<Survey> surverysTable;

    @FXML
    private TableColumn<Survey, Integer> surveyIDColumn;

    @FXML
    private TableColumn<Survey, String> startDateColumn;

    @FXML
    private TableColumn<Survey, String> endDateColumn;

    @FXML
    private TableColumn<Survey, Integer> numOfSurveyedCustomersColumn;

    @FXML
    private TableColumn<Survey, Button> resultColumn;

    @FXML
    void onBackBtn(ActionEvent event) {
        SceneManager.loadPreviousPage();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {    
        surveyController = SurveyController.getInstance();
        initializeTableColumn();
		surveyController.getAllDiscounts(new IResponse<ArrayList<Survey>>()
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
							surveyList.setAll((ArrayList<Survey>) message);
                            surverysTable.setItems(surveyList);						
						}
					}
				});
			}
		});
	}

    private void initializeTableColumn()
    {
        surveyIDColumn.setCellValueFactory(new PropertyValueFactory<Survey,Integer >("FormattedDiscountStartDate"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<Survey,String >("FormattedDiscountEndDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<Survey,String >("ProductsSize"));
        numOfSurveyedCustomersColumn.setCellValueFactory(new PropertyValueFactory<Survey,Integer >("DiscountValue"));
        resultColumn.setCellValueFactory(new PropertyValueFactory<Survey,Button >("DiscountValue"));  
    }

}
