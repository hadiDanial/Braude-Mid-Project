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


 	@Test
    public void testLogin_existingUser()
    {
		expected.setUsername("amr");
		expected.setPassword("123");
       userController.login(expected.getUsername(),expected.getPassword(), new UserResponse(expected));
	   assertTrue("",user.equals(expected));
    }

 	@Test
    public void testLogin_nullUser()
    {
		expected.setUsername(null);
		expected.setPassword("123");
       userController.login(expected.getUsername(),expected.getPassword(), new UserResponse(expected));
	   assertFalse("",user.equals(expected));
    }

	@Test
    public void testLogin_nullPassword()
    {
		expected.setUsername("amr");
		expected.setPassword(null);
       userController.login(expected.getUsername(),expected.getPassword(), new UserResponse(expected));
	   assertTrue("",user.equals(expected));        
    }

	@Test
    public void testLogin_nullUserPassword()
    {
		expected.setUsername(null);
		expected.setPassword(null);
       userController.login(expected.getUsername(),expected.getPassword(), new UserResponse(expected));
	   assertFalse("",user.equals(expected));     
    }
	@Test
    public void testLogin_blankUserPassword()
    {
		expected.setUsername("");
		expected.setPassword("");
       userController.login(expected.getUsername(),expected.getPassword(), new UserResponse(expected));
	   assertFalse("",user.equals(expected));   
    }
	@Test
    public void testLogin_blankPassword()
    {
		expected.setUsername("amr");
		expected.setPassword("");
       userController.login(expected.getUsername(),expected.getPassword(), new UserResponse(expected));
	   assertTrue("",user.equals(expected));       
    }
	@Test
    public void testLogin_blankUser()
    {
		expected.setUsername("");
		expected.setPassword("123");
       userController.login(expected.getUsername(),expected.getPassword(), new UserResponse(expected));
	   assertFalse("",user.equals(expected));  
    }

}
