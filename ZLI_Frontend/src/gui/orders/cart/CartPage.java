package gui.orders.cart;

import java.net.URL;
import java.util.ResourceBundle;

import gui.guimanagement.GUIController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CartPage extends GUIController
{

    @FXML
    private Label totalPriceLabel;

    @FXML
    private Label currencyLabel;

    @FXML
    private VBox cartItemsList;

    @FXML
    void onBackBtn(ActionEvent event) {

    }

    @FXML
    void onCheckOutBtn(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
	}

}
