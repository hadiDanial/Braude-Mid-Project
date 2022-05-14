package controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;

import com.mysql.cj.protocol.Message;

import database.DatabaseConnection;
import database.IObjectToPreparedStatementParameters;
import database.IResultSetToObject;
import entities.users.User;
import enums.AccountStatus;
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

	public User Login(String username, String password)
	{
		User user =databaseConnection.getBySimpleCondition(allColumnNames[0],username, TABLE_NAME,rsToUser);
		if(user==null)
			return null;
		if(password.equals(user.getPassword()) && !user.isLoggedIn()){
			user.setLoggedIn(true);
			user.setLastLoginDate(Instant.now());
			ArrayList<String> keys = new ArrayList<String>();
			keys.add("isLoggedIn");
			keys.add("lastLoginDate");
			databaseConnection.updateById(user.getUserId(), ID_FIELD_NAME, TABLE_NAME, keys,
				new IObjectToPreparedStatementParameters<User>()
				{
					@Override
					public void convertObjectToPSQuery(PreparedStatement statementToPrepare) throws SQLException
					{
						statementToPrepare.setBoolean(1, true);
						statementToPrepare.setTimestamp(2, Timestamp.from(user.getLastLoginDate()));
					}
				});
			return user;
		}
		return null;

	}

	public boolean Logout(int userId)
	{
		ArrayList<String> keys=new ArrayList<>();
		keys.add("isLoggedIn");
		databaseConnection.updateById(userId, ID_FIELD_NAME, TABLE_NAME,keys, new IObjectToPreparedStatementParameters<User>() {

			@Override
			public void convertObjectToPSQuery(PreparedStatement statementToPrepare) throws SQLException {
				statementToPrepare.setBoolean(1, false);
			}
		});
		return false;
	}

	public boolean Register(User user)
	{	//"username", "password", "firstName", "lastName", "emailAddress", "phoneNumber", "role", "accountStatus", "credit",
	//"isLoggedIn", "lastLoginDate"
		int res=databaseConnection.insertToDatabase(TABLE_NAME, allColumnNames, new IObjectToPreparedStatementParameters<User>() {

			@Override
			public void convertObjectToPSQuery(PreparedStatement statementToPrepare) throws SQLException {
					statementToPrepare.setBoolean(1,true);	
					statementToPrepare.setString(1, user.getUsername());
					statementToPrepare.setString(2, user.getPassword());
					statementToPrepare.setString(3, user.getFirstName());
					statementToPrepare.setString(4, user.getLastName());
					statementToPrepare.setString(5, user.getEmailAddress());
					statementToPrepare.setString(6, user.getPhoneNumber());
					statementToPrepare.setString(7, user.getRole().name());
					statementToPrepare.setString(8, user.getAccountStatus().name());
					statementToPrepare.setFloat(9, user.getCredit());
					statementToPrepare.setBoolean(10,false);	
					statementToPrepare.setTimestamp(11,null);
			}
		});
		return res==1;
	}

}
