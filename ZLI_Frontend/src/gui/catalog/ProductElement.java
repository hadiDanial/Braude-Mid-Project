package gui.catalog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class ProductElement {

    public ProductElement() {
        // super("/gui/catalog/ProductElement.fxml");
    }

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

}
