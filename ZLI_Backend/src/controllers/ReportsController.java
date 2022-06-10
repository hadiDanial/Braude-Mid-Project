package controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.Year;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;

import database.DatabaseConnection;
import database.IObjectToPreparedStatementParameters;
import database.Tables;
import entities.other.Branch;
import entities.other.Report;
import entities.users.Order;
import entities.users.User;
import enums.OrderStatus;
import enums.ReportType;
import enums.UserRole;
import exceptions.UnauthenticatedUserException;

public class ReportsController {
	private static ReportsController instance;
	private final DatabaseConnection databaseConnection;
	private IOrderController orderController;
	private static final String ID_FIELD_NAME = "reportId";

	// " Bouquet : 52.3 , BridalBouquet : 6.1 , FlowerPot : 78 "

	// 0:56,1:67

	private ReportsController() {
		databaseConnection = DatabaseConnection.getInstance();
		orderController = OrderController.getInstance();
	}
	private ReportsController(IOrderController orderController) {
		databaseConnection = DatabaseConnection.getInstance();
		orderController = OrderController.getInstance();
	}

	public static synchronized ReportsController getInstance() {
		if (instance == null) {
			instance = new ReportsController();
		}
		return instance;
	}
	public static synchronized ReportsController getInstance(IOrderController orderController) {
		if (instance == null) {
			instance = new ReportsController(orderController);
		}
		return instance;
	}

	public boolean createNewReport(Report report) {
		boolean res = false;
		int insertedReportId = databaseConnection.insertAndReturnGeneratedId(Tables.REPORTS_TABLE_NAME,
				Tables.reportsColumnNames, reportToPS(report));

		if (insertedReportId == -1) {
			return false;
		}

		report.setReportId(insertedReportId);
		return true;
	}

	private IObjectToPreparedStatementParameters<Report> reportToPS(Report report) {
		return new IObjectToPreparedStatementParameters<Report>() {
			@Override
			public void convertObjectToPSQuery(PreparedStatement statementToPrepare) throws SQLException {
				statementToPrepare.setInt(1, report.getReportId());
				statementToPrepare.setInt(2, report.getYear());
				statementToPrepare.setInt(3, report.getMonth());
				statementToPrepare.setInt(4, report.getBranch().getBranchId());
				statementToPrepare.setString(5, report.getReportType().name().toString());
				statementToPrepare.setString(6, report.getDataString());
				statementToPrepare.setTimestamp(7, Timestamp.from(report.getReportDate().toInstant()));
			}
		};
	}

	public Report getSpecificReport(User user, Branch branch, int month, int year, ReportType type)
			throws UnauthenticatedUserException {
		if (!(user.getRole() == UserRole.BranchManager || user.getRole() == UserRole.CEO)) {
			throw new UnauthenticatedUserException("Only CEOs or BranchManagers can view Reports");
		}

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("branchId", branch.getBranchId() + "");
		map.put("month", month + "");
		map.put("year", year + "");
		map.put("reportType", type.name());
		ResultSet reportsRS = databaseConnection.getByConditions(Tables.REPORTS_TABLE_NAME, map);
		Report report = convertRSToReport(reportsRS);

		return report;
	}

	private Report convertRSToReport(ResultSet resultSet) {
		String[] reportsColumnNames = Tables.reportsColumnNames;
		Report report;
		Branch branch = new Branch();

		try {
			branch.setBranchId(resultSet.getInt(reportsColumnNames[3]));
			report = new Report(
					resultSet.getInt(ID_FIELD_NAME),
					branch,
					resultSet.getDate(reportsColumnNames[6]),
					ReportType.valueOf(resultSet.getString(reportsColumnNames[4])),
					resultSet.getInt(reportsColumnNames[2]),
					resultSet.getInt(reportsColumnNames[1]),
					resultSet.getString(reportsColumnNames[5]));
		} catch (Exception e) {
			report = null;
		}

		return report;
	}
	
	public Report generateIncomeReport(Date startDate, Date endDate, int branchId)
	{
		ArrayList<Order> orders = orderController.getOrdersByDatesAndBranch(startDate, endDate, branchId, OrderStatus.Delivered);
		Report report = new Report();
		report.setReportDate(Date.from(Instant.now()));
		HashMap<String, Number> map = new HashMap<String, Number>();
		orders.sort(new Comparator<Order>() {
			@Override
			public int compare(Order o1, Order o2) {
				return o1.getOrderDate().compareTo(o2.getOrderDate());
			}
		});
		for (Order order : orders) {
			map.put(String.valueOf(Date.from(order.getOrderDate()).getDay()), order.getTotalCost());
		}
		report.setReportType(ReportType.IncomeReport);
		report.setBranch(BranchController.getInstance().getBranchById(branchId));
		report.setData(map);
		databaseConnection.insertToDatabase(Tables.REPORTS_TABLE_NAME, Tables.reportsColumnNames, reportToPS(report));
		return report;
	}
}
