package gui.catalog;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controllers.ProductController;
import entities.products.CatalogItem;
import gui.guimanagement.GUIController;
import gui.guimanagement.GUIPages;
import gui.guimanagement.SceneManager;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import utility.IResponse;

public class CatalogPage extends GUIController
{
	@FXML
	private VBox productsListView;

	public static ObservableList<CatalogItem> productsList = FXCollections.observableArrayList();
	private ProductController productController;

	private VBox scrollPaneContent;
	
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
						scrollPaneContent = new VBox();
						if(message == null)
							SceneManager.displayErrorMessage("Failed to load products!");
						else
						{
							productsList.setAll((ArrayList<CatalogItem>) message);
							for(CatalogItem item : productsList)
							{
								ProductElement element = (ProductElement)SceneManager.loadAdditiveSceneFromParent(GUIPages.ProductElement, scrollPaneContent);
								element.setData(item);
							}
							
						}
					}
				});
			}
		});
	}

}
