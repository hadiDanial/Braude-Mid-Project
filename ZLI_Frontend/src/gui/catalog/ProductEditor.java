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
import entities.users.Order;
import enums.ColorEnum;
import enums.ItemType;
import enums.ProductType;
import gui.guimanagement.ButtonAnimator;
import gui.guimanagement.FormController;
import gui.guimanagement.GUIPages;
import gui.guimanagement.SceneManager;
import gui.orders.UpdateOrder;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import utility.FileManager;
import utility.IResponse;

public class ProductEditor extends FormController
{
	private ObservableList<Item> itemsList = FXCollections.observableArrayList();
	private ObservableList<Item> itemsInProductList = FXCollections.observableArrayList();
	private ObservableList<ColorEnum> colors = FXCollections.observableArrayList();
	private ObservableList<ProductType> productTypes = FXCollections.observableArrayList();
	private ObservableList<ItemType> itemTypes = FXCollections.observableArrayList();

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
	private ChoiceBox<ProductType> productTypeDropDown;

	@FXML
	private HBox itemTypeSelect;

	@FXML
	private ChoiceBox<ItemType> itemTypeDropDown;

	@FXML
	private HBox colorSelect;

	@FXML
	private ChoiceBox<ColorEnum> colorDropDown;

	@FXML
	private HBox addItemsPane;

	@FXML
	private ChoiceBox<Item> itemDropDown;

	@FXML
	private TableView<Item> detailsTable;

	@FXML
	private TableColumn<Item, String> itemNameColumn;

	@FXML
	private TableColumn<Item, Item> quantityColumn;

	@FXML
	private TableColumn<Item, Item> deleteColumn;


    @FXML private Button imgBtn;
    @FXML private Button addBtn;
    @FXML private Button backBtn;
    @FXML private Button saveBtn;
    
    
	private boolean isNewProduct = true;
	private boolean isProduct = true;
	private Product product;
	private Item item;
	private BaseProduct activeProduct;
	
	private ProductController productController;

	private CatalogItem catalogItem;

	@FXML
	void onAddBtn(ActionEvent event)
	{
		activeProduct = (isProduct) ? product : item;
		if(isNewProduct)
		{
			productController.createProduct(activeProduct, new IResponse<Boolean>()
			{

				@Override
				public void executeAfterResponse(Object message)
				{
					if((boolean) message)
					{
						catalogItem.setBaseProduct(activeProduct);
						stage.close();
					}
					else
					{
						SceneManager.displayErrorMessage("Failed to add new product/item!");
					}
				}
				
			});
		}
		else
		{
			productController.updateProduct(catalogItem.getBaseProduct().getProductId(), activeProduct, new IResponse<Boolean>()
					{

						@Override
						public void executeAfterResponse(Object message)
						{
							if((boolean) message)
							{
								catalogItem.setBaseProduct(activeProduct);
								stage.close();
							}
							else
							{
								SceneManager.displayErrorMessage("Failed to update product/item!");
							}
						}}
			);
		}
	}

	@FXML
	void onAddItemBtn(ActionEvent event)
	{
		product.addItem(itemDropDown.getValue());
		itemsInProductList.setAll(product.getItems().keySet());
	}

	@FXML
	void onImageSaveBtn(ActionEvent event)
	{
		byte[] img = FileManager.chooseImage();
		product.setImage(img);
		item.setImage(img);
		productImage.setImage(FileManager.bytesToImage(img));
	}

	@FXML
	void onProductItemToggle(ActionEvent event)
	{
		isProduct = productItemToggle.isSelected();
		if(isProduct)
			activeProduct = product;
		else activeProduct = item;
		if(isNewProduct)
		setVisibility();
		// TODO: change validators
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
		detailsTable.setItems(itemsInProductList);
		setManagedProperties();
		setupDropdowns();
		setupTableColumns();

		
		//
		product.addItem(new Item( "A", 5, null, ItemType.Seedling, ColorEnum.Blue));
		product.addItem(new Item( "B", 55, null, ItemType.Branch, ColorEnum.White));
		product.addItem(new Item( "C", 35, null, ItemType.Flower, ColorEnum.Purple));
		itemsInProductList.setAll(product.getItems().keySet());

		//
		onProductItemToggle(null);
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
					itemsList.setAll((ArrayList<Item>) message);
				}
			}

		});
		
		ButtonAnimator.addButtonAnimations(addBtn, backBtn, imgBtn, saveBtn);
	}

	private void setManagedProperties()
	{
		colorSelect.managedProperty().bind(colorSelect.visibleProperty());
		itemTypeSelect.managedProperty().bind(itemTypeSelect.visibleProperty());
		productTypeSelect.managedProperty().bind(productTypeSelect.visibleProperty());
		addItemsPane.managedProperty().bind(addItemsPane.visibleProperty());
	}

	private void setupDropdowns()
	{
		colors.setAll(ColorEnum.values());
		productTypes.setAll(ProductType.values());
		itemTypes.setAll(ItemType.values());

		itemDropDown.setItems(itemsList);
		colorDropDown.setItems(colors);
		productTypeDropDown.setItems(productTypes);
		itemTypeDropDown.setItems(itemTypes);
	}

	private void setupTableColumns()
	{
		itemNameColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("productName"));
		quantityColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<Item>());
		quantityColumn.setCellFactory(param -> new TableCell<Item, Item>()
		{
			@Override
			protected void updateItem(Item item, boolean empty)
			{
				super.updateItem(item, empty);

				if (item == null)
				{
					setGraphic(null);
					return;
				}
				setText(String.valueOf(product.getItemQuantity(item)));
			}
		});
		deleteColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		deleteColumn.setCellFactory(param -> new TableCell<Item, Item>()
		{
			private final Button editButton = new Button("Delete");

			@Override
			protected void updateItem(Item item, boolean empty)
			{
				super.updateItem(item, empty);

				if (item == null)
				{
					setGraphic(null);
					return;
				}
				setGraphic(editButton);
				editButton.setOnAction(event -> {
					product.removeItem(item);
					itemsInProductList.setAll(product.getItems().keySet());
				});
			}
		});
	}

	private void initFieldsToEdit()
	{
		BaseProduct base;
		if (catalogItem.getBaseProduct().isProduct())
		{
			isProduct = true;
			product = (Product) catalogItem.getBaseProduct();
			base = product;
			productItemToggle.setSelected(isProduct);
			setVisibility();
			setTitle();
			itemsInProductList.setAll(product.getItems().keySet());
		} else
		{
			isProduct = false;
			item = (Item) catalogItem.getBaseProduct();
			base = item;
			setVisibility();
			setTitle();
		}
		nameField.setText(base.getProductName());
		priceField.setText(String.valueOf(base.getPrice()));
		productImage.setImage(FileManager.bytesToImage(base.getImage()));
	}

	private void setVisibility()
	{
		productTypeSelect.setVisible(isProduct);
		colorSelect.setVisible(!isProduct);
		itemTypeSelect.setVisible(!isProduct);
		addItemsPane.setVisible(isProduct);
	}

	private void setTitle()
	{
		String title = "";
		if(isNewProduct)
		 title = "Add ";
		else title = "Edit ";
		if(isProduct)
			title += "Product - " + product.getProductName();
		else
			title += "Item - " + item.getProductName();
		titleLabel.setText(title);
	}

	public void setIsEditing(CatalogItem catalogItem)
	{
		isNewProduct = false;
		this.catalogItem = catalogItem;
		initFieldsToEdit();
	}

	@Override
	public void setData(Object data)
	{
		super.setData(data);
	}
}
