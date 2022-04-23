package server;

import java.net.InetAddress;

import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class Server extends AbstractServer {

	private String hostAddress;
	private static Server instance = null;

	public Server(int port) {
		super(port);

		try {
			this.hostAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static Server getInstance() {
		if (instance == null) {
			instance = new Server(ServerProperties.DEFAULT_PORT);
		}
		return instance;
	}

	protected void serverStarted() {
		// ! ServerUI.consoleTxtList.add("Server listening for connections on port " +
		// ! getPort());
		System.out.println("Server listening for connections on port " + getPort());
	}

	protected void serverStopped() {
		// ! ServerUI.consoleTxtList.add("Server has stopped listening for
		// ! connections.");
		System.out.println("Server has stopped listening for connections.");
	}

	/**
	 *
	 * @param p int
	 * @return Server
	 */
	public static Server runServer(int p) {
		Server sv = new Server(p);

		try {
			sv.listen(); // Start listening for connections
		} catch (Exception ex) {
			System.out.println("ERROR - Could not listen for clients!");
		}

		return instance = sv;
	}

	/**
	 * 
	 * @param p String
	 * @return Server
	 */
	public static Server runServer(String p) {
		int port = 0; // Port to listen on

		try {
			port = Integer.parseInt(p);
		} catch (Throwable t) {
			port = ServerProperties.DEFAULT_PORT; // Set port to 5555
		}
		return runServer(port);
	}

	public String getHostAddress() {
		return this.hostAddress;
	}

	public void sendToClient(Object message, ConnectionToClient client) {
		try {
			client.sendToClient(message);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		try {
			MessageParser.parseMessage(msg, client);
		} catch (Exception e) {

		}

		System.out.println("Message received: " + msg + " from " + client);
		this.sendToAllClients(msg);
	}

	@Override
	protected void clientConnected(ConnectionToClient client) {
		// TODO Auto-generated method stub
		super.clientConnected(client);
	}

	@Override
	protected synchronized void clientDisconnected(ConnectionToClient client) {
		// TODO Auto-generated method stub
		super.clientDisconnected(client);
	}

}