package entities.surveys;

import java.io.Serializable;

import entities.users.Order;
import entities.users.User;
import exceptions.SurveyException;

public class SurveyAnswers implements Serializable
{
	private int answerId;
	private User customer;
	private Order order;
	private Survey survey;
	private int surveyAnswers[];
	
	static final int MAX_ANSWER_VALUE = 10;
	static final int MIN_ANSWER_VALUE = 1;
	
	private static final long serialVersionUID = -8074074013065418141L;

	public SurveyAnswers(User customer, Order order, Survey survey)
	{
		super();
		this.customer = customer;
		this.order = order;
		this.survey = survey;
		this.surveyAnswers = new int[Survey.NUM_QUESTIONS];
	}

	public SurveyAnswers(User customer, Order order, Survey survey, int[] surveyAnswers) throws SurveyException
	{
		super();
		this.customer = customer;
		this.order = order;
		this.survey = survey;
		checkSurveyArrayLength(surveyAnswers);
		this.surveyAnswers = surveyAnswers;
	}
	
	
	/** 
	 * @param surveyAnswers
	 * @throws SurveyException
	 */
	private void checkSurveyArrayLength(int[] surveyAnswers) throws SurveyException
	{
		if(surveyAnswers.length != Survey.NUM_QUESTIONS)
			throw new SurveyException("Surveys must have exactly " + Survey.NUM_QUESTIONS + " answers!");
	}
	
	
	/** 
	 * @return int
	 */
	public int getAnswerId()
	{
		return answerId;
	}

	
	/** 
	 * @param answerId
	 */
	public void setAnswerId(int answerId)
	{
		this.answerId = answerId;
	}

	
	/** 
	 * @return User
	 */
	public User getCustomer()
	{
		return customer;
	}

	
	/** 
	 * @param customer
	 */
	public void setCustomer(User customer)
	{
		this.customer = customer;
	}

	
	/** 
	 * @return Order
	 */
	public Order getOrder()
	{
		return order;
	}

	
	/** 
	 * @param order
	 */
	public void setOrder(Order order)
	{
		this.order = order;
	}

	
	/** 
	 * @return Survey
	 */
	public Survey getSurvey()
	{
		return survey;
	}

	
	/** 
	 * @param survey
	 */
	public void setSurvey(Survey survey)
	{
		this.survey = survey;
	}

	
	/** 
	 * @return int[]
	 */
	public int[] getSurveyAnswers()
	{
		return surveyAnswers;
	}

	
	/** 
	 * @param surveyAnswers
	 * @throws SurveyException
	 */
	public void setSurveyAnswers(int[] surveyAnswers) throws SurveyException
	{
		checkSurveyArrayLength(surveyAnswers);
		this.surveyAnswers = surveyAnswers;
	}
	
	
	/** 
	 * @param answerIndex
	 * @return int
	 * @throws SurveyException
	 */
	public int getAnswer(int answerIndex) throws SurveyException
	{
		checkValidIndex(answerIndex);
		return surveyAnswers[answerIndex];
	}
		
	
	/** 
	 * @param answerIndex
	 * @param answerValue
	 * @throws SurveyException
	 */
	public void setAnswer(int answerIndex, int answerValue) throws SurveyException
	{
		checkValidIndexAndValue(answerIndex, answerValue);
		surveyAnswers[answerIndex] = answerValue;
	}	

	
	/** 
	 * @param answerIndex
	 * @param answerValue
	 * @throws ArrayIndexOutOfBoundsException
	 * @throws SurveyException
	 */
	private void checkValidIndexAndValue(int answerIndex, int answerValue)
			throws ArrayIndexOutOfBoundsException, SurveyException
	{
		checkValidIndex(answerIndex);
		checkValidValue(answerValue);
	}

	
	/** 
	 * @param answerValue
	 * @throws SurveyException
	 */
	private void checkValidValue(int answerValue) throws SurveyException
	{
		if(answerValue > MAX_ANSWER_VALUE || answerValue < MIN_ANSWER_VALUE)
			throw new SurveyException("Answer value must be between " + MIN_ANSWER_VALUE + " and " + MAX_ANSWER_VALUE + ".");
	}

	
	/** 
	 * @param answerIndex
	 * @throws ArrayIndexOutOfBoundsException
	 */
	private void checkValidIndex(int answerIndex) throws ArrayIndexOutOfBoundsException
	{
		if(answerIndex >= Survey.NUM_QUESTIONS || answerIndex < 0)
			throw new ArrayIndexOutOfBoundsException("Answer index must be between 0 and " + Survey.NUM_QUESTIONS + ".");
	}

	
}