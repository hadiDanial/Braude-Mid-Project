package gui.newPages.branchEmployee.surveys;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;

public class SurveysList {

    @FXML
    private TreeTableView<?> discountsTable;

    @FXML
    private TreeTableColumn<?, ?> surveyIDColumn;

    @FXML
    private TreeTableColumn<?, ?> startDateColumn;

    @FXML
    private TreeTableColumn<?, ?> endDateColumn;

    @FXML
    private TreeTableColumn<?, ?> numOfSurveyedCustomersColumn;

    @FXML
    private TreeTableColumn<?, ?> resultColumn;

    @FXML
    void onBackBtn(ActionEvent event) {

    }

}
