package gui.discountManagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;

public class DiscountManagement {

    @FXML
    private TreeTableView<?> discountsTable;

    @FXML
    private TreeTableColumn<?, ?> startDateColumn;

    @FXML
    private TreeTableColumn<?, ?> endDateColumn;

    @FXML
    private TreeTableColumn<?, ?> numProductsColumn;

    @FXML
    private TreeTableColumn<?, ?> discountAmountColumn;

    @FXML
    private TreeTableColumn<?, ?> numSoldColumn;

    @FXML
    void onAddBtn(ActionEvent event) {

    }

    @FXML
    void onBackBtn(ActionEvent event) {

    }

}
