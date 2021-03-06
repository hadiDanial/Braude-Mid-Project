package controllers;

import java.util.ArrayList;
import java.util.HashSet;

import entities.other.Branch;
import requests.Request;
import requests.RequestType;
import utility.IResponse;

public class BranchController
{
	private static BranchController instance;
	private ClientController clientController;
	private ArrayList<Branch> branches = null;
	private Branch workerBranch;
	
	private BranchController()
	{
		clientController = ClientController.getInstance();
	}
	
	
	/** 
	 * @return BranchController
	 */
	public static synchronized BranchController getInstance()
	{
		if(instance == null)
		{
			instance = new BranchController();
		}
		return instance;
	}
	
	
	/** 
	 * @param response
	 */
	public void getAllBranches(IResponse<ArrayList<Branch>> response)
	{
		if(branches == null || branches.isEmpty())
		{			
			Request req = new Request(RequestType.GET_ALL_BRANCHES, null);
			clientController.sendRequest(req, new IResponse<ArrayList<Branch>>()
			{

				@Override
				public void executeAfterResponse(Object message)
				{
					branches = (ArrayList<Branch>) message;
					response.executeAfterResponse(message);
				}
			});
		}
		else
		{
			response.executeAfterResponse(branches);
		}
	}
	
	
	/** 
	 * @param branches
	 * @return HashSet<String>
	 */
	public HashSet<String> getBranchCities(ArrayList<Branch> branches)
	{
		HashSet<String> set = new HashSet<String>();
		for (Branch branch : branches)
		{
			set.add(branch.getLocation().getCity());
		}
		return set;
	}
	
	
	/** 
	 * @param workerId
	 * @param response
	 */
	public void getWorkerBranch(int workerId, IResponse<Branch> response)
	{
		if(workerBranch != null)
		{
			response.executeAfterResponse(workerBranch);
			return;
		}
		
		Request req = new Request(RequestType.GET_WORKER_BRANCH, workerId);
		clientController.sendRequest(req, new IResponse<Branch>()
		{

			@Override
			public void executeAfterResponse(Object message)
			{
				workerBranch = (Branch) message;
				response.executeAfterResponse(message);
			}
		});
	}
}
