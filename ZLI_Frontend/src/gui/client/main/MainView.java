package gui.client.main;

import java.net.URL;
import java.util.ResourceBundle;

import controllers.UserController;
import gui.guimanagement.ButtonAnimator;
import gui.guimanagement.GUIPages;
import gui.guimanagement.SceneManager;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainView extends Application implements Initializable
{
	@FXML
	private MenuButton userDropDown;

	@FXML
	private Button shoppingCartBtn;

	@FXML
	private Button homeBtn;

	@FXML
	private Button settingsBtn;

	@FXML
	private VBox centerView;

	@FXML
	private HBox header;

	@FXML
	private ScrollPane scrollPane;

	private static AnchorPane root;

	
	/** 
	 * @param event
	 */
	@FXML
	private void onShoppingCartBtn(ActionEvent event)
	{
		if (shoppingCartBtn.isVisible() && !shoppingCartBtn.isDisabled())
		{
			SceneManager.loadNewScene(GUIPages.CART, true);
		}
	}

	
	/** 
	 * @param primaryStage
	 * @throws Exception
	 */
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		SceneManager.initUI(primaryStage);
	}

	
	/** 
	 * @param location
	 * @param resources
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
//		ButtonAnimator.addButtonAnimations("", "fx-background-color: #062C30;", "fx-background-color: #0c545c;",
//				"fx-background-color: #062C30;", settingsBtn, shoppingCartBtn);
	}

	
	/** 
	 * @param args
	 */
	public static void main(String[] args)
	{
		launch(args);
	}

	
	/** 
	 * @param event
	 */
	@FXML
	private void onSettingsBtn(ActionEvent event)
	{
		SceneManager.openSettingsPage();
	}

	
	/** 
	 * @return Pane
	 */
	public Pane getRoot()
	{
		return root;
	}

	
	/** 
	 * @return VBox
	 */
	public VBox getContent()
	{
		return centerView;
	}

	
	/** 
	 * @return HBox
	 */
	public HBox getHeader()
	{
		return header;
	}

	
	/** 
	 * @return MenuButton
	 */
	public MenuButton getUserDropDown()
	{
		return userDropDown;
	}

	
	/** 
	 * @return Button
	 */
	public Button getShoppingCartButton()
	{
		return shoppingCartBtn;
	}

	
	/** 
	 * @return ScrollPane
	 */
	public ScrollPane getScrollPane()
	{
		return scrollPane;
	}

	
	/** 
	 * @return Button
	 */
	public Button getHomeBtn()
	{
		return homeBtn;
	}

	
	/** 
	 * @param event
	 */
	@FXML
	private void onLogOutBtn(ActionEvent event){
		UserController.getInstance().logout();
	}
	
	/** 
	 * @param event
	 */
	@FXML
	private void onHomeBtn(ActionEvent event){
		SceneManager.openHomePage();
	}
}
