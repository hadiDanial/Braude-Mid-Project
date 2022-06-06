package gui.branchManager.customers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;

public class CustomersList {

    @FXML
    private TreeTableView<?> discountsTable;

    @FXML
    private TreeTableColumn<?, ?> addressColumn;

    @FXML
    private TreeTableColumn<?, ?> orderDateColumn;

    @FXML
    private TreeTableColumn<?, ?> numOfItemsColumn;

    @FXML
    private TreeTableColumn<?, ?> priceColumn;

    @FXML
    private TreeTableColumn<?, ?> acceptColumn;

    @FXML
    void onAddBtn(ActionEvent event) {

    }

    @FXML
    void onBackBtn(ActionEvent event) {

    }

}
