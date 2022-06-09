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
		}
		
	}
	UserController userController;
	User user;
	User user1;
	boolean result;

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
		CompletableFuture completableFuture =new CompletableFuture<User>();
		user1 = null;
		user = new User();
		userController.login("amr","1233", new IResponse<User>() {

			@Override
			public void executeAfterResponse(Object message) {
				user1 = (User) message;
				completableFuture.complete(user1);
			}
		   });
		   try {
			Thread.currentThread().sleep(2000);
			completableFuture.get(2,TimeUnit.SECONDS);
			Assert.assertEquals(user1, user);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

/* 	@Test
    public void testLogin_nullUser()
    {
       userController.login(null,"123", null);
       assertNull(user);
    }

	@Test
    public void testLogin_nullPassword()
    {
        userController.login("amr",null, null);
        assertNull(user);        
    }

	@Test
    public void testLogin_nullUserPassword()
    {
        userController.login(null,null, null);
        assertNull(user);        
    }
	@Test
    public void testLogin_blankUserPassword()
    {
        userController.login("","", null);
        assertNull(user);        
    }
	@Test
    public void testLogin_blankPassword()
    {
        userController.login("amr","", null);
        assertNull(user);        
    }
	@Test
    public void testLogin_blankUser()
    {
        userController.login("","123", null);
        assertNull(user);       
    }*/

}
