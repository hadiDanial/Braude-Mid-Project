package gui.guimanagement.forms;

import java.util.List;

import javafx.scene.control.Control;

public class ValidatedControl
{
	private Control control;
	private List<Validator> validators;
	public ValidatedControl(Control control, List<Validator> validators)
	{
		super();
		this.control = control;
		this.validators = validators;
	}
	public Control getControl()
	{
		return control;
	}
	public void setControl(Control control)
	{
		this.control = control;
	}
	public List<Validator> getValidators()
	{
		return validators;
	}
	public void setValidators(List<Validator> validators)
	{
		this.validators = validators;
	}
	
	public boolean validateAll()
	{
		for(Validator validator : validators)
		{
			if(!validator.validate()) 
				return false;
		}
		return true;
	}
}
