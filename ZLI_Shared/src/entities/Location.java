package entities;

public class Location
{
	private String city;
	private int zipCode;
	private String street;
	private String building;
	private String notes;
	public Location(String city, int zipCode, String street, String building, String notes)
	{
		super();
		this.city = city;
		this.zipCode = zipCode;
		this.street = street;
		this.building = building;
		this.notes = notes;
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
	public String getBuilding()
	{
		return building;
	}
	public void setBuilding(String building)
	{
		this.building = building;
	}
	public String getNotes()
	{
		return notes;
	}
	public void setNotes(String notes)
	{
		this.notes = notes;
	}
	
	

}