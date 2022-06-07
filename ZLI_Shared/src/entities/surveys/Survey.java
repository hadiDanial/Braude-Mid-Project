package entities.surveys;

import java.io.Serializable;
import java.time.Instant;
import java.util.*;

import entities.users.User;
import exceptions.SurveyException;
import utility.DateFormatter;

public class Survey implements Serializable
{
	private int surveyId;
	private User customerServiceSpecialist;
	private ArrayList<User> customers;
	private ArrayList<SurveyAnswers> answers;
	private String questions[];
	private byte[] analysisResults; // Should be saved as a PDF
	private Instant startDate, endDate;
	static final int NUM_QUESTIONS = 6;

	private static final long serialVersionUID = 4685144997413378000L;

	public Survey()
	{
		customers = new ArrayList<User>();
		answers = new ArrayList<SurveyAnswers>();
	}

	public Survey(ArrayList<User> customers, String[] questions)
	{
		super();
		this.customers = customers;
		this.questions = questions;
		this.questions = new String[NUM_QUESTIONS];
	}

	public Survey(User customerServiceSpecialist, ArrayList<User> customers, ArrayList<SurveyAnswers> answers,
			String[] questions, byte[] analysisResults, Instant surveyDate) throws SurveyException
	{
		super();
		this.customerServiceSpecialist = customerServiceSpecialist;
		this.customers = customers;
		this.answers = answers;
		checkQuestionsLength(questions);
		this.questions = questions;
		this.analysisResults = analysisResults;
		this.startDate = surveyDate;
	}

	private void checkQuestionsLength(String[] questions) throws SurveyException
	{
		if (questions.length != NUM_QUESTIONS)
			throw new SurveyException("Survey must have " + NUM_QUESTIONS + " questions.");
	}
	public int getNumberOfSurveyed(){
		return customers.size();
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

	public byte[] getAnalysisResults()
	{
		return analysisResults;
	}

	public void setAnalysisResults(byte[] analysisResults)
	{
		this.analysisResults = analysisResults;
	}

	public Instant getStartDate()
	{
		return startDate;
	}

	public void setStartDate(Instant surveyDate)
	{
		this.startDate = surveyDate;
	}

	public Instant getEndDate()
	{
		return endDate;
	}

	public String getFormattedStartDate()
	{
		return DateFormatter.formatInstant(startDate, true);
	}

	public String getFormattedEndDate()
	{
		return DateFormatter.formatInstant(endDate, true);
	}

	public void setEndDate(Instant endDate)
	{
		this.endDate = endDate;
	}
	public int getNumOfCustomers()
	{
		return customers.size();
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
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
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
		if (startDate == null)
		{
			if (other.startDate != null)
			{
				return false;
			}
		} else if (!startDate.equals(other.startDate))
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