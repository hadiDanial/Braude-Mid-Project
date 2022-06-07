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
	
	
	/** 
	 * @return int
	 */
	public int getLocationId()
	{
		return locationId;
	}

	
	/** 
	 * @param locationId
	 */
	public void setLocationId(int locationId)
	{
		this.locationId = locationId;
	}

	
	/** 
	 * @return String
	 */
	public String getCity()
	{
		return city;
	}
	
	/** 
	 * @param city
	 */
	public void setCity(String city)
	{
		this.city = city;
	}
	
	/** 
	 * @return int
	 */
	public int getZipCode()
	{
		return zipCode;
	}
	
	/** 
	 * @param zipCode
	 */
	public void setZipCode(int zipCode)
	{
		this.zipCode = zipCode;
	}
	
	/** 
	 * @return String
	 */
	public String getStreet()
	{
		return street;
	}
	
	/** 
	 * @param street
	 */
	public void setStreet(String street)
	{
		this.street = street;
	}

	
	/** 
	 * @return String
	 */
	public String getNotes()
	{
		return notes;
	}
	
	/** 
	 * @param notes
	 */
	public void setNotes(String notes)
	{
		this.notes = notes;
	}
	
	
	/** 
	 * @return String
	 */
	@Override
	public String toString()
	{
		return street + ", " + city + " - " + notes;
	}

}