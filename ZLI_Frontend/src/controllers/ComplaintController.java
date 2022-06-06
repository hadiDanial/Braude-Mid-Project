package controllers;

import entities.users.*;
import requests.Request;
import requests.RequestType;
import utility.IResponse;

import java.util.ArrayList;

import entities.other.*;

public class ComplaintController
{
	private static ComplaintController instance;
	
	private ClientController clientController;
	private UserController userController;

	private ComplaintController()
	{
		clientController = ClientController.getInstance();
		userController	= UserController.getInstance();
	}
	
	public static synchronized ComplaintController getInstance()
	{
		if(instance == null)
		{
			instance = new ComplaintController();
		}
		return instance;
	}
	/**
	 * 
	 * @param complaint
	 */
	public void createComplaint(Complaint complaint, IResponse<Boolean> response)
	{
		Request req=new Request(RequestType.CREATE_COMPLAINTS, complaint,userController.getLoggedInUser());
		clientController.sendRequest(req, response);
	}

	/**
	 * 
	 * @param complaint
	 * @param response
	 */
	public void handleComplaint(Complaint complaint, String response)
	{
		complaint.setComplaintResult(response);
	}

	public void getAllComplaints(IResponse <ArrayList<Complaint>> response)
	{
		Request req=new Request(RequestType.GET_ALL_COMPLAINTS,userController.getLoggedInUser());
		clientController.sendRequest(req, response);
	}

}