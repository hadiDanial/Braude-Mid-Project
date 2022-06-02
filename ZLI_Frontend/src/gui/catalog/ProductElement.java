package gui.catalog;

import java.net.URL;
import java.util.ResourceBundle;

import controllers.OrderController;
import controllers.ProductController;
import entities.products.BaseProduct;
import entities.products.CatalogItem;
import entities.products.Item;
import entities.products.Product;
import gui.guimanagement.GUIController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import utility.FileManager;

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

	private CatalogItem product;

	@FXML
	void onAddToCart(ActionEvent event)
	{
		OrderController.getInstance().addProductToCart(product);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
	}

	@Override
	public void setData(Object data)
	{
		this.product = (CatalogItem) data;
		BaseProduct baseProduct = product.getBaseProduct();
		nameLabel.setText(baseProduct.getProductName());
		priceLabel.setText(baseProduct.getPrice() + "");
		imageView.setImage(FileManager.bytesToImage(baseProduct.getImage()));
		String details;
		if (baseProduct.isProduct())
		{
			details = ((Product) baseProduct).getProductType().name();
		}
		else {
			Item item = ((Item)baseProduct);
			details = item.getPrimaryColor().name() + " " + item.getItemType().name();
		}
		detailsLabel.setText(details);
	}
}
