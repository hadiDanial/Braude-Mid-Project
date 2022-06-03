package gui.guimanagement;

import java.util.List;

import gui.guimanagement.forms.ValidatorList;
import javafx.scene.control.Button;
import utility.IEventListener;

public abstract class FormController extends GUIController implements IEventListener
{
	protected List<ValidatorList> validatorLists;
	protected Button submissionButton;
	
	public void setupFormController(List<ValidatorList> validatorLists, Button submissionButton)
	{
		this.validatorLists = validatorLists;
		this.submissionButton = submissionButton;
		if(validatorLists != null)
			this.submissionButton.setDisable(true);
	}
	
	public boolean isValidForm()
	{
		if(validatorLists == null) 
		{
			submissionButton.setDisable(false);			
			return true;
		}
		for(ValidatorList control : validatorLists)
		{
			if(!control.validateAll())
			{
				submissionButton.setDisable(true);
				submissionButton.setFocusTraversable(true);
				return false;
			}
		}
		submissionButton.setDisable(false);
		return true;
	}
	
	@Override
	public void invoke()
	{
		isValidForm();
	}
}
