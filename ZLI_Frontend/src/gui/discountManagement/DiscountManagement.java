package gui.discountManagement;

import gui.guimanagement.GUIPages;
import gui.guimanagement.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;

public class DiscountManagement {

    @FXML
    private TableView<?> discountsTable;

    @FXML
    private TableColumn<?, ?> startDateColumn;

    @FXML
    private TableColumn<?, ?> endDateColumn;

    @FXML
    private TableColumn<?, ?> numProductsColumn;

    @FXML
    private TableColumn<?, ?> discountAmountColumn;

    @FXML
    private TableColumn<?, ?> numSoldColumn;

    @FXML
    void onAddBtn(ActionEvent event) {
        SceneManager.loadNewScene(GUIPages.NewDiscount, true);
    }

    @FXML
    void onBackBtn(ActionEvent event) {
        SceneManager.loadPreviousPage();
    }

}
