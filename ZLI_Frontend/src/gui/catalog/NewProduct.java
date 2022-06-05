package gui.catalog;

import gui.guimanagement.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.image.ImageView;

public class NewProduct {

    @FXML
    private ImageView productImage;

    @FXML
    private TextField imageURLField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private MenuButton colorDropDown;

    @FXML
    private MenuButton detailDropDown;

    @FXML
    private TreeTableView<?> detailsTable;

    @FXML
    private TreeTableColumn<?, ?> detailsColumn;

    @FXML
    void onAddBtn(ActionEvent event) {
        
    }

    @FXML
    void onAddDetailBtn(ActionEvent event) {

    }

    @FXML
    void onBackBtn(ActionEvent event) {
        SceneManager.loadPreviousPage();
    }

    @FXML
    void onImageSaveBtn(ActionEvent event) {

    }


}
