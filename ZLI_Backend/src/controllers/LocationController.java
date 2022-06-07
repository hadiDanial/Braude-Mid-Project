package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.DatabaseConnection;
import database.Tables;
import entities.other.Location;

public class LocationController
{
	private final String ID_FIELD_NAME = "locationId";
	private DatabaseConnection databaseConnection;
	private static LocationController instance;

	private LocationController()
	{
		databaseConnection = DatabaseConnection.getInstance();
	}

	
	/** 
	 * @return LocationController
	 */
	public static synchronized LocationController getInstance()
	{
		if (instance == null)
		{
			instance = new LocationController();
		}
		return instance;
	}

	
	/** function that initializes location details from database and put it in input
	 * @param resultSet
	 * @param useNext
	 * @param closeRS
	 * @return Location 
	 */
	public static Location convertRSToLocation(ResultSet resultSet, boolean useNext, boolean closeRS)
	{
		try
		{
			if (useNext)
				if (!resultSet.next())
					return null;
			Location loc = new Location();
			loc.setLocationId(resultSet.getInt("locationId"));
			loc.setStreet(resultSet.getString("street"));
			loc.setZipCode(resultSet.getInt("zipCode"));
			loc.setCity(resultSet.getString("city"));
			loc.setNotes(resultSet.getString("notes"));
			if (closeRS)
				resultSet.close();
			return loc;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	
	/** function that gets the id of the location from the database
	 * @param locationId
	 * @return Location of the database
	 */
	public Location getLocationById(int locationId)
	{
		ResultSet rs = databaseConnection.getByID(locationId, Tables.LOCATIONS_TABLE_NAME, ID_FIELD_NAME);
		Location location = LocationController.convertRSToLocation(rs, true, true);
		return location;
	}
}
