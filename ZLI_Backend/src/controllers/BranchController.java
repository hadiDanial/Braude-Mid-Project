package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DatabaseConnection;
import database.Tables;
import entities.other.Branch;
import entities.other.Location;
import entities.users.User;

public class BranchController
{
	private final String ID_FIELD_NAME = "branchId";
	private DatabaseConnection databaseConnection;
	private static BranchController instance;
	private LocationController locationController;

	private BranchController()
	{
		databaseConnection = DatabaseConnection.getInstance();
		locationController = LocationController.getInstance();
	}

	public static synchronized BranchController getInstance()
	{
		if (instance == null)
		{
			instance = new BranchController();
		}
		return instance;
	}

	public Branch getBranchById(int branchId)
	{
		ResultSet rs = databaseConnection.getByID(branchId, Tables.BRANCHES_TABLE_NAME, ID_FIELD_NAME);
		Branch branch = convertRSToBranch(rs, true);
		Location location = locationController.getLocationById(branch.getLocation().getLocationId());
		branch.setLocation(location);
		return branch;
	}

	/**
	 * function that takes details of branch table and move it to branch object
	 * @param rs
	 * @param useNext
	 * @return a branch details from database 
	 */
	public static Branch convertRSToBranch(ResultSet rs, boolean useNext)
	{
		String[] columnNames = Tables.branchColumnNames;
		try
		{
			if(useNext)
				if(!rs.next())
					return null;
			Branch branch = new Branch();
			branch.setBranchId(rs.getInt(columnNames[0]));
			User user = new User();
			user.setUserId(rs.getInt(columnNames[1]));
			branch.setManager(user);
			branch.setBranchName(rs.getString(columnNames[2]));
			Location loc = new Location();
			loc.setLocationId(rs.getInt(columnNames[3]));
			branch.setLocation(loc);
			return branch;
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 
	 * @return an arraylist of branches that are moved from branch table of the database
	 */
	public ArrayList<Branch> getAllBranches()
	{
		ArrayList<String> names = new ArrayList<String>();
		names.add(Tables.BRANCHES_TABLE_NAME);
		names.add(Tables.LOCATIONS_TABLE_NAME);
		String selects = "branchId, managerId, branchName, branches.locationId, city, street, notes, zipCode ";
		String conditions = "branches.locationId=locations.locationId";
		ResultSet rs = databaseConnection.getJoinResultsWithSelectColumns(names, selects, conditions);
		ArrayList<Branch> branches = new ArrayList<Branch>();
		try
		{
			while (rs.next())
			{
				Branch branch = convertRSToBranch(rs, false);
				Location loc = LocationController.convertRSToLocation(rs, false, false);				
				branch.setLocation(loc);
				branches.add(branch);
			}
			rs.close();
			return branches;			
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	/**function that searches for branch employee using his id in the database
	 * 
	 * @param userId of the branch employee
	 * @return branch object of the branch employee from branch table in the database
	 */
	public Branch getWorkerBranch(int userId)
	{
		ArrayList<String> names = new ArrayList<String>();
		names.add(Tables.BRANCHES_WORKERS_TABLE_NAME);
		names.add(Tables.BRANCHES_TABLE_NAME);
		names.add(Tables.LOCATIONS_TABLE_NAME);
		String selects = " branches.branchId, branches.managerId, branches.locationId, branches.branchName, "
				+ "locations.city, locations.notes, locations.street, locations.zipCode";
		String conditions = "branch_workers.workerId=" + userId + " AND branch_workers.branchId=branches.branchId AND locations.locationId = branches.locationId";
		ResultSet rs = databaseConnection.getJoinResultsWithSelectColumns(names, selects, conditions);
		return convertRSToBranch(rs, true);
	}

}
