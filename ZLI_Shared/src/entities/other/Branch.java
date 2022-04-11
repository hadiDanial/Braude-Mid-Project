package entities.other;

import java.util.*;

import entities.users.User;

public class Branch
{

	private int branchId;
	private User manager;
	private ArrayList<User> workers;
	private String branchName;
	private Location location;
	
	public Branch(int branchId, User manager, ArrayList<User> workers, String branchName, Location location)
	{
		super();
		this.branchId = branchId;
		this.manager = manager;
		this.workers = workers;
		this.branchName = branchName;
		this.location = location;
	}
	
	public int getBranchId()
	{
		return branchId;
	}
	public void setBranchId(int branchId)
	{
		this.branchId = branchId;
	}
	public User getManager()
	{
		return manager;
	}
	public void setManager(User manager)
	{
		this.manager = manager;
	}
	public ArrayList<User> getWorkers()
	{
		return workers;
	}
	public void setWorkers(ArrayList<User> workers)
	{
		this.workers = workers;
	}
	public String getBranchName()
	{
		return branchName;
	}
	public void setBranchName(String branchName)
	{
		this.branchName = branchName;
	}
	
	public Location getLocation()
	{
		return location;
	}

	public void setLocation(Location location)
	{
		this.location = location;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(branchId);
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
		Branch other = (Branch) obj;
		return branchId == other.branchId;
	}
}