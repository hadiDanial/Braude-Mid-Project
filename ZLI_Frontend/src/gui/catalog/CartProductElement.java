package gui.catalog;

import java.net.URL;
import java.util.ResourceBundle;

import gui.guimanagement.GUIController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class CartProductElement extends GUIController{

    @FXML
    private Label countNumLabel;

    @FXML
    private Label countXLabel;

    @FXML
    private ImageView imageView;

    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label currencyLabel;

    @FXML
    void onDeleteBtn(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
	}

}
