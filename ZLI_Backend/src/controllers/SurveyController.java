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

	public static synchronized SurveyController getInstance()
	{
		if (instance == null)
		{
			instance = new SurveyController();
		}
		return instance;
	}
	public Survey convertRSToSurvey(ResultSet rs)
	{
		try
		{
            String [] questions= new String[6];
			String[] usersColumnNames = Tables.usersColumnNames;
			if (rs.next())
			{
//				{ "surveyid, specialistId, surveyDate, analysisResults, q1, q2, q3, q4, q5, q6 };
				Survey survey = new Survey();
				survey.setSurveyId(rs.getInt(usersColumnNames[0]));
                User customer = new User();
                customer.setUserId(rs.getInt(usersColumnNames[1]));
                survey.setCustomerServiceSpecialist(customer);
				Timestamp startDate = rs.getTimestamp(usersColumnNames[2]);
				survey.setStartDate((startDate == null) ? null : startDate.toInstant());
				survey.setAnalysisResults(rs.getBytes(usersColumnNames[3]));
                questions[0] = (rs.getString(usersColumnNames[4]));
                questions[1] = (rs.getString(usersColumnNames[5]));
                questions[2] = (rs.getString(usersColumnNames[6]));
                questions[3] = (rs.getString(usersColumnNames[7]));
                questions[4] = (rs.getString(usersColumnNames[8]));
                questions[5] = (rs.getString(usersColumnNames[9]));
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

    public ArrayList<Survey> getAllSurvey()
	{
        return convertRSToSurveyArray(databaseConnection.getAll(Tables.SURVEYS_TABLE_NAME));
    }
}
