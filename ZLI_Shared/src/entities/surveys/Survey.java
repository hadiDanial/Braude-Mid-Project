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
	private int score;
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
			String[] questions, byte[] analysisResults, Instant surveyDate,int score) throws SurveyException
	{
		super();
		this.customerServiceSpecialist = customerServiceSpecialist;
		this.customers = customers;
		this.answers = answers;
		checkQuestionsLength(questions);
		this.questions = questions;
		this.analysisResults = analysisResults;
		this.startDate = surveyDate;
		this.score=score;
	}
	
	/** 
	 * @return int
	 */
	public int getScore()
	{
		return score;
	}
	
	/** 
	 * @param score
	 */
	public void setScore(int score)
	{
		this.score=score;
	}
	
	/** 
	 * @param questions
	 * @throws SurveyException
	 */
	private void checkQuestionsLength(String[] questions) throws SurveyException
	{
		if (questions.length != NUM_QUESTIONS)
			throw new SurveyException("Survey must have " + NUM_QUESTIONS + " questions.");
	}

	
	/** 
	 * @return int
	 */
	public int getSurveyId()
	{
		return surveyId;
	}

	
	/** 
	 * @param surveyId
	 */
	public void setSurveyId(int surveyId)
	{
		this.surveyId = surveyId;
	}

	
	/** 
	 * @return User
	 */
	public User getCustomerServiceSpecialist()
	{
		return customerServiceSpecialist;
	}

	
	/** 
	 * @param customerServiceSpecialist
	 */
	public void setCustomerServiceSpecialist(User customerServiceSpecialist)
	{
		this.customerServiceSpecialist = customerServiceSpecialist;
	}

	
	/** 
	 * @return ArrayList<User>
	 */
	public ArrayList<User> getCustomers()
	{
		return customers;
	}

	
	/** 
	 * @param customers
	 */
	public void setCustomers(ArrayList<User> customers)
	{
		this.customers = customers;
	}

	
	/** 
	 * @return byte[]
	 */
	public byte[] getAnalysisResults()
	{
		return analysisResults;
	}

	
	/** 
	 * @param analysisResults
	 */
	public void setAnalysisResults(byte[] analysisResults)
	{
		this.analysisResults = analysisResults;
	}

	
	/** 
	 * @return Instant
	 */
	public Instant getStartDate()
	{
		return startDate;
	}

	
	/** 
	 * @param surveyDate
	 */
	public void setStartDate(Instant surveyDate)
	{
		this.startDate = surveyDate;
	}

	
	/** 
	 * @return Instant
	 */
	public Instant getEndDate()
	{
		return endDate;
	}

	
	/** 
	 * @return String
	 */
	public String getFormattedStartDate()
	{
		return DateFormatter.formatInstant(startDate, true);
	}

	
	/** 
	 * @return String
	 */
	public String getFormattedEndDate()
	{
		return DateFormatter.formatInstant(endDate, true);
	}

	
	/** 
	 * @param endDate
	 */
	public void setEndDate(Instant endDate)
	{
		this.endDate = endDate;
	}

	
	/** 
	 * @return int
	 */
	public int getNumOfCustomers()
	{
		return customers.size();
	}

	
	/** 
	 * @return ArrayList<SurveyAnswers>
	 */
	public ArrayList<SurveyAnswers> getAnswers()
	{
		return answers;
	}

	
	/** 
	 * @param answers
	 */
	public void setAnswers(ArrayList<SurveyAnswers> answers)
	{
		this.answers = answers;
	}

	
	/** 
	 * @return String[]
	 */
	public String[] getQuestions()
	{
		return questions;
	}

	
	/** 
	 * @param questions
	 * @throws SurveyException
	 */
	public void setQuestions(String[] questions) throws SurveyException
	{
		checkQuestionsLength(questions);
		this.questions = questions;
	}

	
	/** 
	 * @return int
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + surveyId;
		return result;
	}

	
	/** 
	 * @param obj
	 * @return boolean
	 */
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