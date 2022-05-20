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
	public void addSurveyAnswers(Survey survey, SurveyAnswers answers)
	{
		survey.setAnswers(answers);
	}

	/**
	 * 
	 * @param survey
	 * @param user
	 * @param answers
	 */
	public Survey getSurveyById(int surveyId)
	{
		return null;
	}

	/**
	 * 
	 * @param survey
	 * @param user
	 * @param answers
	 */
	public boolean getSurveyByDate(Instant surveyDate)
	{
		// TODO - implement SurveyController.addSurveyAnswers
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param survey
	 * @param user
	 * @param answers
	 */
	public boolean getSurveyByBranch(Survey survey, User user, SurveyAnswers answers)
	{
		// TODO - implement SurveyController.addSurveyAnswers
		throw new UnsupportedOperationException();
	}
}