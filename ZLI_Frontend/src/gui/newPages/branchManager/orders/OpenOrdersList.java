package gui.branchManager.orders;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;

public class OpenOrdersList {

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
    private TreeTableColumn<?, ?> cancelColumn;

    @FXML
    void onBackBtn(ActionEvent event) {

    }

}
