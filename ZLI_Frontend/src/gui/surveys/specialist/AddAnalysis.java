package gui.surveys.specialist;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.junit.runner.Request;

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
import utility.FileManager;
import utility.IResponse;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AddAnalysis extends FormController
{

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
	private TableColumn<Survey, Integer> questionNumberColumn;

	@FXML
	private TableColumn<Survey, String> questionTextColumn;

	@FXML
	private TableColumn<Survey, Integer> questionScoreColumn;

	@FXML
	private TextArea surveyAnalysisAria;

	@FXML
	private Button downloadBtn;

	@FXML
	private Button chooseFile;

	@FXML
	private Button backBtn;

	@FXML
	private Button saveBtn;

	private byte[] PDFFile;

	@FXML
	void OnChooseFile(ActionEvent event)
	{
		PDFFile = FileManager.choosePDFFile();
	}

	@FXML
	void onSaveBtn(ActionEvent event)
	{
		surveyController.addSurveyAnalysis(survey, PDFFile);
	}

	@FXML
	void onBackBtn(ActionEvent event)
	{
		SceneManager.loadPreviousPage();
	}

	
    /** 
     * @param location
     * @param resources
     */
    @Override
	public void initialize(URL location, ResourceBundle resources)
	{
//		ButtonAnimator.addButtonAnimations(backBtn, downloadBtn, saveBtn, chooseFile);
		initializeTableColumns();
		surveyController = SurveyController.getInstance();
		surveyTable.setItems(surveyList);
		surveyController.getAllSurvey(new IResponse<ArrayList<Survey>>()
		{

			@Override
			public void executeAfterResponse(Object message)
			{
				if (message == null)
					SceneManager.displayErrorMessage("Failed to load questions!");
				else
					surveyList.setAll((ArrayList<Survey>) message);
			}

		});
	}

	private void initializeTableColumns()
	{
		questionNumberColumn.setCellValueFactory(new PropertyValueFactory<Survey, Integer>("surveyId"));
		questionTextColumn.setCellValueFactory(new PropertyValueFactory<Survey, String>("questions"));
		questionScoreColumn.setCellValueFactory(new PropertyValueFactory<Survey, Integer>("score"));
	}

	@Override
	public void setData(Object data)
	{
		this.survey = (Survey) data;
	}
}
