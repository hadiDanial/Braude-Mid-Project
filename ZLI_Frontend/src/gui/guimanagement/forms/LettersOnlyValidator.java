package gui.guimanagement.forms;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import utility.IEventListener;

public class LettersOnlyValidator extends RegexValidator
{

	public LettersOnlyValidator(TextField control, Label invalidLabel, boolean autoValidate, IEventListener eventListener)
	{
		super(control, "^[a-zA-Z]+$", invalidLabel, autoValidate, "Field must contain letters only", eventListener);
	}
}