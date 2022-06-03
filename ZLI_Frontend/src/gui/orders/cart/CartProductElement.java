package gui.orders.cart;

import java.net.URL;
import java.util.ResourceBundle;

import entities.products.BaseProduct;
import entities.products.CartItem;
import gui.guimanagement.GUIController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import utility.FileManager;

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

    private CartItem cartItem;

	private CartPage cartPage;
    @FXML
    void onDeleteBtn(ActionEvent event) {
    	cartItem.getOrder().removeProduct(cartItem, 1);
    	countNumLabel.setText(cartItem.getQuantity() + "");
    	if(cartItem.getQuantity() <= 0)
    	{
    		cartPage.removeFromCart(this);
    	}
    	else
    	{
    		cartPage.updatePrice();
    	}
    	System.out.println("Remove");
    }

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
	}
	
	@Override
	public void setData(Object data)
	{
		cartItem = (CartItem) data;
		BaseProduct p = cartItem.getCatalogItem().getBaseProduct();
		nameLabel.setText(p.getProductName());
		priceLabel.setText(p.getPrice() + "");
		imageView.setImage(FileManager.bytesToImage(p.getImage()));
		countNumLabel.setText(cartItem.getQuantity() + "");
	}

	public void setCartPage(CartPage cartPage)
	{
		this.cartPage = cartPage;
	}
}
