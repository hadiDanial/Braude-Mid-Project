package controllers;

import java.util.ArrayList;
import java.time.Instant;
import entities.surveys.*;
import exceptions.SurveyException;
import requests.Request;
import requests.RequestType;
import utility.IResponse;

public class SurveyController
{
	private static SurveyController instance;
	private ClientController clientController;
	private UserController userController;
	
	private SurveyController() {
		clientController = ClientController.getInstance();
	}
	
	public static synchronized SurveyController getInstance()
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
	public void createSurvey(IResponse<ArrayList<Survey>> response, ArrayList<SurveyAnswers> answers, String[] questions) throws SurveyException
	{
		Survey survey = new Survey();
		survey.setQuestions(questions);
		survey.setAnswers(answers);
		Request req=new Request(RequestType.CREATE_SURVEY,survey);
		ClientController.getInstance().sendRequest(req, response);
	}

	/**
	 * 
	 * @param survey
	 * @param results
	 */
	public boolean addSurveyAnalysis(Survey survey, byte[] resultsPDF)
	{
		// TODO - implement SurveyController.addSurveyAnalysis
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param survey
	 * @param answers
	 */
	public void addSurveyAnswers(Survey survey, ArrayList<SurveyAnswers> answers)
	{
		survey.setAnswers(answers);
	}

	/**
	 * 
	 * @param survey
	 * @param user
	 * @param answers
	 */
	public void getSurveyById(IResponse<ArrayList<Survey>> response, int surveyId)
	{
		Request req=new Request(RequestType.GET_SURVEY_BY_ID, surveyId);
		ClientController.getInstance().sendRequest(req, response);
	}

	/**
	 * 
	 * @param survey
	 * @param user
	 * @param answers
	 */
	public void getSurveyByDate(IResponse<ArrayList<Survey>> response,Instant surveyDate)
	{
		Request req=new Request(RequestType.GET_SURVEY_BY_DATE, surveyDate);
		ClientController.getInstance().sendRequest(req, response);
	}

	/**
	 * 
	 * @param survey
	 * @param user
	 * @param answers
	 */
	public void getSurveyByBranch(int branchId,IResponse<ArrayList<Survey>> response)
	{
		Request req=new Request(RequestType.GET_SURVEY_BY_BRANCH, branchId);
		ClientController.getInstance().sendRequest(req, response);
	}

	public void getAllSurvey(IResponse<ArrayList<Survey>> response)
	{
		Request req=new Request(RequestType.GET_ALL_SURVEY);
		ClientController.getInstance().sendRequest(req, response);
	}
}