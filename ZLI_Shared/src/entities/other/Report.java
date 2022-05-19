package entities.other;

import java.io.Serializable;
import java.util.*;
import enums.ReportType;

public class Report implements Serializable
{
	private int reportId;
	private Branch branch;
	private Date reportDate;
	private ReportType reportType;
	
	private static final long serialVersionUID = 1262150235358849085L;

	public Report(Branch branch, Date reportDate, ReportType reportType)
	{
		super();
		this.branch = branch;
		this.reportDate = reportDate;
		this.reportType = reportType;
	}
	
	public int getReportId()
	{
		return reportId;
	}

	public void setReportId(int reportId)
	{
		this.reportId = reportId;
	}


	public Branch getBranch()
	{
		return branch;
	}
	public void setBranch(Branch branch)
	{
		this.branch = branch;
	}
	public Date getReportDate()
	{
		return reportDate;
	}
	public void setReportDate(Date reportDate)
	{
		this.reportDate = reportDate;
	}
	public ReportType getReportType()
	{
		return reportType;
	}
	public void setReportType(ReportType reportType)
	{
		this.reportType = reportType;
	}
	@Override
	public int hashCode()
	{
		return Objects.hash(branch, reportDate, reportType);
	}
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Report other = (Report) obj;
		return branch.equals(other.branch) && reportDate.equals(other.reportDate)
				&& reportType == other.reportType;
	}
	
	
	

	
	
}