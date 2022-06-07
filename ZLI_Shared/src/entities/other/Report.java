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
	
	
	/** 
	 * @return int
	 */
	public int getReportId()
	{
		return reportId;
	}

	
	/** 
	 * @param reportId
	 */
	public void setReportId(int reportId)
	{
		this.reportId = reportId;
	}


	
	/** 
	 * @return Branch
	 */
	public Branch getBranch()
	{
		return branch;
	}
	
	/** 
	 * @param branch
	 */
	public void setBranch(Branch branch)
	{
		this.branch = branch;
	}
	
	/** 
	 * @return Date
	 */
	public Date getReportDate()
	{
		return reportDate;
	}
	
	/** 
	 * @param reportDate
	 */
	public void setReportDate(Date reportDate)
	{
		this.reportDate = reportDate;
	}
	
	/** 
	 * @return ReportType
	 */
	public ReportType getReportType()
	{
		return reportType;
	}
	
	/** 
	 * @param reportType
	 */
	public void setReportType(ReportType reportType)
	{
		this.reportType = reportType;
	}
	
	/** 
	 * @return int
	 */
	@Override
	public int hashCode()
	{
		return Objects.hash(branch, reportDate, reportType);
	}
	
	/** 
	 * @param obj
	 * @return boolean
	 */
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