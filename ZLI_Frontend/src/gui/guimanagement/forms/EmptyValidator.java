package gui.guimanagement.forms;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import utility.IEventListener;

public class EmptyValidator extends Validator
{

	public EmptyValidator(TextField control, Label invalidLabel, boolean autoValidate, String fieldName, IEventListener eventListener)
	{
		super(control, invalidLabel, autoValidate, fieldName + " can't be empty.", eventListener);	
	}

	
	/** 
	 * @return boolean
	 */
	@Override
	public boolean validate()
	{
		return ((TextField)control).getText().length() > 0;
	}

}
