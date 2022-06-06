package gui.newPages.customerService.complaints;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

public class OpenedComplaints {

    @FXML
    private TableColumn<?, ?> complaintIDColumn;

    @FXML
    private TableColumn<?, ?> customerIDColumn;

    @FXML
    private TableColumn<?, ?> customerNameColumn;

    @FXML
    private TableColumn<?, ?> dateOpenedColumn;

    @FXML
    private TableColumn<?, ?> timeSinceOpeningColumn;

    @FXML
    private TableColumn<?, ?> reviewColumn;

    @FXML
    void onAddBtn(ActionEvent event) {

    }

    @FXML
    void onBackBtn(ActionEvent event) {

    }

}
