package gui.guimanagement.forms;

import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
		this.message = message + "  ";
		this.eventListener = eventListener;
		if(this.invalidLabel != null)
		{			
			this.invalidLabel.setVisible(false);
			this.invalidLabel.setWrapText(true);
			this.invalidLabel.setMouseTransparent(true);
			this.invalidLabel.setFocusTraversable(false);
			this.invalidLabel.managedProperty().bind(this.invalidLabel.visibleProperty());
		}
		if(autoValidate)
		{
			control.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> 
			{
				checkValidation();
			});
		}
		control.setOnKeyPressed(new EventHandler<KeyEvent>()
		{

			@Override
			public void handle(KeyEvent event)
			{
				KeyCode code = event.getCode();
				if (code.equals(KeyCode.TAB) || code.equals(KeyCode.ENTER)) {
					checkValidation();
		        }
			}
		});
	}

	/**
	 * Check if the control's value is valid.
	 */
	public boolean checkValidation()
	{
		boolean isValid = validate();
		if(invalidLabel != null)
		{			
			invalidLabel.setVisible(!isValid);
			if(!isValid)
				invalidLabel.setText(message);
		}
		else if(!isValid) System.out.println(message);
		// Check the rest of the form so we can disable the submission button...
		eventListener.invoke(); 
		return isValid;
	}

	
	/** 
	 * @param getMessage(
	 * @return boolean
	 */
	/**
	 * Validate the control. This should be overwritten by child classes to perform custom validation as needed.
	 * @return True if the form is valid.
	 */
	protected abstract boolean validate();

	
	/** 
	 * @return String
	 */
	public String getMessage()
	{
		return message;
	}
}
