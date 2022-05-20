package gui.client.login;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import controllers.UserController;
import entities.users.User;
import gui.guimanagement.GUIController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import utility.IResponse;

public class LoginPage extends GUIController
{
	@FXML
	private TextField userNameInput;

	@FXML
	private PasswordField passwordInput;

	@FXML
	private Button loginButton;

	ValidationSupport validationSupport;

	@FXML
	void onLoginBtn(ActionEvent event)
	{
		String username = userNameInput.getText();
		String password = passwordInput.getText();
		if (username == null || password == null || username.isEmpty() || password.isEmpty())
		{
			return;
		}
		UserController.getInstance().login(userNameInput.getText(), passwordInput.getText(), new IResponse<User>()
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
							UserController.getInstance().openHomePage();
						}
					}
				});
			}
		});
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
//		int minLength = 3, maxLength = 20;
//		Predicate<TextField> inputLengthPredicate = new Predicate<TextField>()
//		{
//			
//			@Override
//			public boolean test(TextField t)
//			{
//				int length = t.getText().length();
//				return length <= maxLength && length >= minLength;
//			}
//		}; 
//		String lengthErrorMessage = "must be between " + minLength + " and " + maxLength + " characters.";
//		validationSupport = new ValidationSupport();
//		validationSupport.registerValidator(userNameInput, Validator.createEmptyValidator("Please enter a username."));
////		validationSupport.registerValidator(userNameInput, Validator.createPredicateValidator(inputLengthPredicate, "Username " + lengthErrorMessage));
//		validationSupport.registerValidator(passwordInput, Validator.createEmptyValidator("Please enter a password."));
////		validationSupport.registerValidator(passwordInput, Validator.createPredicateValidator(inputLengthPredicate, "Password " + lengthErrorMessage));
//		loginButton.disableProperty().bind(userNameInput.textProperty().isEmpty().or(passwordInput.textProperty().isEmpty()));
	}

}
