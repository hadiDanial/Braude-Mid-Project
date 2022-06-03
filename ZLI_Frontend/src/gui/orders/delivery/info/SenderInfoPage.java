package gui.orders.delivery.info;

import java.net.URL;
import java.util.ResourceBundle;

import controllers.OrderController;
import entities.users.Order;
import gui.guimanagement.ButtonAnimator;
import gui.guimanagement.FormController;
import gui.guimanagement.GUIPages;
import gui.guimanagement.SceneManager;
import gui.guimanagement.forms.ValidatedControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SenderInfoPage extends FormController
{

    @FXML
    private TextField nameInput;

    @FXML
    private TextField phoneInput;

    @FXML
    private TextArea greetingInput;
    
    @FXML
	private Button backBtn;

	@FXML
	private Button nextBtn;

	private Order order;
	
	
    @FXML
    void onBackBtn(ActionEvent event) {
		SceneManager.loadPreviousPage();
    }

    @FXML
    void onNextBtn(ActionEvent event) {
    	order.setGreetingCard(greetingInput.getText());
    	SceneManager.loadNewScene(GUIPages.CHECKOUT_DELIVERY, true);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		setupFormController(null, nextBtn);
		this.order = OrderController.getInstance().getOrder();
		ButtonAnimator.addButtonAnimations(backBtn, nextBtn);
	}
}
