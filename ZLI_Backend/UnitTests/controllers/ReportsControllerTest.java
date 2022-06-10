package controllers;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import database.DatabaseConnection;

public class ReportsControllerTest
{
	private ReportsController reportsController;
	private DatabaseConnection databaseConnection;
	@Before
	public void setUp() throws Exception
	{
		databaseConnection = DatabaseConnection.getInstance();
		databaseConnection.connectToDB("localhost", "zlig13", "root", "mYtsb46Ql97"); 
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void test()
	{
		fail("Not yet implemented");
	}

}
