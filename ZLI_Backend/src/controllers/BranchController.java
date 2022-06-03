package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;

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

	private BranchController()
	{
		databaseConnection = DatabaseConnection.getInstance();
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
		Location location = LocationController.getInstance().getLocationById(branch.getLocation().getLocationId());
		branch.setLocation(location);
		return branch;
	}

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

}
