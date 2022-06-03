package controllers;

import entities.users.User;
import enums.*;
import gui.guimanagement.GUIPages;
import gui.guimanagement.SceneManager;
import requests.Request;
import requests.RequestType;
import utility.IResponse;

public class UserController
{
	public static UserController instance;
	private User loggedInUser = null;

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
	 */
	public void logout()
	{
		if(isLoggedIn())			
		{
			Request req = new Request(RequestType.Logout, loggedInUser.getUserId());
			ClientController.getInstance().sendRequest(req, null);
			loggedInUser = null;
		}
	}


	public boolean setUserStatus(User user, AccountStatus accountStatus)
	{
		// TODO - implement UserController.setUserStatus
		throw new UnsupportedOperationException();
	}

	public void openHomePage()
	{
		switch (loggedInUser.getRole())
		{
		case Customer:
			SceneManager.loadNewScene(GUIPages.CATALOG_PAGE, true);
			SceneManager.setHeaderButtonVisibility(true, true);
			break;

		default:
			break;
		}
	}

	public boolean isLoggedIn()
	{
		return loggedInUser != null;
	}
	
	public User getLoggedInUser()
	{
		return loggedInUser;
	}

	public void setLoggedInUser(User loggedInUser)
	{
		this.loggedInUser = loggedInUser;
	}


}