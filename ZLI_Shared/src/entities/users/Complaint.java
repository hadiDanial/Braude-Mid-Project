package entities.users;

import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import utility.DateFormatter;

public class Complaint implements Serializable
{
	private int complaintId;
	private User customer;
	private User customerServiceEmployee;
	private String complaintDetails;
	private Instant submissionTime;
	private Instant openedDate;
	private Duration duration;
	private String complaintResult;
	private boolean wasHandled;

	private static final long serialVersionUID = 8137391902399453083L;

	public Complaint()
	{
		
	}
	public Complaint(User customer, User customerServiceEmployee, String complaintDetails,
			Instant submissionTime, String complaintResult, Instant openedDate)
	{
		super();
		this.customer = customer;
		this.customerServiceEmployee = customerServiceEmployee;
		this.complaintDetails = complaintDetails;
		this.submissionTime = submissionTime;
		this.complaintResult = complaintResult;
		this.openedDate = openedDate;
		this.wasHandled = false;
	}
	
	/** 
	 * @param duration
	 */
	public void setDuration(Duration duration)
	{
		this.duration=duration;
	}
	
	/** 
	 * @return long
	 */
	public long getDuration()
	{
		if(openedDate == null) return 0;
		duration=Duration.between(submissionTime,openedDate);
		return duration.toHours();
	}
	
	
	/** 
	 * @return String
	 */
	public String getFormattedopenedDate()
	{
		if(openedDate == null) return "";
		return DateFormatter.formatInstant(openedDate, true);
	}
	
	/** 
	 * @return int
	 */
	public int getComplaintId()
	{
		return complaintId;
	}
	
	/** 
	 * @param complaintId
	 */
	public void setComplaintId(int complaintId)
	{
		this.complaintId = complaintId;
	}
	
	/** 
	 * @return User
	 */
	public User getCustomer()
	{
		return customer;
	}
	
	/** 
	 * @param customer
	 */
	public void setCustomer(User customer)
	{
		this.customer = customer;
	}
	
	/** 
	 * @return User
	 */
	public User getCustomerServiceEmployee()
	{
		return customerServiceEmployee;
	}
	
	/** 
	 * @param customerServiceEmployee
	 */
	public void setCustomerServiceEmployee(User customerServiceEmployee)
	{
		this.customerServiceEmployee = customerServiceEmployee;
	}
	
	/** 
	 * @return String
	 */
	public String getComplaintDetails()
	{
		return complaintDetails;
	}
	
	/** 
	 * @param complaintDetails
	 */
	public void setComplaintDetails(String complaintDetails)
	{
		this.complaintDetails = complaintDetails;
	}
	
	/** 
	 * @return Instant
	 */
	public Instant getSubmissionTime()
	{
		return submissionTime;
	}
	
	/** 
	 * @param submissionTime
	 */
	public void setSubmissionTime(Instant submissionTime)
	{
		this.submissionTime = submissionTime;
	}
	
	/** 
	 * @return String
	 */
	public String getComplaintResult()
	{
		return complaintResult;
	}
	
	/** 
	 * @param complaintResult
	 */
	public void setComplaintResult(String complaintResult)
	{
		this.complaintResult = complaintResult;
	}
	
	/** 
	 * @return boolean
	 */
	public boolean isWasHandled()
	{
		return wasHandled;
	}
	
	/** 
	 * @param wasHandled
	 */
	public void setWasHandled(boolean wasHandled)
	{
		this.wasHandled = wasHandled;
	}
	
	
	
	/** 
	 * @return int
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + complaintId;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((customerServiceEmployee == null) ? 0 : customerServiceEmployee.hashCode());
		result = prime * result + ((submissionTime == null) ? 0 : submissionTime.hashCode());
		return result;
	}
	
	/** 
	 * @param obj
	 * @return boolean
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (!(obj instanceof Complaint))
		{
			return false;
		}
		Complaint other = (Complaint) obj;
		if (complaintId != other.complaintId)
		{
			return false;
		}
		if (customer == null)
		{
			if (other.customer != null)
			{
				return false;
			}
		} else if (!customer.equals(other.customer))
		{
			return false;
		}
		if (customerServiceEmployee == null)
		{
			if (other.customerServiceEmployee != null)
			{
				return false;
			}
		} else if (!customerServiceEmployee.equals(other.customerServiceEmployee))
		{
			return false;
		}
		if (submissionTime == null)
		{
			if (other.submissionTime != null)
			{
				return false;
			}
		} else if (!submissionTime.equals(other.submissionTime))
		{
			return false;
		}
		return true;
	}

	
}