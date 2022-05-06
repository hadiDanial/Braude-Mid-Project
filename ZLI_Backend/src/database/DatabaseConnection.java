package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;

import gui.ServerUI;
import server.ConsoleString;

public class DatabaseConnection
{
	private static Connection conn = null;
	private static DatabaseConnection instance = null;

	private DatabaseConnection()
	{
	}

	public static DatabaseConnection getInstance()
	{
		if (instance == null)
		{
			instance = new DatabaseConnection();
		}
		return instance;
	}

	/**
	 * Connect to the database
	 * 
	 * @param hostIP     IP address of the SQL database (usually localhost)
	 * @param serverName Database schema name
	 * @param user       User that connects to the database
	 * @param password   Password used to connect to the data base
	 * @throws Exception Exceptions of type Exception and SQLException
	 */
	public void connectToDB(String hostIP, String serverName, String user, String password) throws Exception
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			ServerUI.consoleTxtList.add(new ConsoleString("Driver definition succeed"));
			System.out.println("Driver definition succeed");
		} catch (Exception e)
		{
			ServerUI.consoleTxtList.add(new ConsoleString("Driver definition failed"));
			System.out.println("Driver definition failed");
			throw e;
		}

		try
		{
			conn = DriverManager.getConnection("jdbc:mysql://" + hostIP + "/" + serverName + "?serverTimezone=IST",
					user, password);
			ServerUI.consoleTxtList.add(new ConsoleString("SQL connection succeed"));
			System.out.println("SQL connection succeed");

		} catch (SQLException e)
		{/* handle any errors */
			ServerUI.consoleTxtList.add(new ConsoleString("SQLException: " + e.getMessage()));
			ServerUI.consoleTxtList.add(new ConsoleString("SQLState: " + e.getSQLState()));
			ServerUI.consoleTxtList.add(new ConsoleString("VendorError: " + e.getErrorCode()));
			// TODO: Remove the print lines
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
			throw e;
		}
	}

	/**
	 * Close the connection to the database.
	 */
	public void disconnect()
	{
		try
		{
			conn.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public int insertToDatabase(ArrayList<String> data, String tableName)
	{
		PreparedStatement ps;
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ");
		sb.append(tableName);
		sb.append(" VALUES(");
		for (int i = 0; i < data.size(); i++)
		{
			if (i == data.size() - 1)
				sb.append("?");
			else
				sb.append("?,");
		}
		sb.append(");");

		try
		{
			ps = conn.prepareStatement(sb.toString());
			for (int i = 0; i < data.size(); i++)
			{
				ps.setString(i + 1, data.get(i));
			}
			return ps.executeUpdate();

		} catch (Exception e)
		{
			System.out.println(e);
			return -1;
		}
	}

	/**
	 * Returns a record from the given table with a specific primary key.
	 * 
	 * @param <T>         Class type for the entity that matches the table.
	 * @param id          Primary key for the requested record.
	 * @param idFieldName Name of the field containing the primary key of the table.
	 * @param tableName   Name of the table.
	 * @param rsToObject  An <code>IResultSetToObject</code> that describes how to
	 *                    convert a record to the entity class.
	 * @return Entity object of type <code>T</code> if a record with the PK is
	 *         found, otherwise <code>NULL</code>.
	 */
	public <T> T getByID(int id, String tableName, String idFieldName, IResultSetToObject<T> rsToObject)
	{
		Statement stmt;
		T item = null;
		try
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT FROM " + tableName + " WHERE " + idFieldName + "=" + id + ";");
			if (rs.next())
			{
				item = rsToObject.convertToObject(rs);
			}
			rs.close();
		} catch (Exception e)
		{
			e.printStackTrace();
			item = null;
		}
		return item;
	}

	/**
	 * Returns all records from the given table.
	 * 
	 * @param <T>        Class type for the entity that matches the table.
	 * @param tableName  Name of the table.
	 * @param rsToObject An <code>IResultSetToObject</code> that describes how to
	 *                   convert a record to the entity class.
	 * @return An ArrayList containing all the records in the database.
	 */
	public <T> ArrayList<T> getAll(String tableName, IResultSetToObject<T> rsToObject)
	{
		Statement stmt;
		ArrayList<T> list = new ArrayList<T>();
		try
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + ";");
			while (rs.next())
			{
				list.add(rsToObject.convertToObject(rs));
			}
			rs.close();
			return list;
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Updates a single record in the database.
	 * 
	 * @param <T>         The class of the entity to update.
	 * @param id          Primary key of the record.
	 * @param idFieldName Name of the primary key field in the table.
	 * @param tableName   Name of the table in the schema.
	 * @param keys        Names of the fields that need updating.
	 * @param objToPS     An instance of
	 *                    <code>IObjectToPreparedStatementParameters</code> that
	 *                    describes how to insert the object data into a
	 *                    <code>PreparedStatement</code>. <b>Must insert the data in
	 *                    the same order as the objects in the <code>keys</code>
	 *                    list.</b>
	 * @return True if the record was successfully updated.
	 */
	public <T> boolean updateInDB(int id, String idFieldName, String tableName, ArrayList<String> keys,
			IObjectToPreparedStatementParameters<T> objToPS)
	{
		PreparedStatement ps;
		try
		{
			StringBuilder sb = new StringBuilder();
			String condition = idFieldName + "=" + id;
			sb.append("UPDATE " + tableName + " SET ");

			appendToQueryString(sb, keys, condition);
			ps = conn.prepareStatement(sb.toString());
			objToPS.convertObjectToPSQuery(ps);
			System.out.println("Update:\n" + sb.toString());
			return ps.executeUpdate() == 1;
		} catch (Exception e)
		{
			System.out.println(e);
		}

		return false;
	}

	/**
	 * Appends the keys list and condition to the query string builder.
	 * @param sb StringBuilder object that contains the query.
	 * @param keys List of field names for the data to be inserted to the <code>PreparedStatement</code>.
	 * @param conditions Conditions for the query (<code>WHERE ...</code>). Can be null or empty.
	 */
	private void appendToQueryString(StringBuilder sb, ArrayList<String> keys, String conditions)
	{
		for (String key : keys)
		{
			sb.append(key + "=?, ");
		}
		sb.delete(sb.length() - 2, sb.length());
		if(conditions == null || conditions.isEmpty())
		{
			sb.append(";");
		}
		else
		{
			sb.append(" WHERE " + conditions + ";");				
		}
	}

	/**
	 * Resets the Orders table in the database.
	 */
	private void resetOrdersTable()
	{
		Statement st;
		try
		{
			st = conn.createStatement();
			boolean rs = st.execute("DROP TABLE IF EXISTS `Orders`;" + "create table `Orders`("
					+ "`orderNumber` int primary key AUTO_INCREMENT," + "`price` float,"
					+ "`greetingCard` varchar(256)," + "`color` varchar(32)," + "`dOrder` varchar(256),"
					+ "`shop` varchar(32)," + "`date` timestamp," + "`orderDate` timestamp);");
		} catch (Exception e)
		{
			System.out.println(e);
		}
	}

}
