package gui.surveys.specialist;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controllers.SurveyController;
import entities.surveys.Survey;
import gui.guimanagement.ButtonAnimator;
import gui.guimanagement.FormController;
import gui.guimanagement.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import utility.IResponse;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AddAnalysis extends FormController {

    private SurveyController surveyController;
    public static ObservableList<Survey> surveyList = FXCollections.observableArrayList();
    private Survey survey;
    @FXML
    private TableView<Survey> surveyTable;

    @FXML
    private Label surveyIDLabel;

    @FXML
    private Label numSurveyedLabel;

    @FXML
    private TableColumn<Survey,Integer> questionNumberColumn;

    @FXML
    private TableColumn<Survey,String> questionTextColumn;

    @FXML
    private TableColumn<Survey,Integer> questionScoreColumn;

    @FXML
    private TextArea surveyAnalysisAria;

    @FXML 
    private Button saveBtn;

    @FXML 
    private Button backBtn;

    @FXML
    void onSaveBtn(ActionEvent event) {
        initializeTableColumns();
    }

    @FXML
    void onBackBtn(ActionEvent event) {
        SceneManager.loadPreviousPage();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
        ButtonAnimator.addButtonAnimations(addBtn,backBtn);
        surveyController=SurveyController.getInstance();
        surveyTable.setItems(surveyList);
        surveyAnalysisAria.setText(""+survey.getAnalysisResults());               
        surveyController.getAllSurvey(new IResponse<ArrayList<Survey>>() {

            @Override
            public void executeAfterResponse(Object message) {
                if (message == null)
                SceneManager.displayErrorMessage("Failed to load questions!");
                else 
                    surveyList.setAll((ArrayList<Survey>)message);
            }
            
        });
	}

    private void initializeTableColumns() {
        questionNumberColumn.setCellValueFactory(new PropertyValueFactory<Survey,Integer>("surveyId"));
        questionTextColumn.setCellValueFactory(new PropertyValueFactory<Survey,String>("questions"));
        questionScoreColumn.setCellValueFactory(new PropertyValueFactory<Survey,Integer>("score"));

    }

}
