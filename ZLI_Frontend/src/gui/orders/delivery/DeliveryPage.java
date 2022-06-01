package gui.orders.delivery;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;

import gui.guimanagement.GUIController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DeliveryPage extends GUIController
{

    @FXML
    private TextField nameInput;

    @FXML
    private TextField phoneInput;

    @FXML
    private TextField addressInput;

    @FXML
    private JFXTimePicker timePicker;

    @FXML
    private JFXDatePicker datePicker;

    @FXML
    private TextArea noteInput;

    @FXML
    void onBackBtn(ActionEvent event) {

    }

    @FXML
    void onDeliverySelect(ActionEvent event) {

    }

    @FXML
    void onNextBtn(ActionEvent event) {

    }

    @FXML
    void onPickUpSelect(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
	}

}