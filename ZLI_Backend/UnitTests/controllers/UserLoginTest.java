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
import database.DatabaseConnection;
import database.Tables;
import entities.users.User;


public class UserLoginTest {
    DatabaseConnection databaseConnection;
	User user;
    UserController userController;
    boolean logOut;
	
    /** setup for connection to a real database 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		databaseConnection = DatabaseConnection.getInstance();
		databaseConnection.connectToDB("localhost", "zlig13", "root", "mYtsb46Ql97"); 
        userController = UserController.getInstance();
        logOut=false;
	}

	/**function to disconnect after finishing work with database
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception
	{
		if(user != null)
			userController.logout(user.getUserId());				
		databaseConnection.disconnect();
	}

	/**
	 * Functionality: Tests logging in with an existing user and a valid username and password.<br>
	 * Input data: Username="amr", password="123", User "amr" in the database.<br>
	 * Expected result: successfully logged with user.
	 */
    @Test
    public void testLogin_existingUser()
    {
       User expected = new User();
       expected.setUserId(1);
       expected.setUsername("amr");
       user = userController.login("amr","123");
       assertEquals(expected.getUserId(), user.getUserId());
       assertEquals(user.isLoggedIn(), true);
    }
    /**
     * Functionality: Tests if the logged-in value is updated in the database after a successful login.<br>
     * Input data: Username="amr", password="123", User "amr" in the database.<br>
     * Expected result: successfully logged with user, isLoggedIn changed to true in the database.
     */
    @Test
    public void testLogin_existingUser_IsLoggedInDB()
    {    	
    	user = userController.login("amr","123");
    	ResultSet rs = databaseConnection.getByID(user.getUserId(), Tables.USERS_TABLE_NAME, "userId");
        try
		{
			if(rs.next())
			 {
				 assertEquals(rs.getBoolean("isLoggedIn"), true);				 
			 }
			else
			{
				Assert.fail();
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}         
        userController.logout(user.getUserId());
    }

	/**
	 * Functionality: Tests that logging out correctly updates the database.<br>
	 * Input data: Username="amr", password="123", User "amr" in the database.<br>
	 * Expected result: null output, failed to login.
	 */
    @Test
    public void testLogin_loggingOut()
    {
    	    	
    	 user = userController.login("amr","123");
         userController.logout(user.getUserId());
         ResultSet rs = databaseConnection.getByID(user.getUserId(), Tables.USERS_TABLE_NAME, "userId");
         try
		{
			if(rs.next())
			 {
				 assertEquals(rs.getBoolean("isLoggedIn"), false);				 
			 }
			else
			{
				Assert.fail();
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}         
    }
    
	/**
	 * Functionality: Tests logging in with a non-existing user.<br>
	 * Input data: Username="stam", password="123", User "amr" in the database.<br>
	 * Expected result: null output, failed to login.
	 */
    @Test
    public void testLogin_nonExistingUser()
    {
       user = userController.login("stam","123");
       assertNull(user);
    }


	/**
	 * Functionality: Tests logging in with an existing user and a valid username and an invalid password.<br>
	 * Input data: Username="amr", password="1234", User "amr" in the database.<br>
	 * Expected result: null output, failed to login.
	 */
    @Test
    public void testLogin_wrongPassword()
    {
        user = userController.login("amr","1234");
        assertNull(user);        
    }

	/**
	 * Functionality: Tests logging in with a null username.<br>
	 * Input data: null username, password="123".<br>
	 * Expected result: null pointer exception thrown, failed to login.
	 */
    @Test(expected = NullPointerException.class)
    public void testLogin_nullUser()
    {
       user=userController.login(null,"123");
    }

    /**
	 * Functionality: Tests logging in with a null password.<br>
	 * Input data: Username="amr", null password.<br>
	 * Expected result: null pointer exception thrown, failed to login.
	 */
    @Test(expected = NullPointerException.class)
    public void testLogin_nullPassword()
    {
        user = userController.login("amr",null);
    }

    /**
	 * Functionality: Tests logging in with null inputs.<br>
	 * Input data: null username, null password.<br>
	 * Expected result: null pointer exception thrown, failed to login.
	 */
    @Test(expected = NullPointerException.class)
    public void testLogin_nullUserPassword()
    {
        user = userController.login(null,null);
    }

    /*Tester for login function in the usercontroller 
    * tests a nonexisting (invalid) user from the database with blank input  
    /*expecting test should be a null output
    */
	@Test
    public void testLogin_blankUserPassword()
    {
        user=userController.login("","");
        assertNull(user);        
    }

	/**
	 * Functionality: Tests logging in with a blank password.<br>
	 * Input data: Username="amr", password="".<br>
	 * Expected result: null output, failed to login.
	 */
	@Test
    public void testLogin_blankPassword()
    {
        user=userController.login("amr","");
        assertNull(user);        
    }

	/**
	 * Functionality: Tests logging in with a blank username.<br>
	 * Input data: blank username (""), password="123".<br>
	 * Expected result: null output, failed to login.
	 */
	@Test
    public void testLogin_blankUser()
    {
        user = userController.login("","123");
        assertNull(user);        
    }

}
