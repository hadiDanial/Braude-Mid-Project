package gui.discountManagement;

import gui.guimanagement.GUIPages;
import gui.guimanagement.SceneManager;
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
        SceneManager.loadNewScene(GUIPages.NewDiscount, true);
    }

    @FXML
    void onBackBtn(ActionEvent event) {
        SceneManager.loadPreviousPage();
    }

}
