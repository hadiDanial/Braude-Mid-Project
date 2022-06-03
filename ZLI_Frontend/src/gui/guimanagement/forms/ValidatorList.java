package gui.guimanagement.forms;

import java.util.List;

import javafx.scene.control.Control;

public class ValidatorList
{
	private List<Validator> validators;
	public ValidatorList(List<Validator> validators)
	{
		super();
		this.validators = validators;
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
