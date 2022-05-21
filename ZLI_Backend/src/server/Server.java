package server;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import gui.ServerUI;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;
import requests.Request;

public class Server extends AbstractServer {

	private String hostAddress = null;
	private static Server instance = null;
	private Timer timer = new Timer();

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
		ServerUI.consoleTxtList.add(new ConsoleString("Server listening for connections on port " + getPort()));

		System.out.println("Server listening for connections on port " + getPort());
	}

	protected void serverStopped() {
		ServerUI.consoleTxtList.add(new ConsoleString("Server has stopped listening for connections."));
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
			ServerUI.consoleTxtList.add(new ConsoleString("ERROR - Could not listen for clients!"));
			System.out.println("ERROR - Could not listen for clients!");
		}
		sv.setCheckClientConnection();
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
		if (hostAddress == null) {
			try {
				this.hostAddress = InetAddress.getLocalHost().getHostAddress();
			} catch (Exception e) {
				System.out.println("Cant get host Address");
			}
		}
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
			// MessageParser.parseMessage(msg, client);
			Request request;
			if (msg instanceof Request) {
				request = (Request) msg;
				System.out.println("Message received: " + request.getRequestType() + " from " + client);
				Object response = MessageParser.handleRequest(request);
				sendToClient(response, client);
			} else {
				return;
			}
		} catch (Exception e) {

		}


	}

	@Override
	protected void clientConnected(ConnectionToClient client) {
		super.clientConnected(client);
		ServerUI.clients.add(new ConnectedClient(client));
	}

	@Override
	protected synchronized void clientDisconnected(ConnectionToClient client) {
		ServerUI.clients.remove(new ConnectedClient(client));
		super.clientDisconnected(client);
	}

	public void closeServer() throws Exception {
		this.close();
		timer.cancel();
	}

	private void setCheckClientConnection() {
		// every 1 second check if a client in the clients list is not connected
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				Server.getInstance().checkIfClientIsConnected();
			}
		}, 100, 1000);

	}

	public void checkIfClientIsConnected() {
		ArrayList<ConnectedClient> toRemove = new ArrayList<>();
		for (ConnectedClient connectedClient : ServerUI.clients) {
			// if the client connection is closed then the toString function will be null
			if (connectedClient.getClient().toString() == null)
				toRemove.add(connectedClient);
		}

		ServerUI.clients.removeAll(toRemove);
	}
}