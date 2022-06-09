package controllers;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
		databaseConnection.connectToDB("localhost", "zlig13", "root", "6plle2nmfr4m"); 
        userController = UserController.getInstance();
        logOut=false;
	}

	/**function to disconnect after finishing work with database
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception
	{
		databaseConnection.disconnect();
	}

	/*Tester for login function in the usercontroller 
    * tests an existing (valid) user from the database 
    /*expecting test should be a true output
    */
    @Test
    public void testLogin_existingUser()
    {
       boolean expected;
       user=userController.login("amr","123");
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

    /*Tester for login function in the usercontroller 
    * tests a non existing (invalid) user from the database 
    /*expecting test should be a null output
    */
    @Test
    public void testLogin_nonExistingUser()
    {
       user=userController.login("stam","123");
       assertNull(user);
    }

    /*Tester for login function in the usercontroller 
    * tests an existing (invalid) username but with a wrong password 
    /*expecting test should be a null output
    */
    @Test
    public void testLogin_wrongPassword()
    {
        user=userController.login("amr","1233");
        assertNull(user);        
    }

    /*Tester for login function in the usercontroller 
    * tests a nonexisting (invalid) input from the database (null username)
    /*expecting test should be a null output
    */
    @Test
    public void testLogin_nullUser()
    {
       user=userController.login(null,"123");
       assertNull(user);
    }

    /*Tester for login function in the usercontroller 
    * tests a nonexisting (invalid) user from the database (null password) 
    /*expecting test should be a null output 
    */
    @Test
    public void testLogin_nullPassword()
    {
        user=userController.login("amr",null);
        assertNull(user);        
    }

    /*Tester for login function in the usercontroller 
    * tests a nonexisting (invalid) user from the database (null username and password) 
    /*expecting test should be a null output
    */
	@Test
    public void testLogin_nullUserPassword()
    {
        user=userController.login(null,null);
        assertNull(user);        
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

    /*Tester for login function in the usercontroller 
    * tests a nonexisting (invalid) user from the database with a blank password  
    /*expecting test should be a null output
    */
	@Test
    public void testLogin_blankPassword()
    {
        user=userController.login("amr","");
        assertNull(user);        
    }

    /*Tester for login function in the usercontroller 
    * tests a nonexisting (invalid) user from the database with a blank username 
    /*expecting test should be a null output
    */
	@Test
    public void testLogin_blankUser()
    {
        user=userController.login("","123");
        assertNull(user);        
    }

    /*Tester for logout function in the usercontroller 
     *tests the user's logging out if it worked
     */
    @Test
    public void testLogin_loggingOut()
    {
        logOut=UserController.getInstance().logout(1);
        assertFalse(logOut);
    }
}
