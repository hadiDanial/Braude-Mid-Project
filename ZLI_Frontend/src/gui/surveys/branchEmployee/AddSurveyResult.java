package gui.surveys.branchEmployee;

import java.net.URL;
import java.util.ResourceBundle;

import controllers.SurveyController;
import entities.surveys.Survey;
import gui.guimanagement.GUIController;
import gui.guimanagement.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AddSurveyResult extends GUIController{

    SurveyController surveyController;
    String [] questions = new String[6];
    @FXML
    private Label surveyNameLabel;

    @FXML
    private Label surveyIDLabel;

    @FXML
    private Label q1Label;

    @FXML
    private TextField q1AnswerField;

    @FXML
    private Label q1Label1;

    @FXML
    private TextField q2AnswerField;

    @FXML
    private Label q1Label2;

    @FXML
    private TextField q3AnswerField;

    @FXML
    private Label q1Label11;

    @FXML
    private TextField q4AnswerField;

    @FXML
    private Label q1Label21;

    @FXML
    private TextField q5AnswerField;

    @FXML
    private Label q1Label111;

    @FXML
    private TextField q6AnswerField;

    @FXML
    void onAddAnotherBtn(ActionEvent event) {  

    }

    @FXML
    void onBackBtn(ActionEvent event) {
        SceneManager.loadPreviousPage();
    }

    @FXML
    void onFinishBtn(ActionEvent event) {
        SceneManager.loadPreviousPage();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        surveyController=SurveyController.getInstance();
        initializeTableColumns();
	}

    private void initializeTableColumns() {
       /* surveyNameLabel.setCellValueFactory(new PropertyValueFactory<Survey,String>("surveyId"));
        surveyIDLabel.setCellValueFactory(new PropertyValueFactory<Survey,Integer>("questions"));*/
    }

}
