package gui.orders;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ResourceBundle;

import controllers.OrderController;
import entities.users.Order;
import enums.ColorEnum;
import gui.guimanagement.GUIController;
import gui.guimanagement.SceneManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import utility.IResponse;

public class UpdateOrder extends GUIController
{
	private OrderController orderController;
	private Order order;

	@FXML
	private Button updateBtn;

	@FXML
	private DatePicker datePicker;

	@FXML
	private Button cancelBtn;

	@FXML
	private ChoiceBox<ColorEnum> colorList;

	@FXML
	private TextArea orderDetails;

	@FXML
	private Spinner<Integer> minuteSpinner;

	@FXML
	private Spinner<Integer> hourSpinner;

	protected boolean waitingForResponse;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		orderController = OrderController.getInstance();
		SpinnerValueFactory<Integer> hourValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 16);
		SpinnerValueFactory<Integer> minValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 30);
		hourSpinner.setValueFactory(hourValueFactory);
		minuteSpinner.setValueFactory(minValueFactory);
		hourSpinner.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue)
			{
				hourSpinner.increment(0);
			}
		});
		minuteSpinner.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue)
			{
				minuteSpinner.increment(0);
			}
		});
	}

	public void setOrderToUpdate(Order order)
	{
		this.order = order;
		setOrderDetailsText();
		colorList.getItems().addAll(ColorEnum.values());
		Instant date = order.getDeliveryDate();
		LocalDate localDate = LocalDateTime.ofInstant(order.getDeliveryDate(), ZoneOffset.UTC).toLocalDate();
		LocalDateTime ldt = LocalDateTime.ofInstant(date, ZoneId.systemDefault());
		datePicker.setValue(localDate);
//		colorList.setValue(order.getColor());
		hourSpinner.getValueFactory().setValue(ldt.getHour());
		minuteSpinner.getValueFactory().setValue(ldt.getMinute());
	}

	private void setOrderDetailsText()
	{
		orderDetails.clear();
		orderDetails.appendText("Order #" + order.getOrderId() + ":\n");
		orderDetails.appendText("Ordered on " + order.getFormattedOrderDate() + "\n");
		orderDetails.appendText("From Branch: " + order.getBranchName() + ".\n");
		orderDetails.appendText("For delivery on " + order.getFormattedDeliveryDate() + ".\n");
//		orderDetails.appendText("Order color is " + order.getColor().name() + ", details:\n");
		orderDetails.appendText(order.getOrderDetails() + "\n");
	}

	@FXML
	void checkValidDate(ActionEvent event)
	{
		if (datePicker.getValue().isBefore(LocalDate.now()))
		{
			datePicker.setValue(LocalDate.now());
		}
	}

	private void closeSceneAndOpenOrdersTable(boolean update)
	{
		OrdersPage ordersPage = (OrdersPage) SceneManager.loadPreviousPage();
		if(update)
		{
			ordersPage.updateTableItems();
		}
	}

	@FXML
	void onUpdateBtnClicked(ActionEvent event)
	{
//		order.setColor(colorList.getValue());
		LocalDate date = datePicker.getValue();

		int hour = hourSpinner.getValue();
		int min = minuteSpinner.getValue();
		Instant instant = date.atStartOfDay(ZoneId.systemDefault()).toInstant().plusSeconds(hour * 60 * 60 + min * 60);
		order.setDeliveryDate(instant);
		waitingForResponse = true;
		orderController.updateOrder(new IResponse<Boolean>()
		{
			@Override
			public void executeAfterResponse(Object message)
			{
				boolean result = (Boolean) message;
				orderDetails.setText(result ? "Order updated successfully." : "Failed to update!");
				Platform.runLater(new Runnable()
				{
					
					@Override
					public void run()
					{
						closeSceneAndOpenOrdersTable(true);
					}
				});
			}
		}, order);
	}

	@FXML
	void onCancelBtnClicked(ActionEvent event)
	{
		closeSceneAndOpenOrdersTable(false);
	}
}