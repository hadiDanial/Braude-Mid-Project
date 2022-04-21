package gui;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class ClientUI extends Application
{
	Stage window;

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
		window = primaryStage;
		AnchorPane anchor;
		try
		{
			anchor = FXMLLoader.load(getClass().getResource("/gui/orders/OrdersPage.fxml"));
			Scene scene = new Scene(anchor);
			window.setTitle("Orders");
			window.setScene(scene);
			window.show();
		} catch (IOException e1)
		{
			e1.printStackTrace();
		}
	}

}