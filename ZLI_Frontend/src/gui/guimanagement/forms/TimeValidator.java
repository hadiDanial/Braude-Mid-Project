package gui.guimanagement.forms;

import java.time.LocalTime;

import com.jfoenix.controls.JFXTimePicker;

import javafx.scene.control.Label;
import utility.IEventListener;

public class TimeValidator extends Validator
{

	private boolean mustBeAfterCurrentTime;

	public TimeValidator(JFXTimePicker control, boolean mustBeAfterCurrentTime, Label invalidLabel, boolean autoValidate, String message,
			IEventListener eventListener)
	{
		super(control, invalidLabel, autoValidate, message, eventListener);
		this.mustBeAfterCurrentTime = mustBeAfterCurrentTime;
	}

	@Override
	protected boolean validate()
	{
		JFXTimePicker timePicker = (JFXTimePicker) control;
		LocalTime time = timePicker.getValue(); 
		if(time != null)
		{
			if(mustBeAfterCurrentTime)
			{
				return time.isAfter(LocalTime.now());
			}
			return true;
		}
		else
		{
			return false;			
		}
	}

}
