package controllers;

import entities.users.User;
import enums.*;
import requests.Request;
import requests.RequestType;
import utility.IResponse;

public class UserController
{
	public static UserController instance;
	private UserController()
	{
		
	}
	
	public static UserController getInstance()
	{
		if(instance == null)
		{
			instance = new UserController();
		}
		return instance;
	}
	/**
	 * 
	 * @param user
	 */
	public boolean createUser(User user)
	{
		// TODO - implement UserController.createUser
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param userId
	 * @param editedUser
	 */
	public boolean editUser(int userId, User editedUser)
	{
		// TODO - implement UserController.editUser
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param userLogin
	 * @param password
	 */
	public void login(String username, String password, IResponse<User> response)
	{
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		Request req = new Request(RequestType.Login, user);
		ClientController.getInstance().sendRequest(req, response);
	}

	/**
	 * 
	 * @param userId
	 */
	public boolean logout(int userId)
	{
		// TODO - implement UserController.logout
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param userId
	 * @param accountStatus
	 */
	public boolean setUserStatus(User userId, AccountStatus accountStatus)
	{
		// TODO - implement UserController.setUserStatus
		throw new UnsupportedOperationException();
	}

}