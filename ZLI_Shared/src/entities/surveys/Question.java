package entities.surveys;

public class Question
{
	private Survey survey; 
	private String questionText;
	
	
	public Question(Survey survey, String questionText)
	{
		super();
		this.survey = survey;
		this.questionText = questionText;
	}
	public Survey getSurvey()
	{
		return survey;
	}
	public String getQuestionText()
	{
		return questionText;
	}
	
	public void setSurvey(Survey survey)
	{
		this.survey = survey;
	}
	public void setQuestionText(String questionText)
	{
		this.questionText = questionText;
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((questionText == null) ? 0 : questionText.hashCode());
		result = prime * result + ((survey == null) ? 0 : survey.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (!(obj instanceof Question))
		{
			return false;
		}
		Question other = (Question) obj;
		if (questionText == null)
		{
			if (other.questionText != null)
			{
				return false;
			}
		} else if (!questionText.equals(other.questionText))
		{
			return false;
		}
		if (survey == null)
		{
			if (other.survey != null)
			{
				return false;
			}
		} else if (!survey.equals(other.survey))
		{
			return false;
		}
		return true;
	}
	
	

}