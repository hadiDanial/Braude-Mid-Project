package gui.orders.openOrders;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class OpenOrdersList {

    @FXML
    private TableView<?> discountsTable;

    @FXML
    private TableColumn<?, ?> addressColumn;

    @FXML
    private TableColumn<?, ?> orderDateColumn;

    @FXML
    private TableColumn<?, ?> numOfItemsColumn;

    @FXML
    private TableColumn<?, ?> priceColumn;

    @FXML
    private TableColumn<?, ?> acceptColumn;

    @FXML
    private TableColumn<?, ?> cancelColumn;

    @FXML
    void onBackBtn(ActionEvent event) {

    }

}
