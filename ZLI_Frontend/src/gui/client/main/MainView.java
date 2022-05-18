package gui.client.main;

import java.net.URL;
import java.util.ResourceBundle;

import gui.catalog.CatalogPage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainView extends Application implements Initializable {
    @FXML
    private MenuButton userDropDown;

    @FXML
    private Button shoppingCartBtn;

    @FXML
    private Button settingsBtn;

    @FXML
    private StackPane centerView;

    @FXML
    private void onSettingsBtn(ActionEvent event) {

        System.out.println("Settings");
    }

    @FXML
    private void onShoppingCartBtn(ActionEvent event) {
        if (shoppingCartBtn.isVisible() && !shoppingCartBtn.isDisabled()) {
            System.out.println("Shopping cart");
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/main/MainView.fxml"));

        Scene scene = new Scene(root);
        // scene.getStylesheets().add(getClass().getResource("/gui/ServerUI.css").toExternalForm());
        primaryStage.setTitle("Zerli");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        primaryStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // centerView.getChildren().add(new CatalogPage().getRoot());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
