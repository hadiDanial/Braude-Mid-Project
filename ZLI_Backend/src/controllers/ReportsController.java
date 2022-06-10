package controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.HashMap;

import database.DatabaseConnection;
import database.IObjectToPreparedStatementParameters;
import database.Tables;
import entities.other.Branch;
import entities.other.Report;
import entities.users.User;
import enums.ReportType;
import enums.UserRole;
import exceptions.UnauthenticatedUserException;

public class ReportsController {
	private static ReportsController instance;
	private final DatabaseConnection databaseConnection;
	private static final String ID_FIELD_NAME = "reportId";

	// " Bouquet : 52.3 , BridalBouquet : 6.1 , FlowerPot : 78 "

	// 0:56,1:67

	public ReportsController() {
		databaseConnection = DatabaseConnection.getInstance();
	}

	public static synchronized ReportsController getInstance() {
		if (instance == null) {
			instance = new ReportsController();
		}
		return instance;
	}

	public boolean createNewReport(Report report) {
		int insertedReportId = databaseConnection.insertAndReturnGeneratedId(Tables.REPORTS_TABLE_NAME,
				Tables.reportsColumnNames, new IObjectToPreparedStatementParameters<Report>() {
					@Override
					public void convertObjectToPSQuery(PreparedStatement statementToPrepare) throws SQLException {
						statementToPrepare.setInt(1, report.getReportId());
						statementToPrepare.setInt(2, report.getYear());
						statementToPrepare.setInt(3, report.getMonth());
						statementToPrepare.setInt(4, report.getBranch().getBranchId());
						statementToPrepare.setString(5, report.getReportType().name().toString());
						statementToPrepare.setString(6, report.getAllDataString());
					}
				});

		if (insertedReportId == -1) {
			return false;
		}

		report.setReportId(insertedReportId);
		return true;
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
}
