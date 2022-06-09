package controllers;

import static org.junit.Assert.*;

import org.hamcrest.core.IsInstanceOf;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entities.users.User;
import requests.Request;
import utility.IResponse;

public class UserControllerTest
{
	public class ClientControllerStub implements ClientControllerIF
	{

		@Override
		public <T> void sendRequest(Request request, IResponse<T> response) {
			
		}
		
	}
	UserController userController;
	User user;
	@Before
	public void setUp() throws Exception
	{
		userController = UserController.getInstance();
		user = new User();
	}

	@After
	public void tearDown() throws Exception
	{
	}
 	@Test
    public void testLogin_existingUser()
    {
       userController.login("amr","123", new IResponse<User>() {

		@Override
		public void executeAfterResponse(Object message) {
			assertNotNull(message);
		}
		
	   });
    }

    @Test
    public void testLogin_nonExistingUser()
    {
	   userController.login("stam","123", new IResponse<User>() {

		@Override
		public void executeAfterResponse(Object message) {
			assertNull(message);
		}
		
	   });
    }

    @Test
    public void testLogin_wrongPassword()
    {
		userController.login("amr","1233", new IResponse<User>() {

			@Override
			public void executeAfterResponse(Object message) {
				assertNull(message);
			}
			
		   });     
    }

	@Test
    public void testLogin_nullUser()
    {
       userController.login(null,"123", null);
       assertNull(user);
    }

   /*  @Test
    public void testLogin_nullPassword()
    {
        user=userController.login("amr",null, null);
        assertNull(user);        
    }

	@Test
    public void testLogin_nullUserPassword()
    {
        user=userController.login(null,null, null);
        assertNull(user);        
    }
	@Test
    public void testLogin_blankUserPassword()
    {
        user=userController.login("","", null);
        assertNull(user);        
    }
	@Test
    public void testLogin_blankPassword()
    {
        user=userController.login("amr","", null);
        assertNull(user);        
    }
	@Test
    public void testLogin_blankUser()
    {
        user=userController.login("","123", null);
        assertNull(user);        
    }*/

}
