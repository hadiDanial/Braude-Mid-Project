package controllers;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
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
		databaseConnection = DatabaseConnection.getInstance();
		databaseConnection.connectToDB("localhost", "zlig13", "root", "6plle2nmfr4m"); 
	}

	/** 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception
	{
		databaseConnection.disconnect();
	}
	
    @Test
    public void testLogin_existingUser()
    {
       boolean expected;
       user=UserController.getInstance().login("amr","123");
       try
       {
       ResultSet rs = databaseConnection.getByID(1, Tables.USERS_TABLE_NAME,"isLoggedIn");
       if(rs.next())
       {
       expected = rs.getBoolean(Tables.usersColumnNames[10]);
       assertTrue(expected);
       }
       else
       {
    	   Assert.fail();
       }
       }catch (SQLException e) {
        e.printStackTrace();
       }
    }

    @Test
    public void testLogin_nonExistingUser()
    {
       user=UserController.getInstance().login("stam","123");
       assertNull(user);
    }

    @Test
    public void testLogin_wrongPassword()
    {
        user=UserController.getInstance().login("amr","1233");
        assertNull(user);        
    }
}
