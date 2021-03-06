package gui.client;

import gui.guimanagement.SceneManager;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ClientUI extends Application
{
	private static AnchorPane root;
	
	@FXML
	private Button settingsBtn;
	
	@FXML
	private AnchorPane content;
	
	@FXML
	public ScrollPane scrollPane;
	
	
	/** 
	 * @param args
	 */
	public static void main(String[] args)
	{
		launch(args);
	}

	/**
	 * 
	 * @param primaryStage
	 */
	public void start(Stage primaryStage)
	{
		SceneManager.initUI(primaryStage);
	}
	
	
	/** 
	 * @param event
	 */
	public void onSettingsButtonClicked(ActionEvent event)
	{
		SceneManager.openSettingsPage();
	}

	
	/** 
	 * @return Pane
	 */
	public Pane getRoot()
	{
		return root;
	}

	
	/** 
	 * @return AnchorPane
	 */
	public AnchorPane getContent()
	{
		return content;
	}
	
}