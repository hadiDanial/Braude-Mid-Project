package controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.protocol.Message;
import com.mysql.cj.x.protobuf.Mysqlx.ClientMessages;

import database.DatabaseConnection;
import database.IResultSetToObject;
import entities.users.User;
import enums.AccountStatus;
import enums.ColorEnum;
import enums.UserRole;

public class UserController {
	private static UserController instance;
	private final DatabaseConnection databaseConnection;
	private static final String TABLE_NAME = "users";
	private static final String ID_FIELD_NAME = "userID";
	private IResultSetToObject<User> rsToOrder;
	private static final String[] allColumnNames =
	{ "username", "password", "firstname", "lastname", "emailaddress", "phonenumber", "role","accountstatus","credit" };

	private UserController(Message msg)
	{
		databaseConnection = DatabaseConnection.getInstance();
        rsToOrder = new IResultSetToObject<User>()
		{
			@Override
			public User convertToObject(ResultSet rs)
			{
				try
				{
					User user = new User();
					user.setUserId(rs.getInt("userid"));
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					user.setFirstName(rs.getString("firstname"));
					user.setLastName(rs.getString("lastname"));
					user.setEmailAddress(rs.getString("emailaddress"));
					user.setPhoneNumber(rs.getString("phonenumber"));
					user.setRole(UserRole.valueOf(rs.getString("role")));
                    user.setAccountStatus(AccountStatus.valueOf(rs.getString("accountstatus")));
					user.setLoggedIn(rs.getBoolean("isloggedin"));
                    
					return user;
				} catch (Exception e)
				{
					e.printStackTrace();
					return null;
				}
			}

            @Override
            public User convertToObject(ResultSet rs) {
                // TODO Auto-generated method stub
                return null;
            }
		};
		
	}

	public static UserController getInstance(Message msg)
	{
		if (instance == null)
		{
			instance = new UserController(msg);
		}
		return instance;
	}
    public User Login()
    {
        Object returnObj;
		Message msgToClient;
		ArrayList <User> returnedUser = new ArrayList<User>();
		String query = "SELECT * FROM member WHERE ID=? AND Password=?";

		try {

        }catch(SQLException sqlException)
        {

        }

    }
    public boolean Logout()
    {
        
    }
    public boolean Register()
    {
        
    }

}
