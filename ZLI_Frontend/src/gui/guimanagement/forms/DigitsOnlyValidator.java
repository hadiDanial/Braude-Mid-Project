package gui.guimanagement.forms;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import utility.IEventListener;

public class DigitsOnlyValidator extends RegexValidator
{

	public DigitsOnlyValidator(TextField control, Label invalidLabel, boolean autoValidate, IEventListener eventListener)
	{
		super(control, "^[0-9]*$", invalidLabel, autoValidate, "Field must contain digits only", eventListener);
	}
}