package server;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;
import server.ServerProperties;

public class Server extends AbstractServer
{

	private String hostAddress;
	private static Server instance = null;
	
	private Server(int port)
	{
		super(port);
	}

	public static Server getInstance()
	{
		if(instance == null)
		{
			instance = new Server(ServerProperties.getServerPort());
		}
		return instance;
	}
	
	/**
	 * 
	 * @param String
	 */
	public Server runServer(int String)
	{
		// TODO - implement Server.runServer
		throw new UnsupportedOperationException();
	}


	protected void serverStarted()
	{
		// TODO - implement Server.serverStarted
		throw new UnsupportedOperationException();
	}

	protected void serverStopped()
	{
		// TODO - implement Server.serverStopped
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param port
	 */
	public boolean runServer(String port)
	{
		// TODO - implement Server.runServer
		throw new UnsupportedOperationException();
	}

	public String getHostAddress()
	{
		return this.hostAddress;
	}

	/**
	 * 
	 * @param message
	 * @param client
	 */
	public void sendToClient(Object message, ConnectionToClient client)
	{
		// TODO - implement Server.sendToClient
		throw new UnsupportedOperationException();
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client)
	{
		
	}

}