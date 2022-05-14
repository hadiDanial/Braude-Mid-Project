package gui.client;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import client.ClientProperties;
import controllers.ClientController;
import gui.GUIController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SettingsPage extends GUIController
{
	@FXML
	private TextField ipAddress;

	@FXML
	private Button confirmBtn;

	@FXML
	private Button cancelBtn;

	@FXML
	void onCancelButtonClick(ActionEvent event)
	{
		stage.close();
	}

	@FXML
	void onConfirmBtnClick(ActionEvent event)
	{
		ClientProperties.setHostAddress(ipAddress.getText());
		ClientController.getInstance().reconnectToServer();
		stage.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		ipAddress.setText(ClientProperties.getHostAddress());
	}
}