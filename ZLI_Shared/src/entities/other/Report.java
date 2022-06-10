package entities.other;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;
import enums.ReportType;

public class Report implements Serializable {
	private int reportId;
	private Branch branch;
	private Date reportDate;
	private ReportType reportType;
	private int month;
	private int year;
	private String data;
	private HashMap<String, Number> map;
	// 0:56,1:89

	private static final long serialVersionUID = 1262150235358849085L;

	public Report(int reportId, Branch branch, Date reportDate, ReportType reportType, int month, int year,
			String data) {
		super();
		this.reportId = reportId;
		this.branch = branch;
		this.reportDate = reportDate;
		this.reportType = reportType;
		this.month = month;
		this.year = year;
		this.data = data;
	}

	public Report() {
		data = "";
	}

	public void setData(HashMap<String, Number> map) {
		this.map = map;
		buildReportDataString();
	}

	private String buildReportDataString() {
		if (map != null) {
			String dataString = "";

			for (int i = 0; i < 31; i++) {
				if (map.containsKey(i + "")) {
					Number value = map.get(i + "");
					dataString += i + ":" + value.toString();
				}
				if (map.containsKey((i + 1) + "") && map.containsKey(i + "")) {
					dataString += ",";
				}
			}

			this.data = dataString;
			return dataString;
		}
		return null;
	}

	private HashMap<String, Number> buildReportMap() {
		HashMap<String, Number> map = new HashMap<>();
		if (data != null) {
			String[] dataSplitByComma = this.data.split(",");
			for (String string : dataSplitByComma) {
				try {
					map.put(string.split(":")[0], NumberFormat.getInstance().parse(string.split(":")[1]));
				} catch (ParseException e) {
					e.printStackTrace();
					return null;
				}
			}
			this.map = map;
		}

		return null;
	}

	public Number getDataByKey(String key) {
		if (map != null) {
			return map.get(key);
		} else if (data != null) {
			buildReportMap();
			return map.get(key);
		}
		return null;
	}

	public String getDataString() {
		if (data != null)
			return data;
		return buildReportDataString();
	}

	public HashMap<String, Number> getDataMap() {
		if (this.map != null)
			return this.map;

		return buildReportMap();
	}

	public void setData(String data) {
		this.data = data;
		buildReportMap();
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return int
	 */
	public int getReportId() {
		return reportId;
	}

	/**
	 * @param reportId
	 */
	public void setReportId(int reportId) {
		this.reportId = reportId;
	}

	/**
	 * @return Branch
	 */
	public Branch getBranch() {
		return branch;
	}

	/**
	 * @param branch
	 */
	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	/**
	 * @return Date
	 */
	public Date getReportDate() {
		return reportDate;
	}

	/**
	 * @param reportDate
	 */
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
		this.year = reportDate.getYear() + 1900;
		this.month = reportDate.getMonth();
	}

	/**
	 * @return ReportType
	 */
	public ReportType getReportType() {
		return reportType;
	}

	/**
	 * @param reportType
	 */
	public void setReportType(ReportType reportType) {
		this.reportType = reportType;
	}

	/**
	 * @return int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(branch, reportDate, reportType);
	}

	/**
	 * @param obj
	 * @return boolean
	 */
	@Override
	public boolean equals(Object obj) {
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