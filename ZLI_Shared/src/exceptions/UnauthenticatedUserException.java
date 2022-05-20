package exceptions;

public class UnauthenticatedUserException extends Exception
{
	private static final long serialVersionUID = 5066086540142250751L;

	public UnauthenticatedUserException(String message)
	{
		super(message);
	}
}
