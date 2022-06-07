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

public class ComplaintsReportPageCEO extends GUIController {

    @FXML
    private ChoiceBox quarterDropDown;

    @FXML
    private Label quarterErrLabel;

    @FXML
    private TextField yearField;

    @FXML
    private Label yearErrLabel;

    @FXML
    private ChoiceBox<?> secondBranchChoice;

    @FXML
    private Label branchErrLabel;

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
