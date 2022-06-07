package gui.complaints;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import controllers.ComplaintController;
import entities.users.Complaint;
import gui.guimanagement.ButtonAnimator;
import gui.guimanagement.FormController;
import gui.guimanagement.GUIController;
import gui.guimanagement.GUIPages;
import gui.guimanagement.SceneManager;
import gui.guimanagement.forms.DigitsOnlyValidator;
import gui.guimanagement.forms.InputLengthValidator;
import gui.guimanagement.forms.LettersOnlyValidator;
import gui.guimanagement.forms.Validator;
import gui.guimanagement.forms.ValidatorList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import utility.IResponse;

public class NewComplaint extends FormController{

    @FXML
    private TextField customerIDField;

    @FXML
    private Label digitlengthError1;

    @FXML
    private Label onlydigitError2;
    
    @FXML 
    private TextField complaintNote;
    
    @FXML
    private TextArea complaintNoteAria;

    @FXML
    private Label notebetweenError1;
    
    @FXML
    private Button backBtn;

    @FXML
    private Button addBtn;

    private ComplaintController complaintController;
    private Complaint complaint;

    
    /** 
     * @param event
     */
    @FXML
    void onAddBtn(ActionEvent event) {
        SceneManager.loadNewScene(GUIPages.OPENED_COMPLAINTS_PAGE, true);
    }

    
    /** 
     * @param event
     */
    @FXML
    void onBackBtn(ActionEvent event) {
        SceneManager.loadPreviousPage();
    }

    
    /** 
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ButtonAnimator.addButtonAnimations(addBtn,backBtn);
        complaintController = ComplaintController.getInstance();
        complaintController.getAllComplaints(new IResponse<ArrayList<Complaint>>() {

            @Override
            public void executeAfterResponse(Object message) {
                complaint=(Complaint) message;
                customerIDField.setText(String.valueOf(complaint.getCustomer().getUserId()));

            }
            
        });
        List<Validator> complaintValidators= Arrays.asList(new Validator[]
        {new DigitsOnlyValidator(customerIDField, onlydigitError2, true, this),
        new InputLengthValidator(customerIDField, digitlengthError1, true, "Customer ID", this, 9, 9),
        new InputLengthValidator(complaintNoteAria,notebetweenError1, true, "Note text", this, 50, 250)});

        List<ValidatorList> complaintValditaorList= Arrays.asList(new ValidatorList[]
        {new ValidatorList(complaintValidators)});
        setupFormController(complaintValditaorList,addBtn);
    }

}
