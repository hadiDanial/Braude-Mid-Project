package gui;

import java.io.IOException;
import java.net.URL;

import client.ClientProperties;
import controllers.ClientController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
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
	
	public void onSettingsButtonClicked(ActionEvent event) throws IOException
	{
		AnchorPane anchor = FXMLLoader.load(getClass().getResource("/gui/orders/OrdersPage.fxml"));
		updateSceneRoot(anchor, "Orders");
	}

	public Pane getRoot()
	{
		return root;
	}

}