package gui.orders.cart;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controllers.OrderController;
import controllers.ProductController;
import entities.products.CartItem;
import entities.users.Order;
import gui.guimanagement.ButtonAnimator;
import gui.guimanagement.GUIController;
import gui.guimanagement.GUIPages;
import gui.guimanagement.SceneManager;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

public class CartPage extends GUIController
{
	private OrderController orderController;
	
	@FXML
	private Label totalPriceLabel;

	@FXML
	private Label currencyLabel;

	@FXML
	private Button backBtn;

	@FXML
	private Button nextBtn;

	@FXML
	private ScrollPane scrollPane;
	
	private Order order;
	private ObservableList<CartItem> cartList = FXCollections.observableArrayList();
	private ObservableList<CartProductElement> cartProductsList = FXCollections.observableArrayList();
	@FXML
	private TableView<CartProductElement> cartTable;

	
	/** 
	 * @param event
	 */
	@FXML
	void onBackBtn(ActionEvent event)
	{
		SceneManager.loadPreviousPage();
	}

	
	/** 
	 * @param event
	 */
	@FXML
	void onCheckOutBtn(ActionEvent event)
	{
		SceneManager.loadNewScene(GUIPages.CHECKOUT_GREETING, true);
	}

	
	/** 
	 * @param location
	 * @param resources
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		orderController = OrderController.getInstance();
		order = orderController.getOrder();
		cartList.setAll((ArrayList<CartItem>) order.getProducts());
		TableColumn<CartProductElement, CartProductElement> cartColumn = new TableColumn<>("Cart");
		cartColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		cartColumn.setCellFactory(param -> new TableCell<CartProductElement, CartProductElement>()
		{

			@Override
			protected void updateItem(CartProductElement cartItem, boolean empty)
			{
				super.updateItem(cartItem, empty);

				if (cartItem == null)
				{
					setGraphic(null);
					setDisable(false);
					setText("");
					return;
				}
				setGraphic(cartItem.getRoot());
			}
		});
		for (CartItem item : cartList)
		{
			CartProductElement element = (CartProductElement) SceneManager
					.loadAdditiveSceneFromParent(GUIPages.CART_ELEMENT, null);
			element.setData(item);
			element.setCartPage(this);
			Pane p = ((Pane) element.getRoot());
			scrollPane.widthProperty()
					.addListener((observable, oldValue, newValue) -> p.setPrefWidth((double) newValue - 25.0));
			cartProductsList.add(element);
		}
		
		cartTable.setItems(cartProductsList);
		cartTable.setFixedCellSize(150);
		cartTable.prefHeightProperty().bind(cartTable.fixedCellSizeProperty().multiply(Bindings.size(cartTable.getItems())));
		cartTable.minHeightProperty().bind(cartTable.prefHeightProperty());
		cartTable.maxHeightProperty().bind(cartTable.prefHeightProperty());
		scrollPane.widthProperty()
				.addListener((observable, oldValue, newValue) -> cartColumn.setPrefWidth((double) newValue - 7.5));
		SceneManager.addHeightListener((observable, oldValue, newValue) ->
		{
			setScrollPaneHeight();
		});
		
		cartTable.getColumns().add(cartColumn);
		updatePrice();

		setScrollPaneHeight();
		ButtonAnimator.addButtonAnimations(backBtn, nextBtn);
	}

	private void setScrollPaneHeight()
	{
		double v;
		v = Math.min(SceneManager.getStageHeight() * 0.5, 150 * cartProductsList.size());
		scrollPane.setMinViewportHeight(v);
		scrollPane.setPrefViewportHeight(v);
	}

	
	/** 
	 * @param cartProductElement
	 */
	public void removeFromCart(CartProductElement cartProductElement)
	{
		cartProductsList.remove(cartProductElement);
		updatePrice();
		setScrollPaneHeight();
	}

	public void updatePrice()
	{
		float totalPrice = 0;
		for (CartItem item : cartList)
		{
			totalPrice += item.getQuantity() * item.getCatalogItem().getPriceAfterDiscounts();
		}
		totalPriceLabel.setText(totalPrice + "");
	}

}
