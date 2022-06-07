package gui.client;

import java.net.URL;
import java.util.ResourceBundle;

import client.ClientProperties;
import controllers.ClientController;
import gui.guimanagement.GUIController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SettingsPage extends GUIController
{
	@FXML
	private TextField ipAddress;

	@FXML
	private Button confirmBtn;

	@FXML
	private Button cancelBtn;

	
	/** 
	 * @param event
	 */
	@FXML
	void onCancelButtonClick(ActionEvent event)
	{
		stage.close();
	}

	
	/** 
	 * @param event
	 */
	@FXML
	void onConfirmBtnClick(ActionEvent event)
	{
		ClientProperties.setHostAddress(ipAddress.getText());
		ClientController.getInstance().reconnectToServer();
		stage.close();
	}

	
	/** 
	 * @param location
	 * @param resources
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		ipAddress.setText(ClientProperties.getHostAddress());
	}
}