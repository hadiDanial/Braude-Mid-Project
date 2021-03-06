package gui.guimanagement.forms;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import utility.IEventListener;

public class InputLengthValidator extends Validator
{

	private int maxLength;
	private int minLength;

	public InputLengthValidator(TextInputControl control, Label invalidLabel, boolean autoValidate, String fieldName, IEventListener eventListener, int minLength, int maxLength)
	{
		super(control, invalidLabel, autoValidate, fieldName + " must be between " + minLength + " and " + maxLength + " characters.", eventListener);
		this.minLength = minLength;
		this.maxLength = maxLength;
		if(minLength == maxLength)
			this.message = fieldName + " must be exactly " + minLength + " characters long.  ";
	}

	
	/** 
	 * @return boolean
	 */
	@Override
	public boolean validate()
	{
		String text =  ((TextInputControl)control).getText();
		return text.length() >= minLength && text.length() <= maxLength;
	}

}
