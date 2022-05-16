package gui.gui.util;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class LocalNode implements Initializable {
    private AnchorPane root;
    private String fxmlPath;

    public LocalNode(String fxmlPath) {
        this.fxmlPath = fxmlPath;
    }

    public AnchorPane getRoot() {
        if (root == null) {
            try {
                root = FXMLLoader.load(getClass().getResource(fxmlPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return root;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
