package controllers;

import java.security.Timestamp;
import java.sql.ResultSet;

import database.DatabaseConnection;
import database.Tables;
import entities.surveys.Survey;

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
	public Survey convertRSToUser(ResultSet rs)
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
				survey.setCustomerServiceSpecialist(rs.(usersColumnNames[1]));
				Timestamp lastLogin = rs.getTimestamp(usersColumnNames[2]);
				survey.setLastLoginDate((lastLogin == null) ? null : lastLogin.toInstant());
				survey.setAnalysisResults(rs.getBytes(usersColumnNames[3]));
				survey.setQuestions(rs.getString(usersColumnNames[5]));
				survey.setPhoneNumber(rs.getString(usersColumnNames[6]));
				survey.setRole(UserRole.valueOf(rs.getString(usersColumnNames[7])));
				survey.setAccountStatus(AccountStatus.valueOf(rs.getString(usersColumnNames[8])));
				survey.setCredit(rs.getFloat(usersColumnNames[9]));
				survey.setLoggedIn(rs.getBoolean(usersColumnNames[10]));
				return survey;
			} else
				return null;
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<survey> convertRSToUsersArray(ResultSet resultSet)
	{
		ArrayList<survey> survey = new ArrayList<survey>();
		try
		{
			while (resultSet.next())
			{
				survey.add(convertRSToUser(resultSet));
			}
			resultSet.close();
			return survey;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}

}
