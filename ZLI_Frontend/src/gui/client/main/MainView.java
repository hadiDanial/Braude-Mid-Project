package gui.client.main;

import java.net.URL;
import java.util.ResourceBundle;

import controllers.ClientController;
import gui.catalog.CatalogPage;
import gui.guimanagement.GUIPages;
import gui.guimanagement.SceneManager;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainView extends Application implements Initializable {
    @FXML
    private MenuButton userDropDown;

    @FXML
    private Button shoppingCartBtn;

    @FXML
    private Button settingsBtn;

    @FXML
    private VBox centerView;
    
	@FXML
    private HBox header;
    
    @FXML
    private ScrollPane scrollPane;
    
	private static AnchorPane root;

    @FXML
    private void onShoppingCartBtn(ActionEvent event) {
        if (shoppingCartBtn.isVisible() && !shoppingCartBtn.isDisabled()) {
            SceneManager.loadNewScene(GUIPages.Cart, true);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
		SceneManager.initUI(primaryStage);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public static void main(String[] args) {
        launch(args);
    }

    @FXML
    private void onSettingsBtn(ActionEvent event) {
    	SceneManager.openSettingsPage();
    }

	public Pane getRoot()
	{
		return root;
	}

	public VBox getContent()
	{
		return centerView;
	}

	public HBox getHeader()
	{
		return header;
	}

	public MenuButton getUserDropDown()
	{
		return userDropDown;
	}

	public Button getShoppingCartButton()
	{
		return shoppingCartBtn;
	}

	public ScrollPane getScrollPane()
	{
		return scrollPane;
	}
}
