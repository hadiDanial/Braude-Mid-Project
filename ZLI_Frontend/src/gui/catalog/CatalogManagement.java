package gui.catalog;

import java.net.URL;
import java.util.ResourceBundle;
import controllers.ProductController;
import entities.products.CatalogItem;
import gui.guimanagement.GUIPages;
import gui.guimanagement.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import java.util.ArrayList;
import gui.guimanagement.GUIController;
import javafx.application.Platform;
import javafx.scene.layout.VBox;
import utility.IResponse;

public class CatalogManagement extends GUIController {

    public static ObservableList<CatalogItem> productsList = FXCollections.observableArrayList();
	private ProductController productController;
    
    @FXML
    private TreeTableView<?> productsTable;

    @FXML
    private TreeTableColumn<?, ?> imageColumn;

    @FXML
    private TreeTableColumn<?, ?> nameColumn;

    @FXML
    private TreeTableColumn<?, ?> detailsColumn;

    @FXML
    private TreeTableColumn<?, ?> priceColumn;

    @FXML
    private TreeTableColumn<?, ?> editColumn;

    @FXML
    void onAddBtn(ActionEvent event) {
        SceneManager.loadNewScene(GUIPages.NEW_PRODUCT, true);
    }

    @FXML
    void onBackBtn(ActionEvent event) {
        SceneManager.loadPreviousPage();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		productController = ProductController.getInstance();
		productController.getAllProducts(new IResponse<ArrayList<CatalogItem>>()
		{
			
			@Override
			public void executeAfterResponse(Object message)
			{
				Platform.runLater(new Runnable()
				{
					
					@Override
					public void run()
					{
						VBox scrollPaneContent = new VBox();
						if(message == null)
							SceneManager.displayErrorMessage("Failed to load products!");
						else
						{
							productsList.setAll((ArrayList<CatalogItem>) message);
							for(CatalogItem item : productsList)
							{
								ProductElement element = (ProductElement)SceneManager.loadAdditiveSceneFromParent(GUIPages.PRODUCT_ELEMENT, scrollPaneContent);
								element.setData(item);
							}
							
						}
					}
				});
			}
		});
	}

}
