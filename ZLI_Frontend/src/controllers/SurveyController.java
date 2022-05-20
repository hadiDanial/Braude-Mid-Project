package controllers;

import java.util.ArrayList;

import entities.surveys.*;
import entities.users.User;
import requests.Request;
import requests.RequestType;
import utility.IResponse;

public class SurveyController
{

	private static SurveyController instance;
	private ClientController clientController;
		
	private SurveyController() {
		clientController = ClientController.getInstance();
	}

	public static SurveyController getInstance()
	{
		if(instance == null)
		{
			instance = new SurveyController();
		}
		return instance;
	}
	public void createSurvey(IResponse<ArrayList<Survey>> response)
	{
		Request request = new Request(RequestType.Survey, null);
		clientController.sendRequest(request, response);
	}
	
	public void updateOrder(IResponse<Boolean> response, Survey updatedOrder)
	{
		Request request = new Request(RequestType.UpdateOrder, updatedOrder);
		clientController.sendRequest(request, response);
	}
	/**
	 * 
	 * @param survey
	 */
	public boolean createSurvey(Survey survey)
	{
		// TODO - implement SurveyController.createSurvey
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param survey
	 * @param results
	 */
	public boolean addSurveyAnalysis(Survey survey, String results)
	{
		// TODO - implement SurveyController.addSurveyAnalysis
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param survey
	 * @param user
	 * @param answers
	 */
	public boolean addSurveyAnswers(Survey survey, User user, SurveyAnswers answers)
	{
		// TODO - implement SurveyController.addSurveyAnswers
		throw new UnsupportedOperationException();
	}
}