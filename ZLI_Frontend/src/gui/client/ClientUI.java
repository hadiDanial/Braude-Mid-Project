package gui.client;

import java.io.IOException;
import java.net.URL;

import client.ClientProperties;
import controllers.ClientController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ClientUI extends Application
{
	private static Stage window;
	private static Scene scene;
	private static AnchorPane root;
	
	@FXML
	private Button settingsBtn;
	
	
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
		root = new AnchorPane();
		window = primaryStage;
		ClientController.setClientUI(this);
		AnchorPane anchor;
		try
		{
			window.setOnCloseRequest(new EventHandler<WindowEvent>()
			{
				
				@Override
				public void handle(WindowEvent event)
				{
			        ClientController.getInstance().closeConnection();
				}
			});
			anchor = FXMLLoader.load(getClass().getResource("/gui/client/ClientUI.fxml"));
			anchor.setPrefHeight(ClientProperties.getClientHeight());
			anchor.setPrefWidth(ClientProperties.getClientWidth());
			updateSceneRoot(anchor, "ZLI");
			AnchorPane orders = FXMLLoader.load(getClass().getResource("/gui/orders/OrdersPage.fxml"));
			anchor.getChildren().add(orders);
		} catch (IOException e1)
		{
			e1.printStackTrace();
		}
	}
	public void updateSceneRoot(Node newRoot, String newTitle)
	{
		root = new AnchorPane();
		root.getChildren().clear();
		root.getChildren().add(newRoot);
		scene = new Scene(root);
//		window.close();
		window.setTitle(newTitle);
		window.setScene(scene);
		window.show();
	}
	public Stage getWindow()
	{
		return window;
	}
	
	public void onSettingsButtonClicked(ActionEvent event)
	{
		openSettingsPage();
	}

	public Pane getRoot()
	{
		return root;
	}
	public void openSettingsPage()
	{
		Stage settings = new Stage();
		settings.initModality(Modality.APPLICATION_MODAL);
		settings.setTitle("Settings");
		Pane pane;
		try
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/client/SettingsPage.fxml"));
			pane = loader.load();
			SettingsPage settingsPage = loader.getController();
			settingsPage.setStage(settings);
			Scene scene = new Scene(pane);
			settings.setScene(scene);
			settings.show();
			System.out.println("SETTINGS");
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
}