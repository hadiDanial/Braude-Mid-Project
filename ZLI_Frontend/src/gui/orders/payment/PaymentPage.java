package gui.orders.payment;

import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;

import controllers.OrderController;
import controllers.UserController;
import entities.users.CreditCard;
import entities.users.Order;
import gui.guimanagement.FormController;
import gui.guimanagement.GUIPages;
import gui.guimanagement.SceneManager;
import gui.guimanagement.forms.DateValidator;
import gui.guimanagement.forms.DigitsOnlyValidator;
import gui.guimanagement.forms.InputLengthValidator;
import gui.guimanagement.forms.LettersOnlyValidator;
import gui.guimanagement.forms.Validator;
import gui.guimanagement.forms.ValidatorList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import utility.IResponse;

public class PaymentPage extends FormController
{

	@FXML
	private Label totalPriceLabel;

	@FXML
	private Label currencyLabel;

	@FXML
	private TextField cardHolderInput;

	@FXML
	private TextField cardNumberInput;

	@FXML
	private TextField CVVInput;

	@FXML
	private JFXDatePicker expirationDateInput;

	@FXML
	private Button backBtn;

	@FXML
	private Button payBtn;
	
	@FXML private Label nameError1;
	@FXML private Label nameError2;
    @FXML private Label cardError1;
    @FXML private Label cardError2;
    @FXML private Label cvvError1;
    @FXML private Label cvvError2;
    @FXML private Label expirationError;
    
	private OrderController orderController;

	private Order order;
	private CreditCard creditCard;

	@FXML
	void onBackBtn(ActionEvent event)
	{
		SceneManager.loadPreviousPage();
	}

	@FXML
	void onPayBtn(ActionEvent event)
	{
		orderController.sendOrderToServer(new IResponse<Boolean>()
		{
			
			@Override
			public void executeAfterResponse(Object message)
			{
				Boolean successful = (Boolean) message;
				if(successful)
				{
					SceneManager.clearHistory();
					// TODO: GUIPages.OPERATION_SUCCESSFUL, return to home page
//					SceneManager.loadModalWindow(GUIPages.OPERATION_SUCCESSFUL, null);
					SceneManager.openLoadingWindow();
					SceneManager.displayErrorMessage("Success");
				}
				else
				{
					SceneManager.displayErrorMessage("Failed to order...");
				}
			}
		});
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		orderController = OrderController.getInstance();
		order = orderController.getOrder();
		totalPriceLabel.setText(String.valueOf(order.getTotalCost()));
		UserController.getInstance().getUserCreditCard(new IResponse<CreditCard>()
		{

			@Override
			public void executeAfterResponse(Object message)
			{
				creditCard = (CreditCard) message;
				cardHolderInput.setText(creditCard.getCardHolderName());
				cardNumberInput.setText(String.valueOf(creditCard.getCreditCardNumber()));
				CVVInput.setText(String.valueOf(creditCard.getCvv()));
				expirationDateInput.setValue(LocalDate.from(creditCard.getExpirationDate()));
			}
		});

		List<Validator> paymentValidators = Arrays.asList(new Validator[]
		{ new LettersOnlyValidator(cardHolderInput, nameError1, true, this),
				new InputLengthValidator(cardHolderInput, nameError2, true, "Card Holder", this, 3, 50),
				new InputLengthValidator(cardNumberInput, cardError1, true, "Credit Card Number", this, 16, 16),
				new DigitsOnlyValidator(cardNumberInput, cardError2, true, this),
				new InputLengthValidator(CVVInput, cvvError1, true, "CVV", this, 3, 4),
				new DigitsOnlyValidator(CVVInput, cvvError2, true, this), 
				new DateValidator(expirationDateInput, false, expirationError,
						true, "You must choose a date and it can't be in the past", this) });
		List<ValidatorList> paymentChecker = Arrays.asList(new ValidatorList[]
		{ new ValidatorList(paymentValidators) });
		setupFormController(paymentChecker, payBtn);
	}
}
