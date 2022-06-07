package requests;

import java.io.Serializable;

import entities.users.User;

public class Request implements Serializable
{
	private static final long serialVersionUID = 3755037202060181947L;
	
	private RequestType requestType;
	private Object message;
	private User user;
	public Request(RequestType requestType)
	{
		super();
		this.requestType = requestType;
	}
	public Request(RequestType requestType, Object message)
	{
		super();
		this.requestType = requestType;
		this.message = message;
	}
	public Request(RequestType requestType, Object message, User user)
	{
		super();
		this.requestType = requestType;
		this.message = message;
		this.user = user;
	}
	
	/** 
	 * @return RequestType
	 */
	public RequestType getRequestType()
	{
		return requestType;
	}
	
	/** 
	 * @param requestType
	 */
	public void setRequestType(RequestType requestType)
	{
		this.requestType = requestType;
	}
	
	/** 
	 * @return Object
	 */
	public Object getMessage()
	{
		return message;
	}
	
	/** 
	 * @param message
	 */
	public void setMessage(Object message)
	{
		this.message = message;
	}
	
	/** 
	 * @return User
	 */
	public User getUser()
	{
		return user;
	}
	
	/** 
	 * @param user
	 */
	public void setUser(User user)
	{
		this.user = user;
	}
	
	
}
