package gui.users.customers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import controllers.UserController;
import entities.users.User;
import gui.guimanagement.ButtonAnimator;
import gui.guimanagement.FormController;
import gui.guimanagement.GUIPages;
import gui.guimanagement.SceneManager;
import gui.guimanagement.forms.DigitsOnlyValidator;
import gui.guimanagement.forms.InputLengthValidator;
import gui.guimanagement.forms.Validator;
import gui.guimanagement.forms.ValidatorList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import utility.IResponse;

public class NewUser extends FormController{

    UserController userController;
    User user;

    @FXML
    private TextField firstNameField;

    @FXML
    private Label lnameErrLabel;

    @FXML
    private Label lnameErrLabel1;

    @FXML
    private TextField lastNameField;

    @FXML
    private Label fnameErrLabel;

    @FXML
    private Label fnameErrLabel1;

    @FXML
    private TextField idField;

    @FXML
    private Label idErrLabel;

    @FXML
    private Label idErrLabel1;

    @FXML
    private TextField phoneNumField;

    @FXML
    private Label phoneErrLabel;

    @FXML
    private Label phoneErrLabel1;

    @FXML
    private TextField userNameField;

    @FXML
    private Label loginNameErrLabel;

    @FXML
    private TextField emailField;

    @FXML
    private Label emailErrLabel;

    @FXML
    private Label emailErrLabel1;

    @FXML
    private TextField passwordField;

    @FXML
    private Label passwordErrLabel;

    @FXML
    private MenuButton roleDropDown;

    @FXML
    private Label roleErrLabel;
    @FXML
    private Label logN;

    @FXML
    private Button backBtn;

    @FXML
    private Button addBtn;

    @FXML
    void onAddBtn(ActionEvent event) {
        SceneManager.loadNewScene(GUIPages.CUSTOMERS_LIST, true);
    }

    @FXML
    void onBackBtn(ActionEvent event) {
        SceneManager.loadPreviousPage();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        user = new User();
        userController = UserController.getInstance();
        ButtonAnimator.addButtonAnimations(addBtn,backBtn);
        userController.getAllUsers(new IResponse<ArrayList<User>>() {

            @Override
            public void executeAfterResponse(Object message) {
                user=(User) message;
            }
            
        });
        List<Validator> complaintValidators= Arrays.asList(new Validator[]
        {new DigitsOnlyValidator(firstNameField,idErrLabel1, true, this),
        new InputLengthValidator(passwordField, passwordErrLabel, true, "Customer ID", this, 9, 9),
        new InputLengthValidator(userNameField,loginNameErrLabel, true, "Note text", this, 50, 250)});

        List<ValidatorList> complaintValditaorList= Arrays.asList(new ValidatorList[]
        {new ValidatorList(complaintValidators)});
        setupFormController(complaintValditaorList,addBtn);
    }

}
