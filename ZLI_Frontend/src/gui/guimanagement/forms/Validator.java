package gui.guimanagement.forms;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import utility.IEventListener;

public abstract class Validator
{
	protected Control control;
	protected Label invalidLabel;
	protected boolean autoValidate;
	protected String message;
	private IEventListener eventListener;

	public <T> Validator(Control control, Label invalidLabel, boolean autoValidate, String message, IEventListener eventListener)
	{
		this.control = control;
		this.invalidLabel = invalidLabel;
		this.autoValidate = autoValidate;
		this.message = message;
		this.eventListener = eventListener;
		this.invalidLabel.setVisible(false);
		this.invalidLabel.setWrapText(true);
		if(autoValidate)
		{
			control.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> 
			{
				checkValidation();
			});
		}
	}

	/**
	 * Check if the control's value is valid.
	 */
	public boolean checkValidation()
	{
		boolean isValid = validate();
		invalidLabel.setVisible(!isValid);
		if(!isValid)
			invalidLabel.setText(message);
		// Check the rest of the form so we can disable the submission button...
		eventListener.invoke(); 
		return isValid;
	}

	/**
	 * Validate the control. This should be overwritten by child classes to perform custom validation as needed.
	 * @return True if the form is valid.
	 */
	protected abstract boolean validate();

	public String getMessage()
	{
		return message;
	}
}
