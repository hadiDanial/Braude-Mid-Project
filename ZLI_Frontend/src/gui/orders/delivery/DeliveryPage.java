package gui.orders.delivery;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;

import gui.guimanagement.ButtonAnimator;
import gui.guimanagement.GUIController;
import gui.guimanagement.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class DeliveryPage extends GUIController
{
	private String styleActive = "-fx-border-color: #05595B; -fx-border-radius: 15; -fx-background-radius: 15;";
	private String styleInactive = "-fx-min-height: 0;";

	@FXML
	private ToggleGroup deliveryGroup;

	@FXML
	private VBox pickupView;

	@FXML
	private ChoiceBox<?> branchSelection;

	@FXML
	private JFXTimePicker pickupTimePicker;

	@FXML
	private JFXDatePicker datePicker;

	@FXML
	private VBox deliveryView;

	@FXML
	private ChoiceBox<String> citySelection;

	@FXML
	private TextField nameInput1;

	@FXML
	private TextField phoneInput;

	@FXML
	private TextField addressInput;

	@FXML
	private JFXTimePicker deliveryTimePicker;

	@FXML
	private JFXDatePicker deliveryDatePicker;

	@FXML
	private TextArea noteInput;

	@FXML
	private Button backBtn;

	@FXML
	private Button nextBtn;

	@FXML
	void onBackBtn(ActionEvent event)
	{
		SceneManager.loadPreviousPage();
	}

	@FXML
	void onDeliverySelect(ActionEvent event)
	{
		enableDeliveryView();
	}

	@FXML
	void onNextBtn(ActionEvent event)
	{

	}

	@FXML
	void onPickupSelect(ActionEvent event)
	{
		enablePickupView();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		pickupView.managedProperty().bind(pickupView.visibleProperty());
		deliveryView.managedProperty().bind(deliveryView.visibleProperty());
		enablePickupView();
		ButtonAnimator.addButtonAnimations(backBtn, nextBtn);
	}

	private void enablePickupView()
	{
		pickupView.setVisible(true);
		deliveryView.setVisible(false);
	}

	private void enableDeliveryView()
	{
		pickupView.setVisible(false);
		deliveryView.setVisible(true);
	}
}
