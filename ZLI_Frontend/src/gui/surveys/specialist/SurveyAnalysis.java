package gui.surveys.specialist;

import java.net.URL;
import java.util.ResourceBundle;

import gui.guimanagement.GUIController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class SurveyAnalysis extends GUIController{

    @FXML
    private TableView<?> discountsTable;

    @FXML
    private TableColumn<?, ?> complaintIDColumn;

    @FXML
    private TableColumn<?, ?> complaintIDColumn1;

    @FXML
    private TableColumn<?, ?> customerIDColumn;

    @FXML
    private TableColumn<?, ?> customerNameColumn;

    @FXML
    private TableColumn<?, ?> dateOpenedColumn;

    @FXML
    private TableColumn<?, ?> timeSinceOpeningColumn;

    @FXML
    private TableColumn<?, ?> timeSinceOpeningColumn1;

    @FXML
    private TableColumn<?, ?> timeSinceOpeningColumn2;

    @FXML
    private TableColumn<?, ?> reviewColumn1;

    @FXML
    void onBackBtn(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
	}

}
