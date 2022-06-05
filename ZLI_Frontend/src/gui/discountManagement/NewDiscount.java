package gui.discountManagement;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;

import gui.guimanagement.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;

public class NewDiscount {

    @FXML
    private TextField nameField;

    @FXML
    private JFXTimePicker startTimePicker;

    @FXML
    private JFXDatePicker startDatePicker;

    @FXML
    private JFXTimePicker endTimePicker;

    @FXML
    private JFXDatePicker endDatePicker;

    @FXML
    private TextField discountAmountField;

    @FXML
    private TextField productToAdd;

    @FXML
    private TreeTableView<?> productsTable;

    @FXML
    private TreeTableColumn<?, ?> idColumn;

    @FXML
    private TreeTableColumn<?, ?> nameColumn;

    @FXML
    private TreeTableColumn<?, ?> priceColumn;

    @FXML
    private TreeTableColumn<?, ?> removeBtnColumn;

    @FXML
    void onAddBtn(ActionEvent event) {

    }

    @FXML
    void onAddProductBtn(ActionEvent event) {

    }

    @FXML
    void onBackBtn(ActionEvent event) {
        SceneManager.loadPreviousPage();
    }

}
