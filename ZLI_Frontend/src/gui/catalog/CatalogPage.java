package gui.catalog;

import java.net.URL;
import java.util.ResourceBundle;

import gui.guimanagement.GUIController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class CatalogPage extends GUIController{
    @FXML
    private VBox productsListView;

    public static ObservableList<String> productsList = FXCollections.observableArrayList();

    public CatalogPage() {
        // super("/gui/catalog/CatalogPage.fxml");
    }

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
	}

}
