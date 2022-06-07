package gui.users.loginPortals;

import java.net.URL;
import java.util.ResourceBundle;

import controllers.OrderController;
import entities.users.User;
import gui.guimanagement.ButtonAnimator;
import gui.guimanagement.GUIController;
import gui.guimanagement.GUIPages;
import gui.guimanagement.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class DeliveryOperatorPortal extends GUIController{

    private User worker;
    OrderController orderController;
    
    @FXML
    private Label nameLabel;

    @FXML
    private Label jobTitleLabel;

    @FXML
    private Label branchLabel;

    @FXML
    private Button VrydlBtn;

    @FXML
    void onVrydlBtn(ActionEvent event) {
        SceneManager.loadNewScene(GUIPages.DELIVERY_LIST, true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        orderController = OrderController.getInstance();
        nameLabel.setText(worker.getFullName());
        jobTitleLabel.setText((worker.getRole().name()));
        ButtonAnimator.addButtonAnimations(VrydlBtn);

    }

}
