package gui.guimanagement.forms;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import utility.IEventListener;

public class ChoiceBoxValidator<T> extends Validator
{

	public ChoiceBoxValidator(ChoiceBox<T> control, Label invalidLabel, boolean autoValidate, IEventListener eventListener, String selectionName)
	{
		super(control, invalidLabel, autoValidate, "Must select a " + selectionName, eventListener);
		
	}

	
	/** 
	 * @return boolean
	 */
	@Override
	protected boolean validate()
	{
		ChoiceBox<T> choiceBox = (ChoiceBox<T>) control;
		return choiceBox.getValue() != null;
	}

}
