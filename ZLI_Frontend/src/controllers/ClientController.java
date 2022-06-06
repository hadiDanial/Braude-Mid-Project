package controllers;

import java.io.IOException;
import java.util.HashSet;

import client.Client;
import entities.users.Order;
import gui.guimanagement.SceneManager;
import requests.EntityRequestWithId;
import requests.Request;
import utility.IEventListener;
import utility.IResponse;

public class ClientController
{

	private static ClientController instance;
	private Client client;
	public static HashSet<IEventListener> connectionListeners;
	
	private ClientController()
	{
		client = new Client();
		connectionListeners = new HashSet<IEventListener>();
	}
	
	public static synchronized ClientController getInstance() 
	{
		if(instance == null)
		{
			instance = new ClientController();
		}
		return instance;
	}

	public <T> void sendRequest(Request request, IResponse<T> response)
	{
		request.setUser(UserController.getInstance().getLoggedInUser());
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
			// Logout
			final UserController userController = UserController.getInstance();
			if(userController.isLoggedIn())
				userController.logout();
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