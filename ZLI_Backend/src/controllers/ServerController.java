package controllers;

import server.Server;

public class ServerController
{

	private Server server;
	
	

	public ServerController()
	{
		server = Server.getInstance();
	}

	/**
	 * 
	 * @param schemaName
	 * @param username
	 * @param password
	 */
	public boolean connectToDatabase(String schemaName, String username, String password)
	{
		// TODO - implement ServerController.connectToDatabase
		throw new UnsupportedOperationException();
	}

	public boolean disconnectFromDatabase()
	{
		// TODO - implement ServerController.disconnectFromDatabase
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param textToAdd
	 */
	public void updateConsole(String textToAdd)
	{
		// TODO - implement ServerController.updateConsole
		throw new UnsupportedOperationException();
	}

	/**
	 * Uses the Server object it has (singleton, Server.getInstance() ) to start the server. Appends result (success or failure) to the console using the ServerUI class.
	 * @param port
	 */
	public boolean startServer(String port)
	{
		// TODO - implement ServerController.startServer
		throw new UnsupportedOperationException();
	}

}