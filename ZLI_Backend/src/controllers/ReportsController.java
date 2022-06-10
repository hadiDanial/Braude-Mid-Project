package controllers;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Year;

import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

import database.DatabaseConnection;
import database.IObjectToPreparedStatementParameters;
import database.Tables;
import entities.other.Branch;
import entities.other.Report;
import entities.users.User;
import enums.ReportType;

public class ReportsController {
	private static ReportsController instance;
	private final DatabaseConnection databaseConnection;
	private static final String ID_FIELD_NAME = "reportId";

	// " Bouquet : 52.3 , BridalBouquet : 6.1 , FlowerPot : 78 "
	// 0 : 56 , 1 : 67

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
		boolean res = false;
		int insertedReportId=databaseConnection.insertAndReturnGeneratedId(Tables.REPORTS_TABLE_NAME,Tables.reportsColumnNames, new IObjectToPreparedStatementParameters<Report>() {
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

		if(insertedReportId ==-1){
			return false;
		}

		report.setReportId(insertedReportId);


		
		return res;
	}

	public Report getReport(User user, Branch branch, int month, int year, ReportType type) {

		return null;
	}
}
