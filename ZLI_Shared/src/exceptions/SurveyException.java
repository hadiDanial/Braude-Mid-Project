package exceptions;

public class SurveyException extends Exception
{
	private static final long serialVersionUID = 7910228831042763846L;

	public SurveyException()
	{
		super();
	}

	public SurveyException(String message)
	{
		super(message);
	}
}
