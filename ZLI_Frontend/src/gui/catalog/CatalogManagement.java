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
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

import gui.guimanagement.ButtonAnimator;
import gui.guimanagement.GUIController;
import javafx.beans.property.ReadOnlyObjectWrapper;
import utility.FileManager;
import utility.IResponse;

public class CatalogManagement extends GUIController
{

	public static ObservableList<CatalogItem> productsList = FXCollections.observableArrayList();
	private ProductController productController;

	@FXML
	private TableView<CatalogItem> productsTable;

	@FXML
	private TableColumn<CatalogItem, CatalogItem> imageColumn;

	@FXML
	private TableColumn<CatalogItem, String> nameColumn;

	@FXML
	private TableColumn<CatalogItem, String> detailsColumn;

	@FXML
	private TableColumn<CatalogItem, Float> priceColumn;

	@FXML
	private TableColumn<CatalogItem, CatalogItem> editColumn;
	@FXML private Button backBtn;
	@FXML private Button addBtn;

	@FXML
	void onAddBtn(ActionEvent event)
	{
		SceneManager.loadModalWindow(GUIPages.NEW_PRODUCT, true);
	}

	@FXML
	void onBackBtn(ActionEvent event)
	{
		SceneManager.loadPreviousPage();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		productController = ProductController.getInstance();
		initializeTableColumns();
		productsTable.setItems(productsList);
		ButtonAnimator.addButtonAnimations(addBtn, backBtn);
		productController.getAllCatalogItems(new IResponse<ArrayList<CatalogItem>>()
		{

			@Override
			public void executeAfterResponse(Object message)
			{
				if (message == null)
					SceneManager.displayErrorMessage("Failed to load products!");
				else
				{
					productsList.setAll((ArrayList<CatalogItem>) message);
				}
			}
		});
	
	}

	private void initializeTableColumns()
	{
		nameColumn.setCellValueFactory(new PropertyValueFactory<CatalogItem, String>("productName"));
		detailsColumn.setCellValueFactory(new PropertyValueFactory<CatalogItem, String>("details"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<CatalogItem, Float>("basePrice"));

		imageColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<CatalogItem>(param.getValue()));
		imageColumn.setCellFactory(param -> new TableCell<CatalogItem, CatalogItem>()
		{
			private final ImageView imageView = new ImageView();

			@Override
			protected void updateItem(CatalogItem item, boolean empty)
			{
				super.updateItem(item, empty);
//				imageView.setViewport(new Rectangle2D(0, 0, 50, 50));
				imageView.setFitHeight(50);
				imageView.setFitWidth(50);
				if (item == null)
				{
					setGraphic(null);
					return;
				}
				Image image = FileManager.bytesToImage(item.getBaseProduct().getImage());
				imageView.setImage(image);
				setGraphic(imageView);
			}
		});

		editColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		editColumn.setCellFactory(param -> new TableCell<CatalogItem, CatalogItem>()
		{
			private final Button editButton = new Button("Edit");

			@Override
			protected void updateItem(CatalogItem item, boolean empty)
			{
				super.updateItem(item, empty);

				if (item == null)
				{
					setGraphic(null);
					return;
				}
				setGraphic(editButton);
				editButton.setOnAction(event -> {
					ProductEditor editor = (ProductEditor) SceneManager.loadModalWindow(GUIPages.EDIT_PRODUCT, item);
					editor.setIsEditing(item);
					// TODO: Refresh table?
				});
			}
		});
	}

}
