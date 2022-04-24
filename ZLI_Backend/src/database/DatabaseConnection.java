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

public class DatabaseConnection {
	// TODO implement the DataBase class

	private static Connection conn = null;

	private static DatabaseConnection instance = null;

	public DatabaseConnection() {
		instance = this;
	}

	public static DatabaseConnection getInstance() {
		if (instance == null) {
			instance = new DatabaseConnection();
		}
		return instance;
	}

	/**
	 * this function connects to the data base
	 * 
	 * @param hostIP     the ip of the SQL data base usually it is localhost
	 * @param serverName the data base name
	 * @param user       the user that connects to the data base
	 * @param password   the password used to connect to the data base
	 * @throws Exception exceptions of type Exception and SQLException
	 */
	public void connectToDB(String hostIP, String serverName, String user, String password) throws Exception {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			ServerUI.consoleTxtList.add(new ConsoleString("Driver definition succeed"));
			System.out.println("Driver definition succeed");
		} catch (Exception e) {
			ServerUI.consoleTxtList.add(new ConsoleString("Driver definition failed"));
			System.out.println("Driver definition failed");
			throw e;
		}

		try {
			conn = DriverManager.getConnection("jdbc:mysql://" + hostIP + "/" + serverName + "?serverTimezone=IST",
					user, password);

			ServerUI.consoleTxtList.add(new ConsoleString("SQL connection succeed"));
			System.out.println("SQL connection succeed");

		} catch (SQLException e) {/* handle any errors */
			ServerUI.consoleTxtList.add(new ConsoleString("SQLException: " + e.getMessage()));
			ServerUI.consoleTxtList.add(new ConsoleString("SQLState: " + e.getSQLState()));
			ServerUI.consoleTxtList.add(new ConsoleString("VendorError: " + e.getErrorCode()));
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
			throw e;
		}
	}

	public int insertToDatabase(ArrayList<String> data, String tableName) {
		PreparedStatement ps;
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ");
		sb.append(tableName);
		sb.append(" VALUES(");
		for (int i = 0; i < data.size(); i++) {
			if (i == data.size() - 1)
				sb.append("?");
			else
				sb.append("?,");
		}
		sb.append(");");

		try {
			ps = conn.prepareStatement(sb.toString());
			for (int i = 0; i < data.size(); i++) {
				ps.setString(i + 1, data.get(i));
			}
			return ps.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
			return -1;
		}
	}

	public <T> T getByID(int id, String tableName, String idFieldName, IResultSetToObject<T> rsToObject) {
		Statement stmt;
		T item = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT FROM " + tableName + " WHERE " + idFieldName + "=" + id + ";");
			if (rs.next()) {
				item = rsToObject.convertToObject(rs);
			}
			rs.close();
			return item;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public <T> ArrayList<T> getAllFromDB(String tableName, IResultSetToObject<T> rsToObject) {
		Statement stmt;
		ArrayList<T> list = new ArrayList<T>();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + ";");
			while (rs.next()) {
				list.add(rsToObject.convertToObject(rs));
			}
			rs.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public <T> boolean updateInDB(int id, String idFieldName, String tableName, ArrayList<String> keys,
			IObjectToPreparedStatementParameters<T> objToPS) {
		PreparedStatement ps;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE " + tableName + " SET ");

			for (String key : keys) {
				sb.append(key + "=?, ");
			}
			sb.delete(sb.length() - 2, sb.length());
			sb.append(" WHERE " + idFieldName + "=" + id + ";");
			ps = conn.prepareStatement(sb.toString());
			objToPS.convertObjectToPSQuery(ps);
			System.out.println("Update:\n" + sb.toString());
			return ps.executeUpdate() == 1;
		} catch (Exception e) {
			System.out.println(e);
		}

		return false;
	}

	public void updateOrderInDB(String orderID, ArrayList<String> data) {
		PreparedStatement ps;
		try {
			if (data.size() != 2)
				throw new Exception("data size is not compatible");
			ps = conn.prepareStatement("UPDATE  Orders SET color= ?, date= ? WHERE orderNumber=?" + orderID + ";");
			for (int i = 0; i < data.size(); i++) {
				ps.setString(i + 1, data.get(i));
			}
			ps.setString(3, orderID);
			ps.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * public void sendNewOrderToDB(ArrayList<String> data)
	 * {
	 * PreparedStatement ps;
	 * try
	 * {
	 * if (data.size() != 8)
	 * throw new Exception("data size is not compatible");
	 * ps = conn.prepareStatement("INSERT INTO Orders VALUES(?,?,?,?,?,?,?,?);");
	 * for (int i = 0; i < data.size(); i++)
	 * {
	 * ps.setString(i + 1, data.get(i));
	 * }
	 * ps.executeUpdate();
	 * 
	 * } catch (Exception e)
	 * {
	 * System.out.println(e);
	 * }
	 * }
	 * 
	 * public ArrayList<Order> getAllOrdersFromDB(IResultSetToObject<Order>
	 * rsToOrder)
	 * {
	 * Statement stmt;
	 * ArrayList<Order> orders = new ArrayList<Order>();
	 * try
	 * {
	 * stmt = conn.createStatement();
	 * ResultSet rs = stmt.executeQuery("SELECT * FROM Orders;");
	 * while(rs.next())
	 * {
	 * orders.add(rsToOrder.convertToObject(rs));
	 * }
	 * rs.close();
	 * return orders;
	 * } catch (Exception e)
	 * {
	 * return null;
	 * }
	 * }
	 * 
	 * public HashMap<String, String> getOrderFromDB(String orderID)
	 * {
	 * Statement stmt;
	 * HashMap<String, String> data = new HashMap<String, String>();
	 * try
	 * {
	 * stmt = conn.createStatement();
	 * ResultSet rs = stmt.executeQuery("SELECT FROM Orders WHERE orderNumber=" +
	 * orderID + ";");
	 * while (rs.next())
	 * {
	 * data.put("orderNumber", rs.getString("orderNumber"));
	 * data.put("price", rs.getString("price"));
	 * data.put("greetingCard", rs.getString("greetingCard"));
	 * data.put("color", rs.getString("color"));
	 * data.put("dOrder", rs.getString("dOrder"));
	 * data.put("shop", rs.getString("shop"));
	 * data.put("date", rs.getString("date"));
	 * data.put("orderDate", rs.getString("orderDate"));
	 * 
	 * }
	 * rs.close();
	 * return data;
	 * } catch (Exception e)
	 * {
	 * return null;
	 * }
	 * }
	 */

	private void resetOrdersTable() {
		Statement st;
		try {
			st = conn.createStatement();
			boolean rs = st.execute("DROP TABLE IF EXISTS `Orders`;"
					+ "create table `Orders`("
					+ "`orderNumber` int primary key AUTO_INCREMENT,"
					+ "`price` float,"
					+ "`greetingCard` varchar(256),"
					+ "`color` varchar(32),"
					+ "`dOrder` varchar(256),"
					+ "`shop` varchar(32),"
					+ "`date` timestamp,"
					+ "`orderDate` timestamp);");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
