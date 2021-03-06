package gui.client.login;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import controllers.UserController;
import entities.users.User;
import gui.guimanagement.ButtonAnimator;
import gui.guimanagement.FormController;
import gui.guimanagement.SceneManager;
import gui.guimanagement.forms.InputLengthValidator;
import gui.guimanagement.forms.ValidatorList;
import gui.guimanagement.forms.Validator;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import utility.IResponse;

public class LoginPage extends FormController
{
	@FXML
	private TextField usernameInput;

	@FXML
	private PasswordField passwordInput;

	@FXML
	private Label usernameErrorLabel;

	@FXML
	private Label passwordErrorLabel;

	@FXML
	private Button loginButton;

	
	/** 
	 * @param event
	 */
	@FXML
	void onLoginBtn(ActionEvent event)
	{
		String username = usernameInput.getText();
		String password = passwordInput.getText();
		if (username == null || password == null || username.isEmpty() || password.isEmpty())
		{
			return;
		}
		UserController.getInstance().login(usernameInput.getText(), passwordInput.getText(), new IResponse<User>()
		{

			@Override
			public void executeAfterResponse(Object message)
			{
				User loggedInUser = (User) message;
				UserController.getInstance().setLoggedInUser(loggedInUser);
				Platform.runLater(new Runnable()
				{

					@Override
					public void run()
					{
						// TODO: Display error message or move to home page by user role
						if (message != null)
						{
							SceneManager.openHomePage();
						}
						else
						{
							SceneManager.displayErrorMessage("Failed to login...");
						}
					}
				});
			}
		});
	}

	
	/** 
	 * @param location
	 * @param resources
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		int minLength = 3, maxLength = 20;
		List<Validator> usernameValidators = Arrays.asList(new Validator[]
		{ new InputLengthValidator(usernameInput, usernameErrorLabel, true, "Username", this, minLength, maxLength) });
		ValidatorList usernameChecker = new ValidatorList(usernameValidators);
		List<Validator> passwordValidators = Arrays.asList(new Validator[]
		{ new InputLengthValidator(passwordInput, passwordErrorLabel, true, "Password", this, minLength, maxLength) });
		ValidatorList passwordChecker = new ValidatorList(passwordValidators);
		setupFormController(Arrays.asList(new ValidatorList[] {usernameChecker, passwordChecker}), loginButton);
		ButtonAnimator.addButtonAnimations(loginButton);
	}

}
