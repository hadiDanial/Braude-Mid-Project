package gui.customerService.complaints;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;

public class OpenedComplaints {

    @FXML
    private TreeTableView<?> discountsTable;

    @FXML
    private TreeTableColumn<?, ?> complaintIDColumn;

    @FXML
    private TreeTableColumn<?, ?> customerIDColumn;

    @FXML
    private TreeTableColumn<?, ?> customerNameColumn;

    @FXML
    private TreeTableColumn<?, ?> dateOpenedColumn;

    @FXML
    private TreeTableColumn<?, ?> timeSinceOpeningColumn;

    @FXML
    private TreeTableColumn<?, ?> reviewColumn;

    @FXML
    void onAddBtn(ActionEvent event) {

    }

    @FXML
    void onBackBtn(ActionEvent event) {

    }

}
