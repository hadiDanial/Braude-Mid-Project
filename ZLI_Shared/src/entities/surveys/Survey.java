package entities.surveys;

import java.time.Instant;
import java.util.*;

import entities.users.User;

public class Survey
{
	private int surveyId;
	private User customerServiceSpecialist;
	private ArrayList<User> customers;
	private String analysisResults; // Should be saved as a PDF
	private Instant surveyDate;
	
	static final int NUM_QUESTIONS = 6;
		
	public Survey(User customerServiceSpecialist, ArrayList<User> customers, String analysisResults,
			Instant surveyDate)
	{
		super();
		this.customerServiceSpecialist = customerServiceSpecialist;
		this.customers = customers;
		this.analysisResults = analysisResults;
		this.surveyDate = surveyDate;
	}
	public int getSurveyId()
	{
		return surveyId;
	}
	public void setSurveyId(int surveyId)
	{
		this.surveyId = surveyId;
	}
	public User getCustomerServiceSpecialist()
	{
		return customerServiceSpecialist;
	}
	public void setCustomerServiceSpecialist(User customerServiceSpecialist)
	{
		this.customerServiceSpecialist = customerServiceSpecialist;
	}
	public ArrayList<User> getCustomers()
	{
		return customers;
	}
	public void setCustomers(ArrayList<User> customers)
	{
		this.customers = customers;
	}
	public String getAnalysisResults()
	{
		return analysisResults;
	}
	public void setAnalysisResults(String analysisResults)
	{
		this.analysisResults = analysisResults;
	}
	public Instant getSurveyDate()
	{
		return surveyDate;
	}
	public void setSurveyDate(Instant surveyDate)
	{
		this.surveyDate = surveyDate;
	}
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((surveyDate == null) ? 0 : surveyDate.hashCode());
		result = prime * result + surveyId;
		return result;
	}
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (!(obj instanceof Survey))
		{
			return false;
		}
		Survey other = (Survey) obj;
		if (surveyDate == null)
		{
			if (other.surveyDate != null)
			{
				return false;
			}
		} else if (!surveyDate.equals(other.surveyDate))
		{
			return false;
		}
		if (surveyId != other.surveyId)
		{
			return false;
		}
		return true;
	}
	
	
}