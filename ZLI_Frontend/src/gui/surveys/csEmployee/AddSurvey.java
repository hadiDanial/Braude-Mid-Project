package gui.surveys.csEmployee;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;

import gui.guimanagement.FormController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddSurvey extends FormController
{

    @FXML
    private TextField nameField;

    @FXML
    private JFXDatePicker startDatePicker;

    @FXML
    private JFXDatePicker endDatePicker;

    @FXML
    private TextField q1Field;

    @FXML
    private TextField q2Field;

    @FXML
    private TextField q3Field;

    @FXML
    private TextField q4Field;

    @FXML
    private TextField q5Field;

    @FXML
    private TextField q6Field;

    
    /** 
     * @param event
     */
    @FXML
    void onAddBtn(ActionEvent event) {

    }

    
    /** 
     * @param event
     */
    @FXML
    void onBackBtn(ActionEvent event) {

    }

	
    /** 
     * @param location
     * @param resources
     */
    @Override
	public void initialize(URL location, ResourceBundle resources)
	{
	}

}
