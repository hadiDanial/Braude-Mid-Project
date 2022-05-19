package gui.orders.delivery.info;

import java.net.URL;
import java.util.ResourceBundle;

import gui.guimanagement.GUIController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SenderInfoPage extends GUIController
{

    @FXML
    private TextField nameInput;

    @FXML
    private TextField phoneInput;

    @FXML
    private TextArea greetingInput;

    @FXML
    void onBackBtn(ActionEvent event) {

    }

    @FXML
    void onNextBtn(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
	}

}
