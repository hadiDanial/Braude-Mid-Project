package controllers;

import entities.surveys.*;
import entities.users.User;

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