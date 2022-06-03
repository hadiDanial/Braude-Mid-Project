package controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;

import database.DatabaseConnection;
import database.IObjectToPreparedStatementParameters;
import database.Tables;
import entities.users.CreditCard;
import entities.users.User;
import enums.AccountStatus;
import enums.UserRole;

public class UserController
{
	private static UserController instance;
	private final DatabaseConnection databaseConnection;
	private static final String ID_FIELD_NAME = "userId";

	private UserController()
	{
		databaseConnection = DatabaseConnection.getInstance();
	}

	public static synchronized UserController getInstance()
	{
		if (instance == null)
		{
			instance = new UserController();
		}
		return instance;
	}

	public User login(String username, String password)
	{
		ResultSet userRS = databaseConnection.getBySimpleCondition(Tables.usersColumnNames[1], username,
				Tables.USERS_TABLE_NAME);

		User user = convertRSToUser(userRS);
		if (user == null || user.isLoggedIn())
			return null;
		if (password.equals(user.getPassword()))
		{
			user.setLoggedIn(true);
			user.setLastLoginDate(Instant.now());
			ArrayList<String> keys = new ArrayList<String>();
			keys.add("isLoggedIn");
			keys.add("lastLoginDate");
			databaseConnection.updateById(user.getUserId(), ID_FIELD_NAME, Tables.USERS_TABLE_NAME, keys,
					new IObjectToPreparedStatementParameters<User>()
					{
						@Override
						public void convertObjectToPSQuery(PreparedStatement statementToPrepare) throws SQLException
						{
							statementToPrepare.setBoolean(1, true);
							statementToPrepare.setTimestamp(2, Timestamp.from(user.getLastLoginDate()));
						}
					});
			user.setPassword(""); // Client shouldn't have the user's password... we should hash it
			return user;
		}
		return null;

	}

	public boolean logout(int userId)
	{
		ArrayList<String> keys = new ArrayList<>();
		keys.add("isLoggedIn");
		databaseConnection.updateById(userId, ID_FIELD_NAME, Tables.USERS_TABLE_NAME, keys,
				new IObjectToPreparedStatementParameters<User>()
				{

					@Override
					public void convertObjectToPSQuery(PreparedStatement statementToPrepare) throws SQLException
					{
						statementToPrepare.setBoolean(1, false);
					}
				});
		return false;
	}

	public boolean register(User user)
	{
		int res = databaseConnection.insertToDatabase(Tables.USERS_TABLE_NAME,
				Arrays.copyOfRange(Tables.usersColumnNames, 1, Tables.usersColumnNames.length),
				new IObjectToPreparedStatementParameters<User>()
				{

					@Override
					public void convertObjectToPSQuery(PreparedStatement statementToPrepare) throws SQLException
					{
						statementToPrepare.setString(1, user.getUsername());
						statementToPrepare.setString(2, user.getPassword());
						statementToPrepare.setString(3, user.getFirstName());
						statementToPrepare.setString(4, user.getLastName());
						statementToPrepare.setString(5, user.getEmailAddress());
						statementToPrepare.setString(6, user.getPhoneNumber());
						statementToPrepare.setString(7, user.getRole().name());
						statementToPrepare.setString(8, user.getAccountStatus().name());
						statementToPrepare.setFloat(9, user.getCredit());
						statementToPrepare.setBoolean(10, false);
						statementToPrepare.setTimestamp(11, null);
					}
				});
		return res == 1;
	}

	public User convertRSToUser(ResultSet rs)
	{
		try
		{
			String[] usersColumnNames = Tables.usersColumnNames;
			if (rs.next())
			{
//				{ "userId", "username", "password", "firstName", "lastName", "emailAddress", "phoneNumber", "role", "status",
//					"credit", "isLoggedIn", "lastLoginDate" };
				User user = new User();
				user.setUserId(rs.getInt(usersColumnNames[0]));
				user.setUsername(rs.getString(usersColumnNames[1]));
				user.setPassword(rs.getString(usersColumnNames[2]));
				user.setFirstName(rs.getString(usersColumnNames[3]));
				user.setLastName(rs.getString(usersColumnNames[4]));
				user.setEmailAddress(rs.getString(usersColumnNames[5]));
				user.setPhoneNumber(rs.getString(usersColumnNames[6]));
				user.setRole(UserRole.valueOf(rs.getString(usersColumnNames[7])));
				user.setAccountStatus(AccountStatus.valueOf(rs.getString(usersColumnNames[8])));
				user.setCredit(rs.getFloat(usersColumnNames[9]));
				user.setLoggedIn(rs.getBoolean(usersColumnNames[10]));
				Timestamp lastLogin = rs.getTimestamp(usersColumnNames[11]);
				user.setLastLoginDate((lastLogin == null) ? null : lastLogin.toInstant());
				return user;
			} else
				return null;
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public void updateUserCredit(User user, float totalCost)
	{
		user.setCredit(user.getCredit() + totalCost);
		databaseConnection.updateById(user.getUserId(), ID_FIELD_NAME, Tables.USERS_TABLE_NAME, "credit",
				user.getCredit() + "");
	}

	public CreditCard getCreditCard(User user)
	{
		ResultSet rs = databaseConnection.getBySimpleCondition(Tables.creditCardColumnNames[1], String.valueOf(user.getUserId()),
				Tables.CREDIT_CARD_TABLE_NAME);
		try
		{
			CreditCard cc = new CreditCard(user, rs.getInt(2), rs.getInt(3), rs.getTimestamp(4).toInstant(), rs.getString(5));
			cc.setCreditCardId(rs.getInt(1));
			rs.close();
			return cc;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
