package utility;

public interface IResponse<T>
{
	public void executeAfterResponse(T response);
}
