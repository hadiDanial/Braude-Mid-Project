package gui.orders.delivery;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;

import gui.guimanagement.GUIController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PickUpPage extends GUIController
{

    @FXML
    private TextField nameInput;

    @FXML
    private JFXTimePicker timePicker;

    @FXML
    private JFXDatePicker datePicker;

    @FXML
    void onBackBtn(ActionEvent event) {

    }

    @FXML
    void onDeliverySelect(ActionEvent event) {

    }

    @FXML
    void onNextBtn(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
	}

}
