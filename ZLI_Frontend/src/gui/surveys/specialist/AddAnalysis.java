package gui.surveys.specialist;

import java.net.URL;
import java.util.ResourceBundle;

import gui.guimanagement.FormController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TableColumn;

public class AddAnalysis extends FormController {

    @FXML
    private Label surveyIDLabel;

    @FXML
    private Label numSurveyedLabel;

    @FXML
    private TableColumn<?, ?> questionNumberColumn;

    @FXML
    private TableColumn<?, ?> questionTextColumn;

    @FXML
    private TableColumn<?, ?> questionScoreColumn;

    @FXML
    private TextArea surveyAnalysisAria;

    @FXML
    void onAddBtn(ActionEvent event) {

    }

    @FXML
    void onBackBtn(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
	}

}
