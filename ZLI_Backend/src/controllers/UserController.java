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

	
	/** 
	 * @return UserController
	 */
	public static synchronized UserController getInstance()
	{
		if (instance == null)
		{
			instance = new UserController();
		}
		return instance;
	}

	
	/** function for logging in of a user with valid username and password and isn't already logged in 
	 * @param username
	 * @param password
	 * @return User
	 */
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

	
	/** function used to set database of the user that he's logged out
	 * @param userId
	 * @return boolean
	 */
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

	
	/** function used to insert a new user with his details to the database 
	 * @param user
	 * @return boolean
	 */
	public boolean register(User user)
	{
		int res = databaseConnection.insertToDatabase(Tables.USERS_TABLE_NAME, Tables.usersColumnNames,	userToRS(user, true));
		return res == 1;
	}

	
	/** function sets user details in the database 
	 * @param user
	 * @param newUser
	 * @return IObjectToPreparedStatementParameters<User>
	 */
	private IObjectToPreparedStatementParameters<User> userToRS(User user, boolean newUser)
	{
		return new IObjectToPreparedStatementParameters<User>()
		{

			@Override
			public void convertObjectToPSQuery(PreparedStatement statementToPrepare) throws SQLException
			{
				statementToPrepare.setInt(1, user.getUserId());
				statementToPrepare.setString(2, user.getUsername());
				statementToPrepare.setString(3, user.getPassword());
				statementToPrepare.setString(4, user.getFirstName());
				statementToPrepare.setString(5, user.getLastName());
				statementToPrepare.setString(6, user.getEmailAddress());
				statementToPrepare.setString(7, user.getPhoneNumber());
				statementToPrepare.setString(8, user.getRole().name());
				statementToPrepare.setString(9, user.getAccountStatus().name());
				statementToPrepare.setFloat(10, user.getCredit());
				statementToPrepare.setBoolean(11, (newUser) ? false : user.isLoggedIn());
				statementToPrepare.setTimestamp(12, (newUser) ? null : Timestamp.from(user.getLastLoginDate()));
			}
		};
	}

	
	/** function initializes user details to the database
	 * @param rs
	 * @return User
	 */
	public User convertRSToUser(ResultSet rs)
	{
		try
		{
			String[] usersColumnNames = Tables.usersColumnNames;
			if (rs.next())
			{
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

	
	/** function used to compensate/refund user with certain amount of money
	 * @param user
	 * @param totalCost
	 */
	public void updateUserCredit(User user, float totalCost)
	{
		user.setCredit(user.getCredit() + totalCost);
		databaseConnection.updateById(user.getUserId(), ID_FIELD_NAME, Tables.USERS_TABLE_NAME, "credit",
				user.getCredit() + "");
	}

	
	/** getter of the database for creditcard 
	 * @param user
	 * @return CreditCard
	 */
	public CreditCard getCreditCard(User user)
	{
		ResultSet rs = databaseConnection.getBySimpleCondition(Tables.creditCardColumnNames[1], String.valueOf(user.getUserId()),
				Tables.CREDIT_CARD_TABLE_NAME);
		try
		{
			if(rs.next())
			{
				
			CreditCard cc = new CreditCard(user, rs.getString("creditCardNumber"), rs.getInt("cvv"), rs.getTimestamp("expirationDate").toInstant(), rs.getString("cardHolderName"));
			cc.setCreditCardId(rs.getInt(1));
			rs.close();
			return cc;
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	/** 
	 * @return ArrayList<User>
	 */
	public ArrayList<User> getAllUsers()
	{
		return convertRSToUsersArray(databaseConnection.getAll(Tables.USERS_TABLE_NAME));
	}
	
	/** 
	 * @param resultSet
	 * @return ArrayList<User>
	 */
	public ArrayList<User> convertRSToUsersArray(ResultSet resultSet)
	{
		ArrayList<User> user = new ArrayList<User>();
		try
		{
			while (resultSet.next())
			{
				user.add(convertRSToUser(resultSet));
			}
			resultSet.close();
			return user;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	
	/** getter of the role of the user 
	 * @param role
	 * @return ArrayList<User>
	 */
	public ArrayList<User> getUsersByRole(UserRole role)
	{
		ResultSet rs = databaseConnection.getBySimpleCondition("role", role.name(), Tables.USERS_TABLE_NAME);
		return convertRSToUsersArray(rs);		
	}

	
	/** getter for the user id
	 * @param userId
	 * @return User
	 */
	public User getUserById(int userId)
	{
		ResultSet rs = databaseConnection.getByID(userId, Tables.USERS_TABLE_NAME, ID_FIELD_NAME);		
		return convertRSToUser(rs);
	}
}
