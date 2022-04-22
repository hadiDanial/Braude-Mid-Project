package requests;

import java.io.Serializable;

public class Request implements Serializable
{
	private static final long serialVersionUID = 3755037202060181947L;
	
	private RequestType requestType;
	private Object message;
	public Request(RequestType requestType, Object message)
	{
		super();
		this.requestType = requestType;
		this.message = message;
	}
	public RequestType getRequestType()
	{
		return requestType;
	}
	public void setRequestType(RequestType requestType)
	{
		this.requestType = requestType;
	}
	public Object getMessage()
	{
		return message;
	}
	public void setMessage(Object message)
	{
		this.message = message;
	}
	
	
}
