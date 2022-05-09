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

public class UserController
{
	private static UserController instance;
	private final DatabaseConnection databaseConnection;
	private static final String TABLE_NAME = "Users";
	private static final String ID_FIELD_NAME = "userId";
	private IResultSetToObject<User> rsToUser;
	private static final String[] allColumnNames =
	{ "username", "password", "firstName", "lastName", "emailAddress", "phoneNumber", "role", "accountStatus", "credit",
			"isLoggedIn", "lastLoginDate" };

	private UserController(Message msg)
	{
		databaseConnection = DatabaseConnection.getInstance();
		rsToUser = new IResultSetToObject<User>()
		{
			@Override
			public User convertToObject(ResultSet rs)
			{
				try
				{
					User user = new User();
					user.setUserId(rs.getInt("userId"));
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					user.setFirstName(rs.getString("firstName"));
					user.setLastName(rs.getString("lastName"));
					user.setEmailAddress(rs.getString("emailAddress"));
					user.setPhoneNumber(rs.getString("phoneNumber"));
					user.setRole(UserRole.valueOf(rs.getString("role")));
					user.setAccountStatus(AccountStatus.valueOf(rs.getString("accountStatus")));
					user.setLoggedIn(rs.getBoolean("isLoggedIn"));
					user.setLastLoginDate(rs.getTimestamp("lastLoginDate").toInstant());
					return user;
				} catch (Exception e)
				{
					e.printStackTrace();
					return null;
				}
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
		ArrayList<User> returnedUser = new ArrayList<User>();
		String query = "SELECT * FROM member WHERE ID=? AND Password=?";

//		try {
//
//        }catch(SQLException sqlException)
//        {
//
//        }
		return null;

	}

	public boolean Logout()
	{
		return false;
	}

	public boolean Register()
	{
		return false;
	}

}
