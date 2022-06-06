package gui.users.customers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import controllers.UserController;
import entities.users.User;
import enums.UserRole;
import gui.guimanagement.ButtonAnimator;
import gui.guimanagement.FormController;
import gui.guimanagement.GUIPages;
import gui.guimanagement.SceneManager;
import gui.guimanagement.forms.ChoiceBoxValidator;
import gui.guimanagement.forms.DigitsOnlyValidator;
import gui.guimanagement.forms.InputLengthValidator;
import gui.guimanagement.forms.LettersOnlyValidator;
import gui.guimanagement.forms.RegexValidator;
import gui.guimanagement.forms.Validator;
import gui.guimanagement.forms.ValidatorList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import utility.IResponse;

public class NewUser extends FormController
{

	private UserController userController;
	private User user;

	@FXML private Label lnameErrLabel;
	@FXML private Label lnameErrLabel1;
	@FXML private Label fnameErrLabel;
	@FXML private Label fnameErrLabel1;
	@FXML private Label idErrLabel;
	@FXML private Label idErrLabel1;
	@FXML private Label phoneErrLabel;
	@FXML private Label phoneErrLabel1;
	@FXML private ChoiceBox<UserRole> roleDropDown;
	@FXML private Label loginNameErrLabel;
	@FXML private Label emailErrLabel;
	@FXML private Label emailErrLabel1;
	@FXML private Label passwordErrLabel;
	@FXML private Label roleErrLabel;
	@FXML private Label logN;
	@FXML private Button backBtn;
	@FXML private Button addBtn;
	
	@FXML private TextField firstNameField;
	@FXML private TextField lastNameField;
	@FXML private TextField idField;
	@FXML private TextField phoneNumField;
	@FXML private TextField userNameField;
	@FXML private TextField emailField;
	@FXML private TextField passwordField;

	@FXML
	void onAddBtn(ActionEvent event)
	{
		user.setFirstName(firstNameField.getText());
		user.setLastName(lastNameField.getText());
		user.setUserId(Integer.parseInt(idField.getText()));
		user.setPhoneNumber(phoneNumField.getText());
		user.setUsername(userNameField.getText());
		user.setEmailAddress(emailField.getText());
		user.setPassword(passwordField.getText());
		user.setRole(roleDropDown.getValue());
		userController.createNewUser(user, new IResponse<Boolean>() {

			@Override
			public void executeAfterResponse(Object message)
			{
				if((Boolean) message)
					stage.close();
				else
					SceneManager.displayErrorMessage("Failed to add user");
			}});
	}

	@FXML
	void onBackBtn(ActionEvent event)
	{
		SceneManager.loadPreviousPage();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		user = new User();
		userController = UserController.getInstance();
		ButtonAnimator.addButtonAnimations(addBtn, backBtn);
		ObservableList<UserRole> roles = FXCollections.observableArrayList();
		UserRole[] rolesArr = new UserRole[] { UserRole.BranchEmployee, UserRole.BranchManager, UserRole.Customer } ;
		roles.setAll(rolesArr);
		roleDropDown.setItems(roles);
		List<Validator> userValidators = Arrays.asList(new Validator[]
		{ new LettersOnlyValidator(firstNameField, fnameErrLabel, true, this),
				 new LettersOnlyValidator(lastNameField, lnameErrLabel, true, this),
				 new InputLengthValidator(firstNameField, fnameErrLabel1, true, "First Name", this, 2, 15),
				 new InputLengthValidator(lastNameField, lnameErrLabel1, true, "Last Name", this, 2, 15),
				 new DigitsOnlyValidator(phoneNumField, phoneErrLabel, true, this),
				 new InputLengthValidator(phoneNumField, phoneErrLabel1, true, "Phone Number", this, 9, 10),
				 new InputLengthValidator(userNameField, loginNameErrLabel, true, "Username", this, 3, 20),
				 new InputLengthValidator(passwordField, passwordErrLabel, true, "Password", this, 3, 20),
				 new RegexValidator(emailField, "^\\w+([.-]?\\w+)@\\w+([.-]?\\w+)(.\\w{2,3})+$", emailErrLabel, true, "Invalid email address", this),
				 new ChoiceBoxValidator<UserRole>(roleDropDown, roleErrLabel, true, this, "Must choose a role")
		  
				});

		List<ValidatorList> userValditaorList = Arrays.asList(new ValidatorList[]
		{ new ValidatorList(userValidators) });
		setupFormController(userValditaorList, addBtn);
	}

}
