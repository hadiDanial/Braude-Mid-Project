package enums;

import javafx.scene.paint.Color;

public enum ColorEnum {
	Mixed,
	Red("#FE0000"),
	Purple("#8710FE"),
	Blue("#001EFE"),
	Yellow("#FEFE10"),
	Bridal("#FEFEFE"),
	White("#FFFFFF");

	private String hexCode;

	ColorEnum() {
		this.hexCode = "#000000";
	}

	ColorEnum(String hexCode) {
		this.hexCode = hexCode;
	}
	
	public Color HexToColor() 
	{
	    String hex = this.hexCode.replace("#", "");
	    switch (hex.length()) {
	        case 6:
	            return new Color(
	            Integer.valueOf(hex.substring(0, 2), 16)/256.0,
	            Integer.valueOf(hex.substring(2, 4), 16)/256.0,
	            Integer.valueOf(hex.substring(4, 6), 16)/256.0, 1);
	        case 8:
	            return new Color(
	            Integer.valueOf(hex.substring(0, 2), 16)/256.0,
	            Integer.valueOf(hex.substring(2, 4), 16)/256.0,
	            Integer.valueOf(hex.substring(4, 6), 16)/256.0,
	            Integer.valueOf(hex.substring(6, 8), 16)/256.0);
	    }
	    return null;
	}
	
	@Override
	public String toString() {
		return this.name();
	}
}