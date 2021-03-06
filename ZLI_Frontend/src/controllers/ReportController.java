package controllers;

import enums.*;
import requests.Request;
import requests.RequestType;
import utility.IResponse;

import java.time.Instant;
import java.util.ArrayList;

import entities.other.*;

public class ReportController
{
	private static ReportController instance;
	
	private ClientController clientController;
	
	private ReportController() 
	{
		clientController = ClientController.getInstance();
	}
	
	
	/** 
	 * @return ReportController
	 */
	public static synchronized ReportController getInstance()
	{
		if(instance == null)
		{
			instance = new ReportController();
		}
		return instance;
	}
	/**
	 * 
	 * @param reportType
	 */
	public void getAllReports(IResponse<ArrayList<Report>> response)
	{
		Request req = new Request(RequestType.GET_ALL_REPORTS, null);
		clientController.sendRequest(req, response);
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