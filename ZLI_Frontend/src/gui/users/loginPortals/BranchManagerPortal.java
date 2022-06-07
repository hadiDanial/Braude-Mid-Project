package gui.users.loginPortals;

import java.net.URL;
import java.util.ResourceBundle;

import controllers.BranchController;
import controllers.UserController;
import entities.other.Branch;
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

public class BranchManagerPortal extends GUIController
{
  private User worker;
	private Branch workerBranch;
	private UserController userController;
	private BranchController branchController;
  
    @FXML
    private Label nameLabel;

    @FXML
    private Label jobTitleLabel;

    @FXML
    private Label branchLabel;
    @FXML
    private Button viewOpenOrdersBtn;

    @FXML
    private Button viewCustomersBtn;

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
    void onViewCustomersBtn(ActionEvent event) {
		SceneManager.loadNewScene(GUIPages.CUSTOMERS_LIST, true);
    }
    
    
    /** 
     * @param event
     */
    @FXML
    void onViewOpenOrdersBtn(ActionEvent event) {
		SceneManager.loadNewScene(GUIPages.OPEN_ORDERS_LIST, true);
    }

    
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
		ButtonAnimator.addButtonAnimations(viewSalesReportsBtn,viewComplaintsReportsBtn,viewOrdersReportsBtn,viewCustomersBtn, viewOpenOrdersBtn);
    userController = UserController.getInstance();
		branchController = BranchController.getInstance();
		worker = userController.getLoggedInUser();
		nameLabel.setText(worker.getFullName());
		branchController.getWorkerBranch(worker.getUserId(), new IResponse<Branch>()
		{
			@Override
			public void executeAfterResponse(Object message)
			{
				workerBranch = (Branch) message;
				branchLabel.setText(workerBranch.getBranchName());
			}
		});
	}

}
