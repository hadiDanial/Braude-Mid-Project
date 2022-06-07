package gui.guimanagement.forms;

import java.util.regex.Pattern;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import utility.IEventListener;

public class RegexValidator extends Validator
{

	private String pattern;

	public RegexValidator(TextField control, String pattern, Label invalidLabel, boolean autoValidate, String message,
			IEventListener eventListener)
	{
		super(control, invalidLabel, autoValidate, message, eventListener);
		this.pattern = pattern;
	}

	
	/** 
	 * @return boolean
	 */
	@Override
	protected boolean validate()
	{
		String text = ((TextField) control).getText();
		if(text == null) return false;
		return (Pattern.matches(pattern, text));
	}

}
