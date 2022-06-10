package controllers;

import static org.junit.Assert.*;

import java.time.Instant;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import database.DatabaseConnection;
import entities.other.Branch;
import entities.other.Report;
import entities.users.Order;
import enums.OrderStatus;

public class ReportsControllerTest
{
	private ReportsController reportsController;
	private DatabaseConnection databaseConnection;
	private IOrderController orderController;
	
	@Before
	public void setUp() throws Exception
	{
		databaseConnection = DatabaseConnection.getInstance();
		databaseConnection.connectToDB("localhost", "zlig13", "root", "12345");
		orderController = new StubOrderController();
		reportsController = ReportsController.getInstance();
	}

	@After
	public void tearDown() throws Exception
	{
		databaseConnection.disconnect();
	}

	@Test
	public void generateIncomeReport()
	{
		Order order = new Order();
		order.setOrderDate(Instant.now());
		order.setTotalCost(200);
		Branch b = new Branch();
		b.setBranchId(1);
		order.setBranch(b);
		orderController.createNewOrder(order);
		Report r = reportsController.generateIncomeReport(Date.from(Instant.now().minusSeconds(10000)), Date.from(Instant.now().plusSeconds(10000)), 1);
		System.out.println("1. "+r.getDataString());
	}
}
