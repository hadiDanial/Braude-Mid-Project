package gui.newPages.deliveryOperator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;

public class DeliveryList {

    @FXML
    private TreeTableView<?> discountsTable;

    @FXML
    private TreeTableColumn<?, ?> orderIDColumn;

    @FXML
    private TreeTableColumn<?, ?> recipientColumn;

    @FXML
    private TreeTableColumn<?, ?> addressColumn;

    @FXML
    private TreeTableColumn<?, ?> deliveryDateColumn;

    @FXML
    private TreeTableColumn<?, ?> numOfItemsColumn;

    @FXML
    private TreeTableColumn<?, ?> preiceColumn;

    @FXML
    private TreeTableColumn<?, ?> confirmColumn;

    @FXML
    void onBackBtn(ActionEvent event) {

    }

}
