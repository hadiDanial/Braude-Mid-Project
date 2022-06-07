package gui.complaints;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import controllers.ComplaintController;
import entities.users.Complaint;
import entities.users.User;
import gui.guimanagement.ButtonAnimator;
import gui.guimanagement.FormController;
import gui.guimanagement.GUIController;
import gui.guimanagement.SceneManager;
import gui.guimanagement.forms.ChoiceBoxValidator;
import gui.guimanagement.forms.Validator;
import gui.guimanagement.forms.ValidatorList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import utility.IResponse;

public class ComplaintHandling extends FormController {
    private Complaint complaint;
    private ObservableList<String> resolutions = FXCollections.observableArrayList();

    @FXML
    private Label customerIDLabel;

    @FXML
    private Label resolutionErrorLabel;

    @FXML
    private Text complaintNoteText;

    @FXML
    private ChoiceBox<String> resolutionChoice;

    @FXML
    private Button backBtn;

    @FXML
    private Button resolveBtn;

    @FXML
    void onBackBtn(ActionEvent event) {
        SceneManager.loadPreviousPage();
    }

    @FXML
    void onResolveBtn(ActionEvent event) {
        complaint.setWasHandled(true);
        complaint.setComplaintResult(resolutionChoice.getValue());
        ComplaintController.getInstance().handleComplaint(complaint, resolutionChoice.getValue());
        SceneManager.loadPreviousPage();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (complaint == null) {
            ComplaintController.getInstance().getAllComplaints(new IResponse<ArrayList<Complaint>>() {
                @Override
                public void executeAfterResponse(Object message) {
                    try {
                        ArrayList<Complaint> complaintList = (ArrayList<Complaint>) message;
                        complaint = complaintList.get(1);

                    } catch (Exception e) {
                        User customer = new User();
                        customer.setUserId(895177);
                        complaint = new Complaint();
                        complaint.setCustomer(customer);
                        complaint.setComplaintDetails("The Flower Pot Was broken");
                    }
                }
            });
        }

        resolutions.add("Full Refund");
        resolutions.add("50%  Refund");
        resolutions.add("No  Refund");

        resolutionChoice.setItems(resolutions);

        customerIDLabel.setText(complaint.getCustomer().getUserId() + "");
        complaintNoteText.setText(complaint.getComplaintDetails());

        List<Validator> resolutionValidators = Arrays.asList(new Validator[] {
                new ChoiceBoxValidator<String>(resolutionChoice, resolutionErrorLabel, true, this, "Resolution") });
        ValidatorList resolutionChecker = new ValidatorList(resolutionValidators);

        setupFormController(Arrays.asList(new ValidatorList[] {resolutionChecker }), resolveBtn);
        
        ButtonAnimator.addButtonAnimations(resolveBtn);
        ButtonAnimator.addButtonAnimations(backBtn);
    }

}
