package controllers;

import client.Client;

public class ClientController
{

	private static ClientController instance;
	private Client client;
	
	private ClientController()
	{
		client = new Client();
	}
	
	public static ClientController getInstance() {
		if(instance == null)
		{
			instance = new ClientController();
		}
		return instance;
	}
	

}