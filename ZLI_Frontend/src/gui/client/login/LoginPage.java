package gui.client.login;

import java.net.URL;
import java.util.ResourceBundle;

import controllers.UserController;
import entities.users.User;
import gui.guimanagement.GUIController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    void onLogInBtn(ActionEvent event) {
    	String username = userNameInput.getText();
    	String password = passwordInput.getText();
    	if(username == null || password == null || username.isEmpty() || password.isEmpty())
    	{
    		return;
    	}
    	UserController.getInstance().login(userNameInput.getText(), passwordInput.getText(), new IResponse<User>()
		{

			@Override
			public void executeAfterResponse(Object message)
			{
				// TODO: Display error message or move to home page by user role
				if(message != null)
				{
					System.out.println((User) message);
				}
			}
		});
    }

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
	}
}
