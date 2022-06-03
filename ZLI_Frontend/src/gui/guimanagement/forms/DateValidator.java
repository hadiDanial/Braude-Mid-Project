package gui.guimanagement.forms;

import java.time.LocalDate;
import java.time.LocalTime;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;

import javafx.scene.control.Label;
import utility.IEventListener;

public class DateValidator extends Validator
{

	private boolean canBePastDate;

	public DateValidator(JFXDatePicker control, boolean canBePastDate, Label invalidLabel, boolean autoValidate, String message,
			IEventListener eventListener)
	{
		super(control, invalidLabel, autoValidate, message, eventListener);
		this.canBePastDate = canBePastDate;
	}

	@Override
	protected boolean validate()
	{
		JFXDatePicker datePicker = (JFXDatePicker) control;
		LocalDate date = datePicker.getValue(); 
		if(date != null)
		{
			if(!canBePastDate)
			{
				return !date.isBefore(LocalDate.now());
			}
			return true;
		}
		else
		{
			return false;			
		}
	}

	public JFXDatePicker getDatePicker()
	{
		return (JFXDatePicker) control;
	}

}
