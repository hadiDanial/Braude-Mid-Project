package client;

import java.io.IOException;

import ocsf.client.AbstractClient;
import requests.Request;
import requests.RequestType;
import utility.IResponse;

public class Client extends AbstractClient
{
	private boolean awaitResponse;
	private IResponse<?> response;
	
	public Client()
	{
		super(ClientProperties.getHostAddress(), ClientProperties.getHostPort());
	}

	/**
	 * 
	 * @param message
	 */
	protected void handleMessageFromServer(Object message)
	{
		System.out.println("--> handleMessageFromServer");

		awaitResponse = false;
		if (response != null)
		{
			response.executeAfterResponse(message);
			response = null;
		}
	}

	/**
	 * 
	 * @param <T>
	 * @param message
	 */
	public <T> void handleMessageFromClientUI(Request request, IResponse<T> response)
	{
		try
		{
			openConnection();// in order to send more than one message
			awaitResponse = true;
			this.response = response;
			sendToServer(request);
			// wait for response
			while (awaitResponse)
			{
				try
				{
					Thread.sleep(100);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		} catch (IOException e)
		{
			e.printStackTrace();
			this.response = null;
			this.awaitResponse = false;
//			clientUI.display("Could not send message to server: Terminating client." + e);
			quit();
		}
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