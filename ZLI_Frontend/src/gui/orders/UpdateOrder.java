package gui.orders;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.ResourceBundle;

import controllers.OrderController;
import entities.users.Order;
import enums.ColorEnum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import utility.IResponse;

public class UpdateOrder implements Initializable
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
	private AnchorPane parent;

	@FXML
	void checkValidDate(InputMethodEvent event)
	{

	}

	@FXML
	void onCancelBtnClicked(ActionEvent event)
	{
		closeSceneAndOpenOrdersTable();
	}

	private void closeSceneAndOpenOrdersTable()
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/orders/OrdersPage.fxml"));
		AnchorPane pane;
		try
		{
			pane = loader.load();
			parent.getChildren().clear();
			parent.getChildren().add(pane);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@FXML
	void onUpdateBtnClicked(ActionEvent event)
	{
		order.setColor(colorList.getValue());
		LocalDate date = datePicker.getValue();
		Instant instant = date.atStartOfDay(ZoneId.systemDefault()).toInstant();
		order.setDeliveryDate(instant);
		orderController.updateOrder(order, new IResponse<Boolean>()
		{

			@Override
			public void executeAfterResponse(Object message)
			{
				orderDetails.setText((Boolean)message ? "Order updated successfully." : "Failed to update!");
			}
		});
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		orderController = OrderController.getInstance();
	}

	public void setOrderToUpdate(Order order)
	{
		this.order = order;
		setOrderDetailsText();
		
		LocalDate localDate = LocalDateTime.ofInstant(order.getDeliveryDate(), ZoneOffset.UTC).toLocalDate();
		datePicker.setValue(localDate);
		colorList.getItems().addAll(ColorEnum.values());
		colorList.setValue(order.getColor());
		System.out.println(this.order);
	}

	private void setOrderDetailsText()
	{
		orderDetails.clear();
		orderDetails.appendText("Order #" + order.getOrderId() + ":\n");
		orderDetails.appendText("Ordered on " + order.getFormattedOrderDate() + " from Branch: " + order.getBranchName() + ".\n");
		orderDetails.appendText("For delivery on" + order.getFormattedDeliveryDate() + ".\n");
		orderDetails.appendText("Order color is " + order.getColor().name() + ", details:\n");
		orderDetails.appendText(order.getOrderDetails() + "\n");
	}

	public void setParent(AnchorPane parent)
	{
		this.parent = parent;
	}

}