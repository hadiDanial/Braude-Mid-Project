package gui.users.loginPortals;

import java.net.URL;
import java.util.ResourceBundle;

import gui.guimanagement.GUIController;
import gui.guimanagement.GUIPages;
import gui.guimanagement.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CEOPortal extends GUIController
{
    private User worker;
	private UserController userController;
	
    @FXML
    private Label nameLabel;

    @FXML
    private Label jobTitleLabel;

    @FXML
    private Button reviewReportsBtn;
    @FXML
    void onViewReportsBtn(ActionEvent event) {
        SceneManager.loadNewScene(GUIPages.VIEW_REPORTS, saveToHistory);
    }
    @Override
	public void initialize(URL location, ResourceBundle resources)
	{

	}
}
