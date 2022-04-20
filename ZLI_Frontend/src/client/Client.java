package client;

import ocsf.client.AbstractClient;

public class Client extends AbstractClient
{
	private static Client instance = null;;

	private Client(String host, int port)
	{
		super(host, port);
		
	}

	public static Client getInstance()
	{
		if(instance == null)
		{
			instance = new Client(ClientProperties.getHostAddress(), ClientProperties.getHostPort());
		}
		return instance;
	}

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
	private void handleMessageFromClientUI(String message)
	{
		// TODO - implement Client.handleMessageFromClientUI
		throw new UnsupportedOperationException();
	}

	public void quit()
	{
		// TODO - implement Client.quit
		throw new UnsupportedOperationException();
	}

}