package gui.surveys.specialist;

import java.net.URL;
import java.time.Instant;
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
import utility.IResponse;

public class SurveyAnalysis extends GUIController{
    private SurveyController surveyController;
    public static ObservableList<Survey> surveyList = FXCollections.observableArrayList();
   
    @FXML
    private TableView<Survey> surveyTable;

    @FXML
    private TableColumn<Survey,Integer> surveyIDColumn;

    @FXML
    private TableColumn<Survey,Integer> surveydColumn;

    @FXML
    private TableColumn<Survey,String> startDateColumn;

    @FXML
    private TableColumn<Survey,String> endDateColumn;

    @FXML
    private TableColumn<Survey,Button> addAnalysisColumn;

    @FXML
    void onBackBtn(ActionEvent event) {
        SceneManager.loadPreviousPage();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
        surveyController=SurveyController.getInstance();
        initializeTableColumn();
        surveyController.getAllSurveyAnalysis(new IResponse<ArrayList<Survey>>() {

            @Override
            public void executeAfterResponse(Object message) {
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        if(message==null)
                            SceneManager.displayErrorMessage("Failed to load survey analysis!");       
                        else
                        {
                            surveyList.setAll((ArrayList<Survey>) message);
                            surveyTable.setItems(surveyList);	
                        }                 
                    }
                    
                });
            }
            
        });
	}

    private void initializeTableColumn()
    {
        surveyIDColumn.setCellValueFactory(new PropertyValueFactory<Survey,Integer>("surveyId"));
        surveydColumn.setCellValueFactory(new PropertyValueFactory<Survey,Integer>("NumOfCustomers"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<Survey,String>("FormattedStartDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<Survey,String>("FormattedEndDate"));
        addAnalysisColumn.setCellValueFactory(new PropertyValueFactory<Survey,Button>("AnalysisResults"));
    }

}
