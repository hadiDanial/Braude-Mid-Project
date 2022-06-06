package gui.newPages.customerService.complaints;

import java.net.URL;
import java.time.Instant;
import java.util.ResourceBundle;

import controllers.ComplaintController;
import entities.users.Complaint;
import gui.guimanagement.ButtonAnimator;
import gui.guimanagement.FormController;
import gui.guimanagement.GUIPages;
import gui.guimanagement.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;

public class OpenedComplaints extends FormController{
    private ComplaintController complaintController;
    private Complaint complaint;
    
    @FXML
    private TableColumn<Complaint,Integer> complaintIDColumn;

    @FXML
    private TableColumn<Complaint,Integer> customerIDColumn;

    @FXML
    private TableColumn<Complaint,String> customerNameColumn;

    @FXML
    private TableColumn<Complaint,Instant> dateOpenedColumn;

    @FXML
    private TableColumn<Complaint, Instant> timeSinceOpeningColumn;

    @FXML
    private TableColumn<Complaint, Complaint> reviewColumn;

    @FXML
    private Button backBtn;

    @FXML
    private Button addBtn;
    
    @FXML
    void onAddBtn(ActionEvent event) {
        SceneManager.loadNewScene(GUIPages.NEW_COMPLAINT_PAGE, true);
    }

    @FXML
    void onBackBtn(ActionEvent event) {
        SceneManager.loadPreviousPage();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ButtonAnimator.addButtonAnimations(addBtn,backBtn);
        complaintController = ComplaintController.getInstance();
        initializeTableColumns();

    }

    private void initializeTableColumns() {
    }


}
