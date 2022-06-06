package gui.complaints;

import java.net.URL;
import java.time.Instant;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controllers.ComplaintController;
import entities.users.Complaint;
import gui.guimanagement.ButtonAnimator;
import gui.guimanagement.FormController;
import gui.guimanagement.GUIPages;
import gui.guimanagement.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import utility.IResponse;

public class OpenedComplaints extends FormController{
    private ComplaintController complaintController;
    public static ObservableList<Complaint> complaintsList = FXCollections.observableArrayList();

    @FXML
    private TableView<Complaint> complaintsTable;

    @FXML
    private TableColumn<Complaint,Integer> complaintIDColumn;

    @FXML
    private TableColumn<Complaint,Integer> customerIDColumn;

    @FXML
    private TableColumn<Complaint,String> customerNameColumn;

    @FXML
    private TableColumn<Complaint,Instant> dateOpenedColumn;

    @FXML
    private TableColumn<Complaint, Long> timeSinceOpeningColumn;

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
        complaintsTable.setItems(complaintsList);
        complaintController.getAllComplaints(new IResponse<ArrayList<Complaint>>() {

            @Override
            public void executeAfterResponse(Object message) {
                if (message == null)
                SceneManager.displayErrorMessage("Failed to load complaints!");
                else 
                    complaintsList.setAll((ArrayList<Complaint>)message);
            }
        });
    }

    private void initializeTableColumns() 
    {
        complaintIDColumn.setCellValueFactory(new PropertyValueFactory<Complaint,Integer>("ComplaintId"));
        customerIDColumn.setCellValueFactory(new PropertyValueFactory<Complaint,Integer>("Customer"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<Complaint,String>("Customer"));
        dateOpenedColumn.setCellValueFactory(new PropertyValueFactory<Complaint,Instant>("FormattedopenedDate"));
        timeSinceOpeningColumn.setCellValueFactory(new PropertyValueFactory<Complaint,Long>("Duration"));
        reviewColumn.setCellValueFactory(new PropertyValueFactory<Complaint,Complaint>("ComplaintDetails"));
    }


}
