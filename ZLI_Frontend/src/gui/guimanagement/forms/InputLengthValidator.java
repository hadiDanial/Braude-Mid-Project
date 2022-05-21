package gui.guimanagement.forms;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import utility.IEventListener;

public class InputLengthValidator extends Validator
{

	private int maxLength;
	private int minLength;

	public InputLengthValidator(TextField control, Label invalidLabel, boolean autoValidate, String fieldName, IEventListener eventListener, int minLength, int maxLength)
	{
		super(control, invalidLabel, autoValidate, fieldName + " must be between " + minLength + " and " + maxLength + " characters.", eventListener);
		this.minLength = minLength;
		this.maxLength = maxLength;
	}

	@Override
	public boolean validate()
	{
		String text =  ((TextField)control).getText();
		return text.length() >= minLength && text.length() <= maxLength;
	}

}
