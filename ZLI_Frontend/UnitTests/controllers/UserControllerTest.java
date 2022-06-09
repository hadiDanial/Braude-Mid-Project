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
		boolean result;
		@Override
		public <T> void sendRequest(Request request, IResponse<T> response) {
			response.executeAfterResponse(user);
		}
		
	}
	public class UserResponse implements IResponse<User>{

		@Override
		public void executeAfterResponse(Object message) {
			user1 = (User) message;
		}

	}
	UserController userController;
	User user;
	User user1;
	boolean result;

	@Before
	public void setUp() throws Exception
	{
		userController = UserController.getInstance(new ClientControllerStub());
		user = new User();
	}

	@After
	public void tearDown() throws Exception
	{
	}


 	@Test
    public void testLogin_existingUser()
    {
       userController.login("amr","123", new UserResponse());
    }

    @Test
    public void testLogin_nonExistingUser()
    {
	   userController.login("stam","123", new UserResponse());
    }

    @Test
    public void testLogin_wrongPassword()
    {
		user1 = new User();
		user = new User();
		userController.login("amr","1233",new UserResponse());
			Assert.assertEquals(user1, user);
    }

 	@Test
    public void testLogin_nullUser()
    {
       userController.login(null,"123", new UserResponse());
       assertNotNull(user);
    }

	@Test
    public void testLogin_nullPassword()
    {
        userController.login("amr",null, new UserResponse());
        assertNotNull(user);        
    }

	@Test
    public void testLogin_nullUserPassword()
    {
        userController.login(null,null, new UserResponse());
        assertNotNull(user);        
    }
	@Test
    public void testLogin_blankUserPassword()
    {
        userController.login("","", new UserResponse());
        assertNotNull(user);        
    }
	@Test
    public void testLogin_blankPassword()
    {
        userController.login("amr","", new UserResponse());
        assertNotNull(user);        
    }
	@Test
    public void testLogin_blankUser()
    {
        userController.login("","123", new UserResponse());
        assertNotNull(user);       
    }

}
