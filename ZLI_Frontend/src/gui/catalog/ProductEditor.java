package gui.catalog;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXToggleButton;

import controllers.ProductController;
import entities.products.BaseProduct;
import entities.products.CatalogItem;
import entities.products.Item;
import entities.products.Product;
import gui.guimanagement.FormController;
import gui.guimanagement.SceneManager;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import utility.FileManager;
import utility.IResponse;

public class ProductEditor extends FormController
{
	public static ObservableList<Item> items = FXCollections.observableArrayList();


    @FXML
    private Label titleLabel;

    @FXML
    private ImageView productImage;

    @FXML
    private JFXToggleButton productItemToggle;

    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private HBox productTypeSelect;

    @FXML
    private MenuButton productTypeDropDown;

    @FXML
    private HBox itemTypeSelect;

    @FXML
    private MenuButton itemTypeDropDown;

    @FXML
    private HBox colorSelect;

    @FXML
    private MenuButton colorDropDown;

    @FXML
    private HBox addItemsPane;

    @FXML
    private MenuButton detailDropDown;

    @FXML
    private TreeTableView<?> detailsTable;

    @FXML
    private TreeTableColumn<?, ?> itemNameColumn;

    @FXML
    private TreeTableColumn<?, ?> quantityColumn;

    @FXML
    private TreeTableColumn<?, ?> deleteColumn;

	
	private boolean isNewProduct = true;
	private boolean isProduct = true;
	private Product product;
	private Item item;
	
	private ProductController productController;

	private CatalogItem catalogItem;

    @FXML
    void onAddBtn(ActionEvent event) {

    }

    @FXML
    void onAddItemBtn(ActionEvent event) {

    }

    @FXML
    void onImageSaveBtn(ActionEvent event) {
//    	FileManager.chooseImage()
    }

    @FXML
    void onProductItemToggle(ActionEvent event) {
    	if(productItemToggle.isSelected())
    	{
//    		Base
    	}
    }

	@FXML
	void onBackBtn(ActionEvent event)
	{
		SceneManager.loadPreviousPage();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		product = new Product();
		item = new Item();
		titleLabel.setText("Add Product");
		colorSelect.managedProperty().bind(colorSelect.visibleProperty());
		itemTypeSelect.managedProperty().bind(itemTypeSelect.visibleProperty());
		productTypeSelect.managedProperty().bind(productTypeSelect.visibleProperty());
		addItemsPane.managedProperty().bind(addItemsPane.visibleProperty());
		
		
		productController = ProductController.getInstance();
		productController.getAllItems(new IResponse<ArrayList<Item>>()
		{

			@Override
			public void executeAfterResponse(Object message)
			{
				if (message == null)
					SceneManager.displayErrorMessage("Failed to load items!");
				else
				{
					items.setAll((ArrayList<Item>) message);
					Platform.runLater(() -> {});
				}
			}

		});
	}
	private void initFields()
	{
		if(catalogItem.getBaseProduct().isProduct())
		{
			productItemToggle.setSelected(true);
			product = (Product) catalogItem.getBaseProduct();
			titleLabel.setText("Edit Product - " + product.getProductName());
		}
		else
		{
			productItemToggle.setSelected(false);
			item = (Item) catalogItem.getBaseProduct();			
			titleLabel.setText("Edit Item - " + item.getProductName());
		}
	}

	public void setIsEditing(CatalogItem catalogItem)
	{
		isNewProduct = false;
		this.catalogItem = catalogItem;
		initFields();
	}
	
	@Override
	public void setData(Object data)
	{
		super.setData(data);
	}
}
