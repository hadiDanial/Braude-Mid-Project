package gui.client;

import java.net.URL;
import java.util.ResourceBundle;

import client.ClientProperties;
import controllers.ClientController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SettingsPage implements Initializable
{
	@FXML
	private TextField ipAddress;

	@FXML
	private Button confirmBtn;

	@FXML
	private Button cancelBtn;

	private Stage stage;

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

	public void setStage(Stage settings)
	{
		this.stage = settings;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		ipAddress.setText(ClientProperties.getHostAddress());
	}
}