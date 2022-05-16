package gui.gui.catalog;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class CatalogPage {
    @FXML
    private VBox productsListView;

    public static ObservableList<String> productsList = FXCollections.observableArrayList();

    public CatalogPage() {
        // super("/gui/catalog/CatalogPage.fxml");
    }

}
