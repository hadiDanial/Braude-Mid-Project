package gui.users.loginPortals;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.jws.soap.SOAPBinding.Use;

import controllers.UserController;
import entities.users.User;
import gui.guimanagement.ButtonAnimator;
import gui.guimanagement.GUIController;
import gui.guimanagement.GUIPages;
import gui.guimanagement.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import utility.IResponse;

public class CEOPortal extends GUIController
{
    private User worker;
    private UserController userController;
    
    @FXML
    private Label nameLabel;

    @FXML
    private Label jobTitleLabel;

    @FXML
    private Button viewSalesReportsBtn;

    @FXML
    private Button viewOrdersReportsBtn;

    @FXML
    private Button viewComplaintsReportsBtn;
   
    
    /** 
     * @param event
     */
    @FXML
    void onViewSalesReportsBtn(ActionEvent event) {
      SceneManager.loadNewScene(GUIPages.VIEW_REPORTS_SALES, true);
    }
    
    /** 
     * @param event
     */
    @FXML
    void onViewOrdersReportsBtn(ActionEvent event) {
      SceneManager.loadNewScene(GUIPages.VIEW_REPORTS_ORDERS,true);
    }
    
    /** 
     * @param event
     */
    @FXML
    void onViewComplaintsReportsBtn(ActionEvent event) {
      SceneManager.loadNewScene(GUIPages.VIEW_REPORTS_COMPLAINTS,true);
    }
    
    
    /** 
     * @param location
     * @param resources
     */
    @Override
	public void initialize(URL location, ResourceBundle resources)
	{
        worker=new User();
        userController=UserController.getInstance();
        ButtonAnimator.addButtonAnimations(viewComplaintsReportsBtn,viewOrdersReportsBtn,viewSalesReportsBtn);
        userController.getAllUsers(new IResponse<ArrayList<User>>() {

            @Override
            public void executeAfterResponse(Object message) {
                    worker=(User)message;                
            }
        });
	}
}
