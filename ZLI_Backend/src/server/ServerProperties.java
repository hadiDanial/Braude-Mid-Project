package server;

public class ServerProperties
{
	private static String dbUsername;
	private static String dbPassword;
	private static final String serverURL = "localhost";
	private static final int serverPort = 5555;
	private static final String databaseName = "zli_project";
	private static final String timezone = "serverTimezone=IST";
	private static String mailHost;
	private static int mailPort;
	private static String mailAddress;
	private static String mailPassword;
	
	public static void setDbUsername(String dbUsername)
	{
		ServerProperties.dbUsername = dbUsername;
	}
	public static void setDbPassword(String dbPassword)
	{
		ServerProperties.dbPassword = dbPassword;
	}
	public static void setMailHost(String mailHost)
	{
		ServerProperties.mailHost = mailHost;
	}
	public static void setMailPort(int mailPort)
	{
		ServerProperties.mailPort = mailPort;
	}
	public static void setMailAddress(String mailAddress)
	{
		ServerProperties.mailAddress = mailAddress;
	}
	public static void setMailPassword(String mailPassword)
	{
		ServerProperties.mailPassword = mailPassword;
	}
	public static String getDbUsername()
	{
		return dbUsername;
	}
	public static String getDbPassword()
	{
		return dbPassword;
	}
	public static String getServerurl()
	{
		return serverURL;
	}
	public static int getServerPort()
	{
		return serverPort;
	}
	public static String getDatabasename()
	{
		return databaseName;
	}
	public static String getTimezone()
	{
		return timezone;
	}
	public static String getMailHost()
	{
		return mailHost;
	}
	public static int getMailPort()
	{
		return mailPort;
	}
	public static String getMailAddress()
	{
		return mailAddress;
	}
	public static String getMailPassword()
	{
		return mailPassword;
	}
	
}