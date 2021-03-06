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
    
	/** function the gets instance of the controller
	 * @return instance ComplaintController
	 */
	public static synchronized ComplaintController getInstance()
	{
		if (instance == null)
		{
			instance = new ComplaintController();
		}
		return instance;
	}
    
	/** function that creates a complaint and inserts it in the database using input complaint
	 * @param complaint 
	 * @return boolean checks if we can create a complaint in the database and send it via email 
	 */
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
			notifyEmployee(complaint,"New Complaint Received");
                }
		return res == 1;
    }
    
	/** function that made to be used in other functions to notify the customer service employee and send the notifcation to the database
	 * @param complaint 
	 * @param title of the sent email 
	 */
	public void notifyEmployee(Complaint complaint,String title)
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
			EmailManager.sendEmail(title, content, rs.getString("emailAddress"));
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
    }
    
	/** function that handles complaint and also in case to warn customer service employee via email
	 * and compensate customer in case if the complaint wasn't handled via refunding credit
	 * @param complaint
	 * @param handled if the complaint is handled or not
	 * @param user the type of user to send the email to 
	 */
	public void handleComplaint(Complaint complaint, Boolean handled, User user) 
    {
		int customerId = complaint.getCustomer().getUserId();
		ArrayList<String> tables = (ArrayList<String>) Arrays.asList(new String[]
		{ Tables.COMPLAINTS_TABLE_NAME, Tables.USERS_TABLE_NAME });
		String selects = "complaints.customerId, users.emailAddress, users.firstName";
		String conditions = "complaints.customerId=" + customerId + " AND complaints.customerId=users.userId";
		ResultSet rs = databaseConnection.getJoinResultsWithSelectColumns(tables, selects, conditions);
        try
        {
		 if(handled==true){
         String content = "Hello, " + rs.getString("firstName") + "!<br>"
         + "your complaint #"+ complaint.getComplaintId() +" has been handled, here's our response to it: <br>"
         + complaint.getComplaintResult()+ "<br>" + "Have a nice day! <br>";
         EmailManager.sendEmail("Complaint Response", content, rs.getString("emailAddress"));
		 }
		 if(handled==false)
		{
		 notifyEmployee(complaint,"Warning: Reply to new complaint !");
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
	
	/** a getter for all the complaints from the database 
	 * @return ArrayList<Complaint> for input of all complaints from the database
	 */
	public ArrayList<Complaint> getAllComplaints()
	{
		ResultSet rs = databaseConnection.getAll(Tables.COMPLAINTS_TABLE_NAME);
		return rsToComplaintArrayList(rs);
	}
	
	/** function that moves all the complaints from the database and put it in arraylist of complaints 
	 * @param rs used to get input from SQL database
	 * @return ArrayList<Complaint> of complaints from the database
	 */
	private ArrayList<Complaint> rsToComplaintArrayList(ResultSet rs)
	{
		ArrayList<Complaint> complaints = new ArrayList<Complaint>();
		try
		{
			while(rs.next())
			{
				Complaint complaint = new Complaint();
				int customerId = rs.getInt("customerId");
				int csEmployeeId = rs.getInt("customerServiceEmployeeId");
				User customer = UserController.getInstance().getUserById(customerId);
				User employee = UserController.getInstance().getUserById(csEmployeeId);
				complaint.setComplaintId(rs.getInt("complaintId"));
				complaint.setComplaintDetails(rs.getString("complaintDetails"));
				complaint.setComplaintResult(rs.getString("complaintResult"));
				complaint.setSubmissionTime(rs.getTimestamp("submissionTime").toInstant());
				complaint.setWasHandled(rs.getBoolean("wasHandled"));
				complaint.setCustomer(customer);
				complaint.setCustomerServiceEmployee(employee);
				complaints.add(complaint);
			}
			rs.close();
			return complaints;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
