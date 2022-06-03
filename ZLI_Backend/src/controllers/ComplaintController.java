package controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;

import database.DatabaseConnection;
import database.IObjectToPreparedStatementParameters;
import database.Tables;
import entities.users.Complaint;
import entities.users.User;

public class ComplaintController {
    private final DatabaseConnection databaseConnection;
	private UserController userController;
    private static ComplaintController instance;
	private static final String ID_FIELD_NAME = "complaintId";

    private ComplaintController()
	{
		databaseConnection = DatabaseConnection.getInstance();
	}
    public static ComplaintController getInstance()
	{
		if (instance == null)
		{
			instance = new ComplaintController();
		}
		return instance;
	}
    public boolean createComplaint(Complaint complaint)
    {
        int res = databaseConnection.insertToDatabase(Tables.COMPLAINTS_TABLE_NAME, Tables.complaintsColumnNames,
				new IObjectToPreparedStatementParameters<Complaint>()
				{
					@Override
					public void convertObjectToPSQuery(PreparedStatement statementToPrepare) throws SQLException
					{
						statementToPrepare.setInt(1, complaint.getComplaintId());
						statementToPrepare.setInt(2, complaint.getCustomer().getUserId());
						statementToPrepare.setInt(3, complaint.getCustomerServiceEmployee().getUserId());
						statementToPrepare.setString(4, complaint.getComplaintDetails());
						statementToPrepare.setString(5, complaint.getComplaintResult());
						statementToPrepare.setTimestamp(6, Timestamp.from(complaint.getSubmissionTime()));
                        statementToPrepare.setBoolean(7, complaint.isWasHandled());
					}
				});
                if(res==1)
                {
                    EmailManager.sendEmail(
					"Zerli - Filed Complaint", "Your complaint has been registered and will be responded by a our Customer Service employee soon.<br>"
							+ "Complaint #" + complaint.getComplaintId() + "<br>" + "Thank you for choosing Zerli!",
					complaint.getCustomer());
			notifyEmployee(complaint);
                }
		return res == 1;
    }
    public void notifyEmployee(Complaint complaint)
    {
        int customerServiceEmployeeId = complaint.getCustomerServiceEmployee().getUserId();
		ArrayList<String> tables = (ArrayList<String>) Arrays.asList(new String[]
		{ Tables.COMPLAINTS_TABLE_NAME, Tables.USERS_TABLE_NAME });
		String selects = "complaints.customerServiceEmployeeId, users.emailAddress, users.firstName";
		String conditions = "complaints.customerServiceEmployeeId=" + customerServiceEmployeeId + " AND complaints.customerServiceEmployeeId=users.userId";
		ResultSet rs = databaseConnection.getJoinResultsWithSelectColumns(tables, selects, conditions);
		try
		{
			String content = "Hello, " + rs.getString("firstName") + "!<br>"
					+ "A new complaint has been filed, please review it within 24 hours !<br>" + "Complaint #"
					+ complaint.getComplaintId();
			EmailManager.sendEmail("New Complaint", content, rs.getString("emailAddress"));
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
    }
    public void handleComplaint(Complaint complaint, Boolean type, User user) 
    {
		int customerId = complaint.getCustomer().getUserId();
		ArrayList<String> tables = (ArrayList<String>) Arrays.asList(new String[]
		{ Tables.COMPLAINTS_TABLE_NAME, Tables.USERS_TABLE_NAME });
		String selects = "complaints.customerId, users.emailAddress, users.firstName";
		String conditions = "complaints.customerId=" + customerId + " AND complaints.customerId=users.userId";
		ResultSet rs = databaseConnection.getJoinResultsWithSelectColumns(tables, selects, conditions);
        try
        {
		 if(type==true){
         String content = "Hello, " + rs.getString("firstName") + "!<br>"
         + "your complaint #"+ complaint.getComplaintId() +" has been handled, here's our response to it: <br>"
         + complaint.getComplaintResult()+ "<br>" + "Have a nice day! <br>";
         EmailManager.sendEmail("Complaint Response", content, rs.getString("emailAddress"));
		 }
		 if(type==false)
		{
		 notifyEmployee(complaint);
		 String content = "Hello, " + rs.getString("firstName") + "!<br>"
         + "sorry for the inconvenience of not responding to your complaint #"+ complaint.getComplaintId() +", we are willing to compensate you for the trouble.<br>"
         + user.getCredit()+ "<br>" + "Have a nice day! <br>";
         EmailManager.sendEmail("Complaint Response", content, rs.getString("emailAddress"));
		 userController.updateUserCredit(complaint.getCustomer(),user.getCredit());
		}
		}catch(Exception e)
		{
		e.printStackTrace();
		}
	}
}
