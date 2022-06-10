package controllers;

import static org.junit.Assert.*;

import org.hamcrest.core.IsInstanceOf;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.concurrent.*;

import javax.naming.spi.DirStateFactory.Result;

import entities.users.User;
import requests.Request;
import utility.IResponse;

public class UserControllerTest
{
	public class ClientControllerStub implements ClientControllerIF
	{
		@Override
		public <T> void sendRequest(Request request, IResponse<T> response) {
			response.executeAfterResponse(expected);
		}
		
	}
	public class UserResponse implements IResponse<User>{

		private User user;

		@Override
		public void executeAfterResponse(Object message) {
			expected = this.user;
		}

		public UserResponse(User user) {
			this.user=user;
		}

	}
	UserController userController;
	User user;
	User expected;
	boolean result;

	@Before
	public void setUp() throws Exception
	{
		userController = UserController.getInstance(new ClientControllerStub());
		user = new User();
		expected = new User();
		user.setUsername("amr");
		user.setPassword("123");
	}

	@After
	public void tearDown() throws Exception
	{
	}

	/* 
	 * Functionality: Tests logging in with user and a valid username and password.<br>
	 * Input data: Username="amr", password="123", User "amr".<br>
	 * Expected result: successfully logged with user.
	*/
 	@Test
    public void testLogin_existingUser()
    {
		expected.setUsername("amr");
		expected.setPassword("123");
       userController.login(expected.getUsername(),expected.getPassword(), new UserResponse(expected));
	   assertTrue("successful User",user.equals(expected));
    }
	/* 
	*Functionality: Tests logging in with a null username.<br>
	* Input data: null username, password="123".<br>
	* Expected result: Failed to login.
	*/
 	@Test
    public void testLogin_nullUser()
    {
		expected.setUsername(null);
		expected.setPassword("123");
       userController.login(expected.getUsername(),expected.getPassword(), new UserResponse(expected));
	   assertFalse("",user.equals(expected));
    }
	/* 
	*Functionality: Tests logging in with a null username.<br>
	* Input data: null username, password="123".<br>
	* Expected result: null pointer exception thrown, failed to login.
	*/
	@Test
    public void testLogin_nullPassword()
    {
		expected.setUsername("amr");
		expected.setPassword(null);
       userController.login(expected.getUsername(),expected.getPassword(), new UserResponse(expected));
	   assertTrue("",user.equals(expected));        
    }
	/* 
	 * Functionality: Tests logging in with a null password.<br>
	 * Input data: Username="amr", null password.<br>
	 * Expected result: Failed.
	*/
	@Test
    public void testLogin_nullUserPassword()
    {
		expected.setUsername(null);
		expected.setPassword(null);
       userController.login(expected.getUsername(),expected.getPassword(), new UserResponse(expected));
	   assertFalse("",user.equals(expected));     
    }
		/* 
    *Tester for login function in the usercontroller 
    * tests a (invalid) user with blank input  
    *expecting test should be a false
	*/
	@Test
    public void testLogin_blankUserPassword()
    {
		expected.setUsername("");
		expected.setPassword("");
       userController.login(expected.getUsername(),expected.getPassword(), new UserResponse(expected));
	   assertFalse("",user.equals(expected));   
    }
		/* 
	 * Functionality: Tests logging in with a blank password.<br>
	 * Input data: Username="amr", password="".<br>
	 * Expected result: failed to login.
	*/
	@Test
    public void testLogin_blankPassword()
    {
		expected.setUsername("amr");
		expected.setPassword("");
       userController.login(expected.getUsername(),expected.getPassword(), new UserResponse(expected));
	   assertTrue("",user.equals(expected));       
    }
		/* 
	 * Functionality: Tests logging in with a blank username.<br>
	 * Input data: blank username (""), password="123".<br>
	 * Expected result:failed to login.
	*/
	@Test
    public void testLogin_blankUser()
    {
		expected.setUsername("");
		expected.setPassword("123");
       userController.login(expected.getUsername(),expected.getPassword(), new UserResponse(expected));
	   assertFalse("",user.equals(expected));  
    }

}
