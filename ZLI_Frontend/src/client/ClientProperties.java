package client;

public class ClientProperties
{
	private static String hostAddress = "localhost";
	private static int hostPort = 5555;
	private static int clientWidth = 1300; 
	private static int clientHeight = 1000; 
	private static double defaultPercentageOfParent = 0.9;

	
	/** 
	 * @return String
	 */
	public static String getHostAddress()
	{
		return hostAddress;
	}
	
	/** 
	 * @param hostAddress
	 */
	public static void setHostAddress(String hostAddress)
	{
		ClientProperties.hostAddress = hostAddress;
	}
	
	/** 
	 * @return int
	 */
	public static int getHostPort()
	{
		return hostPort;
	}
	
	/** 
	 * @param hostPort
	 */
	public static void setHostPort(int hostPort)
	{
		ClientProperties.hostPort = hostPort;
	}
	
	/** 
	 * @return int
	 */
	public static int getClientWidth()
	{
		return clientWidth;
	}
	
	/** 
	 * @return int
	 */
	public static int getClientHeight()
	{
		return clientHeight;
	}
	
	/** 
	 * @return double
	 */
	public static double getDefaultPercentageOfParent()
	{
		return defaultPercentageOfParent;
	} 
	
	
}
