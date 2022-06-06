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

public class EmployeePortalCS extends GUIController
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
    private Button reviewComplaintsbButton;

    @FXML 
    private Button addSurveyButton;
    
    @FXML
    void onFeedBackSurveyBtn(ActionEvent event) {
        SceneManager.loadNewScene(GUIPages.ADD_SURVEY, true);
    }

    @FXML
    void onReviewComplaintsBtn(ActionEvent event) {
        SceneManager.loadNewScene(GUIPages.OPENED_COMPLAINTS_PAGE,true);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
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

		ButtonAnimator.addButtonAnimations(addSurveyButton,reviewComplaintsbButton);
	}

}
