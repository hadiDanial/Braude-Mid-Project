package enums;

public enum Color
{
	Mixed,
	Red("#FE0000"),
	Purple("#8710FE"),
	Blue("#001EFE"),
	Yellow("#FEFE10"),
	Bridal,
	White("#FFFFFF");
	
	private String hexCode;
	
	Color()
	{
		this.hexCode = "#000000";
	}
	Color(String hexCode)
	{
		this.hexCode = hexCode;
	}
	
	@Override
	public String toString()
	{
		return this.name() + ": " + this.hexCode;
	}
}