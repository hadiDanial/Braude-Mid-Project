package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.products.BaseProduct;
import entities.products.CartItem;
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

	/**
	 * Insert an entity to a table.
	 * 
	 * @param <T>         Class type for the entity that matches the table.
	 * @param tableName   Name of the table.
	 * @param columnNames The names of the columns of the table that will have
	 *                    values to be inserted.
	 * @param objToPS     An instance of
	 *                    <code>IObjectToPreparedStatementParameters</code> that
	 *                    describes how to insert the object data into a
	 *                    <code>PreparedStatement</code>. <b>Must insert the data in
	 *                    the same order as the column names in the
	 *                    <code>columnNames</code> array.</b>
	 * @return Number of records modified (should be 1) or -1 on failure.
	 */
	public <T> int insertToDatabase(String tableName, String[] columnNames,
			IObjectToPreparedStatementParameters<T> objToPS)
	{
		PreparedStatement ps;
		String query = generatePSInsertQuery(tableName, columnNames, 1);

		try
		{
			ps = conn.prepareStatement(query);
			objToPS.convertObjectToPSQuery(ps);
			return ps.executeUpdate();

		} catch (Exception e)
		{
			System.out.println(e);
			return -1;
		}
	}

	/**
	 * Insert a collection into the database.
	 * @param <T> Class type for the entity that matches the table.
	 * @param tableName   Name of the table.
	 * @param columnNames The names of the columns of the table that will have
	 *                    values to be inserted.
	 * @param count The number of records to be inserted.
	 * @param objToPS     An instance of
	 *                    <code>IObjectToPreparedStatementParameters</code> that
	 *                    describes how to insert the object data into a
	 *                    <code>PreparedStatement</code>. <b>Must insert the data in
	 *                    the same order as the column names in the
	 *                    <code>columnNames</code> array.</b>
	 * @return True if number of records affected equals count.
	 */
	public <T> boolean insertCollection(String tableName, String[] columnNames, int count,
			IObjectToPreparedStatementParameters<T> objToPS)
	{
		PreparedStatement ps;
		String query = generatePSInsertQuery(tableName, columnNames, count);

		try
		{
			ps = conn.prepareStatement(query);
			objToPS.convertObjectToPSQuery(ps);
			int res = ps.executeUpdate();
			if(res != count)
				throw new SQLException("Affected rows: " + res + ", should be " + count + "! (DatabaseConnection.insertCollection)");
			return true;

		} catch (Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}
	}
	/**
	 * Insert an entity to a table and returns its auto-generated id.
	 * 
	 * @param <T>         Class type for the entity that matches the table.
	 * @param tableName   Name of the table.
	 * @param columnNames The names of the columns of the table that will have
	 *                    values to be inserted.
	 * @param objToPS     An instance of
	 *                    <code>IObjectToPreparedStatementParameters</code> that
	 *                    describes how to insert the object data into a
	 *                    <code>PreparedStatement</code>. <b>Must insert the data in
	 *                    the same order as the column names in the
	 *                    <code>columnNames</code> array.</b>
	 * @return ID of new record or -1 on failure.
	 */
	public <T> int insertAndReturnGeneratedId(String tableName, String[] columnNames,
			IObjectToPreparedStatementParameters<T> objToPS)
	{
		PreparedStatement ps;
		String query = generatePSInsertQuery(tableName, columnNames, 1);

		try
		{
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			objToPS.convertObjectToPSQuery(ps);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next())
			{
				int id = rs.getInt(1);
				rs.close();
				return id;
			} else
			{
				return -1;
			}

		} catch (Exception e)
		{
			System.out.println(e);
			return -1;
		}
	}

	/**
	 * Generate an insertion query:<br>
	 * <code>INSERT INTO tableName (col1, col2,...) VALUES (val_a1, val_a2,...), (val_b1, val_b2,...), ... ;</code>
	 * @param tableName Name of the table
	 * @param columnNames Names of the table columns columns.
	 * @param count Number of values to insert.
	 * @return
	 */
	private String generatePSInsertQuery(String tableName, String[] columnNames, int count)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ");
		sb.append(tableName);
		sb.append(" (");
		for (int i = 0; i < columnNames.length; i++)
		{
			if (i == columnNames.length - 1)
				sb.append(columnNames[i]);
			else
				sb.append(columnNames[i] + ",");
		}
		sb.append(") VALUES ");
		for (int j = 0; j < count; j++)
		{
			sb.append("(");
			for (int i = 0; i < columnNames.length; i++)
			{
				if (i == columnNames.length - 1)
					sb.append("?");
				else
					sb.append("?,");
			}
			sb.append(")");
			if(j < count-1)
			{				
				sb.append(", ");
			}
		}
		sb.append(";");
		return sb.toString();
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
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " WHERE " + idFieldName + "=" + id + ";");
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
	 * Returns a record from the given table with a specific simple condition
	 * (string, one column).
	 * 
	 * @param <T>                Class type for the entity that matches the table.
	 * @param conditionFieldName Name of the field containing the condition.
	 * @param conditionValue     Value of the condition.
	 * @param tableName          Name of the table.
	 * @param rsToObject         An <code>IResultSetToObject</code> that describes
	 *                           how to convert a record to the entity class.
	 * @return Entity object of type <code>T</code> if a record with the condition
	 *         is found, otherwise <code>NULL</code>.
	 */
	public <T> T getBySimpleCondition(String conditionFieldName, String conditionValue, String tableName,
			IResultSetToObject<T> rsToObject)
	{
		Statement stmt;
		T item = null;
		try
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT * FROM " + tableName + " WHERE " + conditionFieldName + "='" + conditionValue + "';");
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
	 * Returns all records when joining the two tables with the given conditions.
	 * 
	 * @param <T>             Class type for the entity that matches the table.
	 * @param firstTableName  First join table name.
	 * @param secondTableName Second join table name.
	 * @param conditions      The conditions of the join operation, for example:<br>
	 *                        <code>firstTableName.firstTableId=secondTableName.secondTableId AND secondTabkeName.quantity > 10</code>.
	 *                        Do not include WHERE or ; in the conditions.
	 * @param rsToObject      An <code>IResultSetToObject</code> that describes how
	 *                        to convert a record to the entity class.
	 * @return An ArrayList containing all matching records from the join result.
	 */
	public <T> ArrayList<T> getJoinResultWithSimpleConditions(String firstTableName, String secondTableName,
			String conditions, IResultSetToObject<T> rsToObject)
	{
		Statement stmt;
		ArrayList<T> list = new ArrayList<T>();
		try
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT * FROM " + firstTableName + " JOIN " + secondTableName + " WHERE " + conditions + ";");
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
	 * Updates a single record in the database by its PK.
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
	public <T> boolean updateById(int id, String idFieldName, String tableName, ArrayList<String> keys,
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
	 * Updates records in the database matching the condition set as the last
	 * parameter in the <code>PreparedStatement</code> prepared by the
	 * <code>IObjectToPreparedStatementParameters</code>.
	 * 
	 * @param <T>                The class of the entity to update.
	 * @param conditionFieldName Name of the field where the condition must match.
	 * @param tableName          Name of the table.
	 * @param keys               Names of the fields that need updating.
	 * @param objToPS            An instance of
	 *                           <code>IObjectToPreparedStatementParameters</code>
	 *                           that describes how to insert the object data into a
	 *                           <code>PreparedStatement</code>. <b>Must insert the
	 *                           data in the same order as the objects in the
	 *                           <code>keys</code> list. Last parameter must be the
	 *                           condition for the <code>conditionFieldName</code>
	 *                           column.</b>
	 * @return
	 */
	public <T> boolean updateAllMatchingCondition(String conditionFieldName, String tableName, ArrayList<String> keys,
			IObjectToPreparedStatementParameters<T> objToPS)
	{
		PreparedStatement ps;
		try
		{
			StringBuilder sb = new StringBuilder();
			String condition = conditionFieldName + "=?";
			sb.append("UPDATE " + tableName + " SET ");

			appendToQueryString(sb, keys, condition);
			ps = conn.prepareStatement(sb.toString());
			objToPS.convertObjectToPSQuery(ps);
			System.out.println(sb.toString());
			return ps.executeUpdate() > 0;
		} catch (Exception e)
		{
			System.out.println(e);
		}

		return false;
	}

	/**
	 * Appends the keys list and condition to the query string builder.
	 * 
	 * @param sb         StringBuilder object that contains the query.
	 * @param keys       List of field names for the data to be inserted to the
	 *                   <code>PreparedStatement</code>.
	 * @param conditions Conditions for the query (<code>WHERE ...</code>). Can be
	 *                   null or empty.
	 */
	private void appendToQueryString(StringBuilder sb, ArrayList<String> keys, String conditions)
	{
		appendKeys(sb, keys);
		if (conditions == null || conditions.isEmpty())
		{
			sb.append(";");
		} else
		{
			sb.append(" WHERE " + conditions + ";");
		}
	}

	/**
	 * Appends the keys to the string builder.
	 * 
	 * @param sb   StringBuilder object that contains the query.
	 * @param keys List of field names for the data to be inserted to the
	 *             <code>PreparedStatement</code>.
	 */
	private void appendKeys(StringBuilder sb, ArrayList<String> keys)
	{
		for (String key : keys)
		{
			sb.append(key + "=?, ");
		}
		if (sb.length() > 2)
		{
			sb.delete(sb.length() - 2, sb.length());
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
