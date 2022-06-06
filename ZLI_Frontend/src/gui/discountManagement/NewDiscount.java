package gui.discountManagement;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;

import gui.guimanagement.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
    private TableView<?> productsTable;

    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private TableColumn<?, ?> priceColumn;

    @FXML
    private TableColumn<?, ?> removeBtnColumn;

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
