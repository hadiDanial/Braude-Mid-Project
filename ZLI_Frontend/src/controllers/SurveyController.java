package controllers;

import java.util.ArrayList;
import java.io.Serializable;
import java.time.Instant;
import java.util.*;
import entities.surveys.*;
import entities.users.User;
import exceptions.SurveyException;
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
	/**
	 * 
	 * @param survey
	 * @throws SurveyException
	 */
	public void createSurvey(IResponse<ArrayList<Survey>> response, SurveyAnswers answers, String[] questions) throws SurveyException
	{
		Survey survey = new Survey();
		survey.setQuestions(questions);
		survey.setAnswers(answers);
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