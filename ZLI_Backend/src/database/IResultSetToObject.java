package database;

import java.sql.ResultSet;

public interface IResultSetToObject<T>
{
	public T convertToObject(ResultSet rs);
}
