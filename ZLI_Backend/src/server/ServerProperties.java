package server;

public class ServerProperties {
	private static String dbUsername;
	private static String dbPassword;
	private static final String serverURL = "localhost";
	private static final int serverPort = 5555;
	public static final int DEFAULT_PORT = 5555;
	private static final String databaseName = "zli_project";
	private static final String timezone = "serverTimezone=IST";
	private static String mailHost;
	private static int mailPort;
	private static String mailAddress;
	private static String mailPassword;

	
	/** 
	 * @param dbUsername
	 */
	public static void setDbUsername(String dbUsername) {
		ServerProperties.dbUsername = dbUsername;
	}

	
	/** 
	 * @param dbPassword
	 */
	public static void setDbPassword(String dbPassword) {
		ServerProperties.dbPassword = dbPassword;
	}

	
	/** 
	 * @param mailHost
	 */
	public static void setMailHost(String mailHost) {
		ServerProperties.mailHost = mailHost;
	}

	
	/** 
	 * @param mailPort
	 */
	public static void setMailPort(int mailPort) {
		ServerProperties.mailPort = mailPort;
	}

	
	/** 
	 * @param mailAddress
	 */
	public static void setMailAddress(String mailAddress) {
		ServerProperties.mailAddress = mailAddress;
	}

	
	/** 
	 * @param mailPassword
	 */
	public static void setMailPassword(String mailPassword) {
		ServerProperties.mailPassword = mailPassword;
	}

	
	/** 
	 * @return String
	 */
	public static String getDbUsername() {
		return dbUsername;
	}

	
	/** 
	 * @return String
	 */
	public static String getDbPassword() {
		return dbPassword;
	}

	
	/** 
	 * @return String
	 */
	public static String getServerurl() {
		return serverURL;
	}

	
	/** 
	 * @return int
	 */
	public static int getServerPort() {
		return serverPort;
	}

	
	/** 
	 * @return String
	 */
	public static String getDatabasename() {
		return databaseName;
	}

	
	/** 
	 * @return String
	 */
	public static String getTimezone() {
		return timezone;
	}

	
	/** 
	 * @return String
	 */
	public static String getMailHost() {
		return mailHost;
	}

	
	/** 
	 * @return int
	 */
	public static int getMailPort() {
		return mailPort;
	}

	
	/** 
	 * @return String
	 */
	public static String getMailAddress() {
		return mailAddress;
	}

	
	/** 
	 * @return String
	 */
	public static String getMailPassword() {
		return mailPassword;
	}

}