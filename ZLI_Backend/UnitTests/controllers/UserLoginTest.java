package controllers;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import controllers.UserController;
import database.DatabaseConnection;
import database.Tables;
import entities.users.User;
import enums.AccountStatus;
import enums.UserRole;

public class UserLoginTest {
    DatabaseConnection databaseConnection;
	User user;
	
    /** 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		DatabaseConnection.getInstance().connectToDB("localhost", "zlig13", "root", "6plle2nmfr4m");
        user=new User("amr", "123", "Amr", "Kalany", "AmrKal@gmail.com", "0504707027",
        UserRole.CEO, AccountStatus.Active, 0);
	}

	
	/** 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception
	{
		DatabaseConnection.getInstance().disconnect();
	}
    @Test
    public void testLogin()
    {
       boolean expected;
       UserController.getInstance().login(user.getUsername(),user.getPassword());
       try
       {
       ResultSet rs = databaseConnection.getByID(1, Tables.USERS_TABLE_NAME,"isLoggedIn");
       expected = rs.getBoolean(Tables.usersColumnNames[10]);
       assertEquals(expected,true);
       }catch (SQLException e) {
        e.printStackTrace();
       }
    }
}
