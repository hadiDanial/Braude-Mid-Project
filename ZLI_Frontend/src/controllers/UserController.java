package controllers;

import java.util.ArrayList;

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
	private ClientControllerIF clientController;
	private User loggedInUser = null;

	private UserController()
	{
		clientController = ClientController.getInstance();
	}

	private UserController(ClientControllerIF clientControllerIF)
	{
		clientController = clientControllerIF;
	}
		/** 
	 * @return UserController
	 */
	public static synchronized UserController getInstance(ClientControllerIF clientControllerIF)
	{

		if (instance == null)
		{
			instance = new UserController(clientControllerIF);
		}
		return instance;
	}

	
	/** 
	 * @return UserController
	 */
	public static synchronized UserController getInstance()
	{
		if (instance == null)
		{
			instance = new UserController();
		}
		return instance;
	}

	
	/** 
	 * @param response
	 * @param newUser
	 */
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
			SceneManager.loadNewScene(GUIPages.LOGIN, false);
			OrderController.getInstance().reset();
			ProductController.getInstance().reset();
		}
	}

	
	/** 
	 * @param user
	 * @param accountStatus
	 * @return boolean
	 */
	public boolean setUserStatus(User user, AccountStatus accountStatus)
	{
		// TODO - implement UserController.setUserStatus
		throw new UnsupportedOperationException();
	}

	
	/** 
	 * @param response
	 */
	public void getUserCreditCard(IResponse<CreditCard> response)
	{
		Request req = new Request(RequestType.GET_USER_CREDIT_CARD, loggedInUser);
		clientController.sendRequest(req, response);
	}

	
	/** 
	 * @return boolean
	 */
	public boolean isLoggedIn()
	{
		return loggedInUser != null;
	}

	
	/** 
	 * @return User
	 */
	public User getLoggedInUser()
	{
		return loggedInUser;
	}

	
	/** 
	 * @param loggedInUser
	 */
	public void setLoggedInUser(User loggedInUser)
	{
		this.loggedInUser = loggedInUser;
	}

	
	/** 
	 * @param response
	 */
	public void checkIfFirstOrder(IResponse<Boolean> response)
	{
		if(loggedInUser.getRole() == UserRole.Customer)
		{			
			Request req = new Request(RequestType.CHECK_IF_FIRST_ORDER, loggedInUser.getUserId());
			clientController.sendRequest(req, response);
		}
	}
    
	/** 
	 * @param response
	 */
	public void getAllUsers(IResponse<ArrayList<User>> response) {
		Request req = new Request(RequestType.GET_ALL_USERS, loggedInUser);
		clientController.sendRequest(req, response);
    }

	
	/** 
	 * @param response
	 */
	public void getAllUsersByRole(UserRole customer, IResponse<ArrayList<User>> response)
	{
		Request req = new Request(RequestType.GET_ALL_USERS_BY_ROLE, customer);
		clientController.sendRequest(req, response);
	}

	
	/** 
	 * @param user
	 * @param response
	 */
	public void createNewUser(User user, IResponse<Boolean> response)
	{
		Request req = new Request(RequestType.CREATE_NEW_USER, user);
		clientController.sendRequest(req, response);		
	}


	public void updateCredit()
	{
		if(isLoggedIn())
		{			
		Request req = new Request(RequestType.GET_UPDATED_CREDIT, loggedInUser.getUserId());
		clientController.sendRequest(req, new IResponse<Float>()
		{

			@Override
			public void executeAfterResponse(Object message)
			{
				loggedInUser.setCredit((Float) message);
			}
		});		
		}
	}

}