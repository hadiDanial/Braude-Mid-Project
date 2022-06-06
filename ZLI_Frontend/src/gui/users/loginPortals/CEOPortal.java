package gui.users.loginPortals;

import java.net.URL;
import java.util.ResourceBundle;

import controllers.UserController;
import entities.users.User;
import gui.guimanagement.GUIController;
import gui.guimanagement.GUIPages;
import gui.guimanagement.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CEOPortal extends GUIController
{
    @FXML
    private Label nameLabel;

    @FXML
    private Label jobTitleLabel;

    @FXML
    private Button reviewReportsBtn;
    @FXML
    void onViewReportsBtn(ActionEvent event) {
<<<<<<< HEAD
        SceneManager.loadNewScene(GUIPages.VIEW_REPORTS_COMPLAINTS_CEO, true);
=======
//        SceneManager.loadNewScene(GUIPages.VIEW_REPORTS, saveToHistory);
>>>>>>> 9d442de3b383c66f0a81821bcc1d75750ee083ff
    }
    @Override
	public void initialize(URL location, ResourceBundle resources)
	{
	}
}
