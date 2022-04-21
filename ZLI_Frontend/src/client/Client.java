package client;

import java.io.IOException;

import ocsf.client.AbstractClient;

public class Client extends AbstractClient
{
	public Client()
	{
		super(ClientProperties.getHostAddress(), ClientProperties.getHostPort());
	}
//	
//	private Client(String host, int port)
//	{
//		super(host, port);
//		
//	}

	/**
	 * 
	 * @param message
	 */
	protected void handleMessageFromServer(Object message)
	{
		// TODO - implement Client.handleMessageFromServer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param message
	 */
	protected void handleMessageFromClientUI(String message)
	{
		// TODO - implement Client.handleMessageFromClientUI
		throw new UnsupportedOperationException();
	}

	public void quit()
	{
		try
		{
			closeConnection();
			System.exit(0);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}