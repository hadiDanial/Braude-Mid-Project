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

public class ClientController implements ClientControllerIF
{

	private static ClientController instance;
	private Client client;
	public static HashSet<IEventListener> connectionListeners;
	
	private ClientController()
	{
		client = new Client();
		connectionListeners = new HashSet<IEventListener>();
	}
	
	
	/** 
	 * @return ClientController
	 */
	public static synchronized ClientController getInstance() 
	{
		if(instance == null)
		{
			instance = new ClientController();
		}
		return instance;
	}

	
	/** sends request for the client  
	 * @param request
	 * @param response
	 */
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

	//closes connection when logged out and returns to the first page
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
	
	
	/** 
	 * @param listener
	 */
	public void registerConnectionListener(IEventListener listener){
	    connectionListeners.add(listener);
	}
	
	/** 
	 * @param listener
	 */
	public void removeConnectionListener(IEventListener listener){
		connectionListeners.remove(listener);
	}
	private void invokeConnectionListeners(){
	    for(IEventListener listener:connectionListeners){
	        listener.invoke();
	    }
	}
}