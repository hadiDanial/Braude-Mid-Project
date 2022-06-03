package controllers;

import entities.users.*;
import entities.other.*;

public class ComplaintController
{
	private static ComplaintController instance;
	private ComplaintController() {}
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
	public boolean createComplaint(Complaint complaint)
	{
		// TODO - implement ComplaintController.createComplaint
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param complaint
	 * @param message
	 */
	public boolean handleComplaint(Complaint complaint, String message)
	{
		// TODO - implement ComplaintController.handleComplaint
		throw new UnsupportedOperationException();
	}

	public Complaint[] getAllComplaints()
	{
		// TODO - implement ComplaintController.getAllComplaints
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param employee
	 * @param opened
	 */
	public Complaint[] getComplaintsByCSEmployee(User employee, Boolean opened)
	{
		// TODO - implement ComplaintController.getComplaintsByCSEmployee
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param branch
	 */
	public Complaint[] getComplaintsByBranch(Branch branch)
	{
		// TODO - implement ComplaintController.getComplaintsByBranch
		throw new UnsupportedOperationException();
	}

}