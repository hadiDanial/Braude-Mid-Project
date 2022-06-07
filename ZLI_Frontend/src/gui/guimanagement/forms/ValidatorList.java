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
	
	/** 
	 * @return List<Validator>
	 */
	public List<Validator> getValidators()
	{
		return validators;
	}
	
	/** 
	 * @param validators
	 */
	public void setValidators(List<Validator> validators)
	{
		this.validators = validators;
	}
	
	
	/** 
	 * @return boolean
	 */
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
