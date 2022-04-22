package gui;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
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
		//AnchorPane anchor;
		Pane pane;
		try
		{
			pane = FXMLLoader.load(getClass().getResource("/gui/orders/OrderUpdatePage.fxml"));
			Scene scene = new Scene(pane);
			window.setTitle("UpdateOrder");
			window.setScene(scene);
			window.show();
		} catch (IOException e1)
		{
			e1.printStackTrace();
		}
	}

}