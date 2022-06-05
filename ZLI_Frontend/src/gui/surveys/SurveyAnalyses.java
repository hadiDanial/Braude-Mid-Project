package gui.surveys;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;

public class SurveyAnalyses {

    @FXML
    private TreeTableView<?> discountsTable;

    @FXML
    private TreeTableColumn<?, ?> complaintIDColumn;

    @FXML
    private TreeTableColumn<?, ?> complaintIDColumn1;

    @FXML
    private TreeTableColumn<?, ?> customerIDColumn;

    @FXML
    private TreeTableColumn<?, ?> customerNameColumn;

    @FXML
    private TreeTableColumn<?, ?> dateOpenedColumn;

    @FXML
    private TreeTableColumn<?, ?> timeSinceOpeningColumn;

    @FXML
    private TreeTableColumn<?, ?> timeSinceOpeningColumn1;

    @FXML
    private TreeTableColumn<?, ?> timeSinceOpeningColumn2;

    @FXML
    private TreeTableColumn<?, ?> reviewColumn1;

    @FXML
    void onBackBtn(ActionEvent event) {

    }

}
