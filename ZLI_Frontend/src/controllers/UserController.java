package controllers;

import entities.users.CreditCard;
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
	private ClientController clientController;
	private User loggedInUser = null;

	private UserController()
	{
		clientController = ClientController.getInstance();
	}

	public static synchronized UserController getInstance()
	{
		if (instance == null)
		{
			instance = new UserController();
		}
		return instance;
	}

	public void register(IResponse<Boolean> response, User newUser)
	{
		Request req = new Request(RequestType.REGISTER, newUser);
		clientController.sendRequest(req, response);
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
		Request req = new Request(RequestType.LOGIN, user);
		clientController.sendRequest(req, response);
	}

	/**
	 */
	public void logout()
	{
		if (isLoggedIn())
		{
			Request req = new Request(RequestType.LOGOUT, loggedInUser.getUserId());
			clientController.sendRequest(req, null);
			loggedInUser = null;
		}
	}

	public boolean setUserStatus(User user, AccountStatus accountStatus)
	{
		// TODO - implement UserController.setUserStatus
		throw new UnsupportedOperationException();
	}

	public void getUserCreditCard(IResponse<CreditCard> response)
	{
		Request req = new Request(RequestType.GET_USER_CREDIT_CARD, response);
		clientController.sendRequest(req, response);
	}

	public void openHomePage()
	{
		switch (loggedInUser.getRole())
		{
		case Customer:
			SceneManager.loadNewScene(GUIPages.DISCOUNT_MANAGEMENT, true);
			SceneManager.setHeaderButtonVisibility(true, true);
			break;
		case CustomerServiceEmployee:
			SceneManager.loadNewScene(GUIPages.COMPLAINT_PAGE, true);
			SceneManager.setHeaderButtonVisibility(true, false);

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

	public void checkIfFirstOrder(IResponse<Boolean> response)
	{
		Request req = new Request(RequestType.CHECK_IF_FIRST_ORDER, loggedInUser);
		clientController.sendRequest(req, response);
	}

}