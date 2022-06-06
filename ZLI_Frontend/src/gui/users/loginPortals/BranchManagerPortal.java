package gui.users.loginPortals;

import java.net.URL;
import java.util.ResourceBundle;

import gui.guimanagement.ButtonAnimator;
import gui.guimanagement.GUIController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class BranchManagerPortal extends GUIController
{

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
    private Button viewReportsBtn;

    @FXML
    void onViewCustomersBtn(ActionEvent event) {

    }

    @FXML
    void onViewOpenOrdersBtn(ActionEvent event) {

    }

    @FXML
    void onViewReportsBtn(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		ButtonAnimator.addButtonAnimations(viewCustomersBtn, viewOpenOrdersBtn, viewReportsBtn);
	}

}
