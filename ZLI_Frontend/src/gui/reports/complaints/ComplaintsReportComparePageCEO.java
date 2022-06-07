package gui.reports.complaints;
import java.net.URL;
import java.util.ResourceBundle;

import gui.guimanagement.GUIController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

public class ComplaintsReportComparePageCEO extends GUIController{

    @FXML
    private ChoiceBox firstQuarterDropDown;

    @FXML
    private Label quarterErrLabel;

    @FXML
    private TextField firstYearField;

    @FXML
    private Label yearErrLabel;

    @FXML
    private ChoiceBox<?> secondBranchChoice;

    @FXML
    private Label branchErrLabel1;

    @FXML
    private ChoiceBox secondQuarterDropDown;

    @FXML
    private Label quarterErrLabel1;

    @FXML
    private TextField secondYearField;

    @FXML
    private Label yearErrLabel1;

    @FXML
    private ChoiceBox<?> firstBranchChoice;

    @FXML
    private Label branchErrLabel2;

    @FXML
    private BarChart<?, ?> complaintsChart;

    
    /** 
     * @param event
     */
    @FXML
    void onBackBtn(ActionEvent event) {

    }

    
    /** 
     * @param event
     */
    @FXML
    void onSetQuarterBtn(ActionEvent event) {

    }

    
    /** 
     * @param event
     */
    @FXML
    void onViewComplaintsReportsBtn(ActionEvent event) {

    }

    
    /** 
     * @param event
     */
    @FXML
    void onViewOrdersReportsBtn(ActionEvent event) {

    }

    
    /** 
     * @param event
     */
    @FXML
    void onViewSalesReportsBtn(ActionEvent event) {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        
    }

}
