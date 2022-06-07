package controllers;

import java.time.Instant;
import java.sql.ResultSet;

import database.DatabaseConnection;
import database.Tables;
import entities.surveys.Survey;
import entities.users.User;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import database.IObjectToPreparedStatementParameters;
import entities.other.Branch;
import entities.products.BaseProduct;
import entities.products.CatalogItem;
import entities.products.Item;
import entities.products.Product;
import enums.ColorEnum;
import enums.ItemType;
import enums.ProductType;
import requests.EntityRequestWithId;
public class SurveyController {
	private static SurveyController instance;
	private final DatabaseConnection databaseConnection;

	private SurveyController()
	{
		databaseConnection = DatabaseConnection.getInstance();
	}

	
	/** 
	 * @return SurveyController
	 */
	public static synchronized SurveyController getInstance()
	{
		if (instance == null)
		{
			instance = new SurveyController();
		}
		return instance;
	}
	
	/** 
	 * @param rs
	 * @return Survey
	 */
	public Survey convertRSToSurvey(ResultSet rs)
	{
		try
		{
            String [] questions= new String[6];
			String[] surveyColumnNames = Tables.surveysColumnNames;
			if (rs.next())
			{
//				{ "surveyid, specialistId, surveyDate, surveyEndDate analysisResults, q1, q2, q3, q4, q5, q6 };
				Survey survey = new Survey();
				survey.setSurveyId(rs.getInt(surveyColumnNames[0]));
                User customer = new User();
                customer.setUserId(rs.getInt(surveyColumnNames[1]));
                survey.setCustomerServiceSpecialist(customer);
				Timestamp startDate = rs.getTimestamp(surveyColumnNames[2]);
				survey.setStartDate((startDate == null) ? null : startDate.toInstant());
				Timestamp endDate = rs.getTimestamp(surveyColumnNames[3]);
				survey.setEndDate((startDate == null) ? null : startDate.toInstant());
				survey.setAnalysisResults(rs.getBytes(surveyColumnNames[4]));
                questions[0] = (rs.getString(surveyColumnNames[5]));
                questions[1] = (rs.getString(surveyColumnNames[6]));
                questions[2] = (rs.getString(surveyColumnNames[7]));
                questions[3] = (rs.getString(surveyColumnNames[8]));
                questions[4] = (rs.getString(surveyColumnNames[9]));
                questions[5] = (rs.getString(surveyColumnNames[10]));
				survey.setQuestions(questions);
				return survey;
			} else
				return null;
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	
	/** 
	 * @param resultSet
	 * @return ArrayList<Survey>
	 */
	public ArrayList<Survey> convertRSToSurveyArray(ResultSet resultSet)
	{
		ArrayList<Survey> survey = new ArrayList<Survey>();
		try
		{
			while (resultSet.next())
			{
				survey.add(convertRSToSurvey(resultSet));
			}
			resultSet.close();
			return survey;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}

    
	/** 
	 * @return ArrayList<Survey>
	 */
	public ArrayList<Survey> getAllSurvey()
	{
        return convertRSToSurveyArray(databaseConnection.getAll(Tables.SURVEYS_TABLE_NAME));
    }
}
