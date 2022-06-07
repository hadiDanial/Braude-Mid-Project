package gui.surveys.specialist;

import java.net.URL;
import java.time.Instant;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controllers.SurveyController;
import entities.surveys.Survey;
import entities.users.Order;
import enums.OrderStatus;
import gui.guimanagement.ButtonAnimator;
import gui.guimanagement.GUIController;
import gui.guimanagement.GUIPages;
import gui.guimanagement.SceneManager;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
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
    private TableColumn<Survey,Survey> addAnalysisColumn;
    @FXML private Button backBtn;
    
    @FXML
    void onBackBtn(ActionEvent event) {
        SceneManager.loadPreviousPage();
    }

	
    /** 
     * @param location
     * @param resources
     */
    @Override
	public void initialize(URL location, ResourceBundle resources)
	{
        surveyController=SurveyController.getInstance();
        initializeTableColumn();
        ButtonAnimator.addButtonAnimations(backBtn);
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
        addAnalysisColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<Survey>(param.getValue()));
        addAnalysisColumn.setCellFactory(param -> new TableCell<Survey, Survey>()
		{
			private final Button acceptButton = new Button("Add Analysis");

			@Override
			protected void updateItem(Survey survey, boolean empty)
			{
				super.updateItem(survey, empty);

				if (survey == null)
				{
					setGraphic(null);
					return;
				}
				setGraphic(acceptButton);
				acceptButton.setOnAction(event -> {
					SceneManager.loadModalWindow(GUIPages.ADD_SURVEY_ANALYSIS, survey);
				});
			}

			
		});
    }

}
