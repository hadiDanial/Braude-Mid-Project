package controllers;

import client.Client;
import gui.ClientUI;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ClientController
{

	private static ClientController instance;
	private static ClientUI clientUI;
	private Client client;
	
	private ClientController()
	{
		client = new Client();
	}
	
	public static ClientController getInstance() 
	{
		if(instance == null)
		{
			instance = new ClientController();
		}
		return instance;
	}

	public static void setClientUI(ClientUI clientUI)
	{
		ClientController.clientUI = clientUI;
	}
	
	public static Stage getStage()
	{
		return clientUI.getWindow();
	}

	public static void updateRoot(Node newRoot, String newTitle)
	{
		clientUI.updateSceneRoot(newRoot, newTitle);
	}

	public static Pane getRoot()
	{
		return clientUI.getRoot();
	}

}