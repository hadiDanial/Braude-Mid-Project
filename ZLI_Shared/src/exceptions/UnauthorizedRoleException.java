package exceptions;

public class UnauthorizedRoleException extends Exception
{
	private static final long serialVersionUID = 3184203245891593457L;

	public UnauthorizedRoleException(String message)
	{
		super(message);
	}
}
