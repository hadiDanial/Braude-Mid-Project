package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;

import gui.ServerUI;

public class DataBase {
    // TODO implement the DataBase class

    private static Connection conn = null;

    private static DataBase instance = null;

    public DataBase() {
        instance = this;
    }

    public static DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase();
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

            // ! ServerUI.consoleTxtList.add("Driver definition succeed");

            System.out.println("Driver definition succeed");
        } catch (Exception e) {
            System.out.println("Driver definition failed");
            throw e;
        }

        try {
            // * ("jdbc:mysql://192.168.3.68/test?serverTimezone=IST","root","password")
            // * ("jdbc:mysql://localhost/test?serverTimezone=IST","root","password")

            conn = DriverManager.getConnection(
                    "jdbc:mysql://" + hostIP + "/" + serverName + "?serverTimezone=IST",
                    user, password);

            // ! ServerUI.consoleTxtList.add("SQL connection succeed");

            System.out.println("SQL connection succeed");

        } catch (SQLException e) {/* handle any errors */
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            throw e;
        }
    }

    public void sendNewOrderToDB(ArrayList<String> data) {
        PreparedStatement ps;
        try {
            if (data.size() != 8)
                throw new Exception("data size is not compatible");
            ps = conn.prepareStatement("INSERT INTO  Orders VALUES(?,?,?,?,?,?,?,?);");
            for (int i = 0; i < data.size(); i++) {
                ps.setString(i + 1, data.get(i));
            }
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateColorDateOrderInDB(String orderID, ArrayList<String> data) {
        PreparedStatement ps;
        try {
            if (data.size() != 2)
                throw new Exception("data size is not compatible");
            ps = conn.prepareStatement(
                    "UPDATE  Orders SET color= ?, date= ? WHERE orderNumber=?"
                            + orderID + ";");
            for (int i = 0; i < data.size(); i++) {
                ps.setString(i + 1, data.get(i));
            }
            ps.setString(3, orderID);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateColorOrderInDB(String orderID, String data) {
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(
                    "UPDATE  Orders SET color= ? WHERE orderNumber=?" + orderID + ";");

            ps.setString(1, data);

            ps.setString(2, orderID);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateDateOrderInDB(String orderID, String data) {
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(
                    "UPDATE  Orders SET date= ? WHERE orderNumber=?" + orderID + ";");

            ps.setString(1, data);

            ps.setString(2, orderID);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ArrayList<String> getOrderFromDB(String orderID) {
        Statement stmt;
        ArrayList<String> data = new ArrayList<String>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT FROM Orders WHERE orderNumber=" + orderID + ";");
            while (rs.next()) {
                data.add(rs.getString(1));
                data.add(rs.getString(2));
                data.add(rs.getString(3));
                data.add(rs.getString(4));
                data.add(rs.getString(5));
                data.add(rs.getString(6));
                data.add(rs.getString(7));
                data.add(rs.getString(8));
            }
            rs.close();
            return data;
        } catch (Exception e) {
            return null;
        }
    }

    private void resetOrdersTable() {
        Statement st;
        try {
            st = conn.createStatement();
            boolean rs = st.execute("DROP TABLE IF EXISTS `Orders`;"
                    + "create table `Orders`("
                    + "`orderNumber` int primary key,"
                    + "`price` float,"
                    + "`greetingCard` varchar(256),"
                    + "`color` varchar(32),"
                    + "`dOrder` varchar(256),"
                    + "`shop` varchar(32),"
                    + "`date` datetime,"
                    + "`orderDate` datetime);");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
