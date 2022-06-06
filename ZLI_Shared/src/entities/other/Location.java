package entities.other;

import java.io.Serializable;

public class Location implements Serializable
{
	private int locationId;
	private String city;
	private int zipCode;
	private String street;
	private String notes;
	
	private static final long serialVersionUID = -2568448348582449249L;

	public Location()
	{
		super();
	}

	public Location(String city, int zipCode, String street, String notes)
	{
		super();
		this.city = city;
		this.zipCode = zipCode;
		this.street = street;
		this.notes = notes;
	}
	
	public int getLocationId()
	{
		return locationId;
	}

	public void setLocationId(int locationId)
	{
		this.locationId = locationId;
	}

	public String getCity()
	{
		return city;
	}
	public void setCity(String city)
	{
		this.city = city;
	}
	public int getZipCode()
	{
		return zipCode;
	}
	public void setZipCode(int zipCode)
	{
		this.zipCode = zipCode;
	}
	public String getStreet()
	{
		return street;
	}
	public void setStreet(String street)
	{
		this.street = street;
	}

	public String getNotes()
	{
		return notes;
	}
	public void setNotes(String notes)
	{
		this.notes = notes;
	}
	
	@Override
	public String toString()
	{
		return street + ", " + city + " - " + notes;
	}

}