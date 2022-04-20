package controllers;

import enums.*;

import java.time.Instant;

import entities.other.*;

public class ReportController
{

	/**
	 * 
	 * @param reportType
	 */
	public Report[] getAllReports(ReportType reportType)
	{
		// TODO - implement ReportController.getAllReports
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param date
	 * @param reportType
	 */
	public Report[] getAllReportsByMonth(Instant date, ReportType reportType)
	{
		// TODO - implement ReportController.getAllReportsByMonth
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param branch
	 * @param reportType
	 */
	public Report[] getAllReportsByBranch(Branch branch, ReportType reportType)
	{
		// TODO - implement ReportController.getAllReportsByBranch
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param branch
	 * @param date
	 * @param reportType
	 */
	public Report getReportByBranchAndDate(Branch branch, Instant date, ReportType reportType)
	{
		// TODO - implement ReportController.getReportByBranchAndDate
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param date
	 * @param branch
	 * @param reportType
	 */
	public boolean generateReport(Instant date, Branch branch, ReportType reportType)
	{
		// TODO - implement ReportController.generateReport
		throw new UnsupportedOperationException();
	}

}