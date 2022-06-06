package gui.client.settings;

import java.net.URL;
import java.util.ResourceBundle;

import client.ClientProperties;
import controllers.ClientController;
import gui.guimanagement.ButtonAnimator;
import gui.guimanagement.FormController;
import gui.guimanagement.GUIController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Settings extends FormController{

    @FXML
    private TextField hostField;

    @FXML
    private TextField portField;
    @FXML private Button connectBtn;
    @FXML
    void onConnectBtn(ActionEvent event) {
    	ClientProperties.setHostAddress(hostField.getText());
    	ClientProperties.setHostPort(Integer.parseInt(portField.getText()));
		ClientController.getInstance().reconnectToServer();
		stage.close();
    }

    @FXML
    void onExitBtn(ActionEvent event) {
		stage.close();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		hostField.setText(ClientProperties.getHostAddress());
		portField.setText(String.valueOf(ClientProperties.getHostPort()));
		ButtonAnimator.addButtonAnimations(connectBtn);
	}

}
