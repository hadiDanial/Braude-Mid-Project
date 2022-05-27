package entities.surveys;

import java.io.Serializable;
import java.time.Instant;
import java.util.*;

import entities.users.User;
import exceptions.SurveyException;

public class Survey implements Serializable
{
	private int surveyId;
	private User customerServiceSpecialist;
	private ArrayList<User> customers;
	private ArrayList<SurveyAnswers> answers;
	private String questions[];
	private String analysisResults; // Should be saved as a PDF
	private Instant surveyDate;
	
	static final int NUM_QUESTIONS = 6;
		
	private static final long serialVersionUID = 4685144997413378000L;

	public Survey()
	{
		customers = new ArrayList<User>();
		answers = new ArrayList<SurveyAnswers>();
		this.surveyDate = Instant.now();
	}
	
	public Survey(ArrayList<User> customers, String[] questions)
	{
		super();
		this.customers = customers;
		this.questions = questions;
		this.surveyDate = Instant.now();
		this.questions = new String[NUM_QUESTIONS];
	}
	
	public Survey(User customerServiceSpecialist, ArrayList<User> customers, ArrayList<SurveyAnswers> answers,
			String[] questions, String analysisResults, Instant surveyDate) throws SurveyException
	{
		super();
		this.customerServiceSpecialist = customerServiceSpecialist;
		this.customers = customers;
		this.answers = answers;
		checkQuestionsLength(questions);
		this.questions = questions;
		this.analysisResults = analysisResults;
		this.surveyDate = surveyDate;
	}

	private void checkQuestionsLength(String[] questions) throws SurveyException
	{
		if(questions.length != NUM_QUESTIONS)
			throw new SurveyException("Survey must have " + NUM_QUESTIONS + " questions.");
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
	
	public ArrayList<SurveyAnswers> getAnswers()
	{
		return answers;
	}

	public void setAnswers(ArrayList<SurveyAnswers> answers)
	{
		this.answers = answers;
	}

	public String[] getQuestions()
	{
		return questions;
	}

	public void setQuestions(String[] questions) throws SurveyException
	{
		checkQuestionsLength(questions);
		this.questions = questions;
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