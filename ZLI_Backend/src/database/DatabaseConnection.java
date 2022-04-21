package database;

import java.sql.Connection;

public abstract class DatabaseConnection {

	protected String driverClassName;
	protected String dbURLPrefix;
	protected Connection dbConnection;

	public void connectToDB() {
		// TODO - implement DatabaseConnection.connectToDB
		throw new UnsupportedOperationException();
	}

	/**
	 * INSERT, UPDATE, or DELETE statement or an SQL statement that returns nothing,
	 * such as an SQL DDL state
	 * private void executeStatement(String sqlQuery)
	 * {
	 * Statement stmt;
	 * try {
	 * stmt = connection.createStatement();
	 * stmt.executeUpdate(sqlQuery);
	 * }
	 * catch(SQLException e)
	 * {
	 * e.printStackTrace();
	 * }
	 * }
	 * 
	 * Parameters:
	 * sql an SQL Data Manipulation Language (DML) statement, such as INSERT, UPDATE
	 * or DELETE; or an SQL statement that returns nothing, such as a DDL statement.
	 * 
	 * Returns:
	 * either (1) the row count for SQL Data Manipulation Language (DML) statements
	 * or (2) 0 for SQL statements that return nothing
	 * Throws:
	 * SQLException - if a database access error occurs, this method is called on a
	 * closed Statement, the given SQL statement produces a ResultSet object, the
	 * method is called on a PreparedStatement or CallableStatement
	 * 
	 * @param sqlQuery
	 */
	public boolean executeStatement(String sqlQuery) {
		// TODO - implement DatabaseConnection.executeStatement
		throw new UnsupportedOperationException();
	}

	public void executePreparedStatement() {
		// TODO - implement DatabaseConnection.executePreparedStatement
		throw new UnsupportedOperationException();
	}

}