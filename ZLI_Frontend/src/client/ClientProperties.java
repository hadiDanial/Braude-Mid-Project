package client;

public class ClientProperties
{
	private static String hostAddress = "localhost";
	private static int hostPort = 5555;
	private static int clientWidth = 1240; 
	private static int clientHeight = 800; 
	public static String getHostAddress()
	{
		return hostAddress;
	}
	public static void setHostAddress(String hostAddress)
	{
		ClientProperties.hostAddress = hostAddress;
	}
	public static int getHostPort()
	{
		return hostPort;
	}
	public static void setHostPort(int hostPort)
	{
		ClientProperties.hostPort = hostPort;
	}
	public static int getClientWidth()
	{
		return clientWidth;
	}
	public static int getClientHeight()
	{
		return clientHeight;
	} 
	
	
}
