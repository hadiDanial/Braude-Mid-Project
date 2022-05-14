package controllers;

import java.io.IOException;
import java.util.HashSet;

import client.Client;
import gui.SceneManager;
import gui.client.ClientUI;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import requests.Request;
import utility.IEventListener;
import utility.IResponse;

public class ClientController
{

	private static ClientController instance;
	private static ClientUI clientUI;
	private Client client;
	public static HashSet<IEventListener> connectionListeners;
	
	private ClientController()
	{
		client = new Client();
		connectionListeners = new HashSet<IEventListener>();
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

	public <T> void sendRequest(Request request, IResponse<T> response)
	{
		client.handleMessageFromClientUI(request, response);
	}
	public void reconnectToServer()
	{
		try
		{
			client.closeConnection();
			client = new Client();
			invokeConnectionListeners();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void openSettingsPage()
	{
		SceneManager.openSettingsPage();
	}

	public void closeConnection()
	{
		if(client != null)
		{
			client.quit();
		}
	}
	
	public void registerConnectionListener(IEventListener listener){
	    connectionListeners.add(listener);
	}
	public void removeConnectionListener(IEventListener listener){
		connectionListeners.remove(listener);
	}
	private void invokeConnectionListeners(){
	    for(IEventListener listener:connectionListeners){
	        listener.invoke();
	    }
	}
}