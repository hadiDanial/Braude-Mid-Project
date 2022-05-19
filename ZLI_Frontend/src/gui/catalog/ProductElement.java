package gui.catalog;

import java.net.URL;
import java.util.ResourceBundle;

import gui.guimanagement.GUIController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class ProductElement extends GUIController
{

    @FXML
    private ImageView imageView;

    @FXML
    private Label nameLabel;

    @FXML
    private Label detailsLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label currencyLabel;

    @FXML
    void onAddToCart(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
	}

}
