package gui;

import java.util.UUID;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class GUIController implements Initializable
{
	protected Scene scene;
	protected Stage stage;
	protected Parent root;

	public void setScene(Scene scene)
	{
		this.scene = scene;
	}

	public void setStage(Stage settings)
	{
		this.stage = settings;
	}
	
	public void setRoot(Parent root)
	{
		this.root = root;
	}
	
	public Parent getRoot()
	{
		return root;
	}
}
