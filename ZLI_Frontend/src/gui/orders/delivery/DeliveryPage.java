package gui.orders.delivery;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;

import controllers.BranchController;
import controllers.OrderController;
import entities.other.Branch;
import entities.other.Location;
import entities.users.Order;
import entities.users.Delivery;
import gui.guimanagement.ButtonAnimator;
import gui.guimanagement.FormController;
import gui.guimanagement.GUIController;
import gui.guimanagement.GUIPages;
import gui.guimanagement.SceneManager;
import gui.guimanagement.forms.ChoiceBoxValidator;
import gui.guimanagement.forms.DateValidator;
import gui.guimanagement.forms.DigitsOnlyValidator;
import gui.guimanagement.forms.InputLengthValidator;
import gui.guimanagement.forms.LettersOnlyValidator;
import gui.guimanagement.forms.TimeValidator;
import gui.guimanagement.forms.ValidatorList;
import gui.guimanagement.forms.Validator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import utility.IResponse;

public class DeliveryPage extends FormController
{
	private String styleActive = "-fx-border-color: #05595B; -fx-border-radius: 15; -fx-background-radius: 15;";
	private String styleInactive = "-fx-min-height: 0;";

	@FXML
	private ToggleGroup deliveryGroup;

	@FXML
	private VBox pickupView;

	@FXML
	private ChoiceBox<Branch> branchSelection;

	@FXML
	private JFXTimePicker pickupTimePicker;

	@FXML
	private JFXDatePicker datePicker;

	@FXML
	private VBox deliveryView;

	@FXML
	private ChoiceBox<String> citySelection;

	@FXML
	private TextField nameInput;

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
	private BranchController branchController;
	private OrderController orderController;
	private ObservableList<Branch> branches = FXCollections.observableArrayList();
	private ObservableList<String> cities = FXCollections.observableArrayList();
	private ArrayList<Branch> branchesArray;
	private HashSet<String> citiesSet;
	private boolean isDelivery;
	private Delivery delivery = null;
	private Order order;
	private List<ValidatorList> pickupChecker;
	private List<ValidatorList> deliveryChecker;

	@FXML
	void onBackBtn(ActionEvent event)
	{
		SceneManager.loadPreviousPage();
	}

	@FXML
	void onNextBtn(ActionEvent event)
	{
		LocalDateTime dateTime;
		if(isDelivery)
		{
			Location loc = null;
			for (Branch branch : branchesArray)
			{
				if(branch.getLocation().getCity().equals(citySelection.getValue()))
				{
					loc = branch.getLocation();
					order.setBranch(branch);
				}
			}
			delivery = new Delivery(order, nameInput.getText(), phoneInput.getText(), loc);
			order.setDeliveryDetails(delivery);
			dateTime = LocalDateTime.of(deliveryDatePicker.getValue(), deliveryTimePicker.getValue());
		}
		else
		{
			dateTime = LocalDateTime.of(datePicker.getValue(), pickupTimePicker.getValue());
			order.setBranch(branchSelection.getValue());
		}
		order.setDeliveryDate(dateTime.toInstant(ZoneOffset.UTC));
		SceneManager.loadNewScene(GUIPages.CHECKOUT_PAYMENT, true);
	}

	@FXML
	void onDeliverySelect(ActionEvent event)
	{
		enableDeliveryView();
		isDelivery = true;
	}

	@FXML
	void onPickupSelect(ActionEvent event)
	{
		enablePickupView();
		isDelivery = false;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		pickupView.managedProperty().bind(pickupView.visibleProperty());
		deliveryView.managedProperty().bind(deliveryView.visibleProperty());
		branchController = BranchController.getInstance();
		orderController = OrderController.getInstance();
		order = orderController.getOrder();
		branchController.getAllBranches(new IResponse<ArrayList<Branch>>()
		{
			@Override
			public void executeAfterResponse(Object message)
			{
				branchesArray = (ArrayList<Branch>) message;
				setupBranchChoiceBoxes();
			}
		});

		setupValidators();
		enablePickupView();
		ButtonAnimator.addButtonAnimations(backBtn, nextBtn);
	}

	private void setupValidators()
	{
		List<Validator> pickupValidators = Arrays.asList(new Validator[]
		{ new ChoiceBoxValidator<Branch>(branchSelection, null, true, this, "branch"),
				new DateValidator(datePicker, false, null, true, "You must choose a date and it can't be in the past",
						this),
				new TimeValidator(pickupTimePicker, false, null, true, "You must choose a time", this) });

		List<Validator> deliveryValidators = Arrays.asList(new Validator[]
		{ new TimeValidator(deliveryTimePicker, false, null, true, "You must choose a time", this),
				new DateValidator(deliveryDatePicker, false, null, true,
						"You must choose a date and it can't be in the past", this),
				new LettersOnlyValidator(nameInput, null, true, this),
				new InputLengthValidator(nameInput, null, true, "Name", this, 1, 20),
				new InputLengthValidator(addressInput, null, true, "Address", this, 3, 50),
				new DigitsOnlyValidator(phoneInput, null, true, this),
				new InputLengthValidator(phoneInput, null, true, "Phone", this, 9, 10) });

		pickupChecker = Arrays.asList(new ValidatorList[]
		{ new ValidatorList(pickupValidators) });
		deliveryChecker = Arrays.asList(new ValidatorList[]
		{ new ValidatorList(deliveryValidators) });
	}

	protected void setupBranchChoiceBoxes()
	{
		branches.setAll(branchesArray);
		branchSelection.setItems(branches);
		citiesSet = branchController.getBranchCities(branchesArray);
		cities.setAll(citiesSet);
		citySelection.setItems(cities);
	}

	private void enablePickupView()
	{
		pickupView.setVisible(true);
		deliveryView.setVisible(false);
		setupFormController(pickupChecker, nextBtn);
	}

	private void enableDeliveryView()
	{
		pickupView.setVisible(false);
		deliveryView.setVisible(true);
		setupFormController(deliveryChecker, nextBtn);

	}
}
