package database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface IObjectToPreparedStatementParameters<T> 
{
	public void convertObjectToPSQuery(PreparedStatement statementToPrepare) throws SQLException;
}
