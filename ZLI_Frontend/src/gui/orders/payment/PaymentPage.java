package gui.orders.payment;

import com.jfoenix.controls.JFXDatePicker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PaymentPage {

    @FXML
    private Label totalPriceLabel;

    @FXML
    private Label currencyLabel;

    @FXML
    private TextField cardHolderInput;

    @FXML
    private TextField cardNumberInput;

    @FXML
    private TextField CVVInput;

    @FXML
    private JFXDatePicker expirationDateInput;

    @FXML
    void onBackBtn(ActionEvent event) {

    }

    @FXML
    void onPayBtn(ActionEvent event) {

    }

}
