package gui.client.error;

import java.net.URL;
import java.util.ResourceBundle;

import controllers.UserController;
import gui.guimanagement.GUIController;
import gui.guimanagement.GUIPages;
import gui.guimanagement.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class ErrorPage extends GUIController
{
	@FXML 
	private Text errorMessageText;
	@FXML
	private Button okBtn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
	}

	public void onBtnClick(ActionEvent event)
	{
		SceneManager.clearHistory();
		UserController.getInstance().logout();
		SceneManager.loadNewScene(GUIPages.LOGIN, true);
		stage.close();
	}

	@Override
	public void setData(Object data)
	{
		super.setData(data);
		errorMessageText.setText((String)data);
	}
	
	
}
