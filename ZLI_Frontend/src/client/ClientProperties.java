package client;

public class ClientProperties
{
	private static String hostAddress = "localhost";
	private static int hostPort = 5555;
	
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
	
	
}
