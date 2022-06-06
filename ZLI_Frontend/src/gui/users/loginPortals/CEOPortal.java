package gui.users.loginPortals;

import java.net.URL;
import java.util.ResourceBundle;

import gui.guimanagement.GUIController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CEOPortal extends GUIController
{

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
	}
    @FXML
    private Label nameLabel;

    @FXML
    private Label jobTitleLabel;

    @FXML
    private Label branchLabel;

    @FXML
    void onViewReportsBtn(ActionEvent event) {

    }
}
