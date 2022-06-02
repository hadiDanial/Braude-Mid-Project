package gui.guimanagement;

import java.util.List;

import gui.guimanagement.forms.ValidatedControl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import utility.IEventListener;

public abstract class FormController extends GUIController implements IEventListener
{
	protected List<ValidatedControl> validatedControls;
	protected Button submissionButton;
	
	public void setupFormController(List<ValidatedControl> validatedControls, Button submissionButton)
	{
		this.validatedControls = validatedControls;
		this.submissionButton = submissionButton;
		this.submissionButton.setDisable(true);
	}
	
	public boolean isValidForm()
	{
		if(validatedControls == null) 
		{
			submissionButton.setDisable(false);			
			return true;
		}
		for(ValidatedControl control : validatedControls)
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
