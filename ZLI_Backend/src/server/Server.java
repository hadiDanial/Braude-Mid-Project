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

	// public static Server getInstance() {
	// if (instance == null) {
	// instance = new Server(ServerProperties.getServerPort());
	// }
	// return instance;
	// }

	/**
	 * 
	 * @param String
	 */
	public Server runServer(int String) {
		// TODO - implement Server.runServer
		throw new UnsupportedOperationException();
	}

	protected void serverStarted() {
		System.out.println("Server listening for connections on port " + getPort());
	}

	protected void serverStopped() {
		System.out.println("Server has stopped listening for connections.");
	}

	public static Server runServer(String p) {
		int port = 0; // Port to listen on

		try {
			port = Integer.parseInt(p);
		} catch (Throwable t) {
			port = ServerProperties.DEFAULT_PORT; // Set port to 5555
		}

		Server sv = new Server(port);

		try {
			sv.listen(); // Start listening for connections
		} catch (Exception ex) {
			System.out.println("ERROR - Could not listen for clients!");
		}
		return sv;
	}

	public String getHostAddress() {
		return this.hostAddress;
	}

	public void sendToClient(Object message, ConnectionToClient client) {
		// TODO - implement Server.sendToClient
		throw new UnsupportedOperationException();
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		// TODO start parsing message and save client if not saved
		// ? maybe send to CommunicationController class
		System.out.println("Message received: " + msg + " from " + client);
		this.sendToAllClients(msg);
	}

}