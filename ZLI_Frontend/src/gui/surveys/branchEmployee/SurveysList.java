package gui.surveys.branchEmployee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class SurveysList {

    @FXML
    private TableView<?> discountsTable;

    @FXML
    private TableColumn<?, ?> surveyIDColumn;

    @FXML
    private TableColumn<?, ?> startDateColumn;

    @FXML
    private TableColumn<?, ?> endDateColumn;

    @FXML
    private TableColumn<?, ?> numOfSurveyedCustomersColumn;

    @FXML
    private TableColumn<?, ?> resultColumn;

    @FXML
    void onBackBtn(ActionEvent event) {

    }

}
