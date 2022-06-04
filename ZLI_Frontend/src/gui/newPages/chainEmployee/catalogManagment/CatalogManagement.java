package gui.newPages.chainEmployee.catalogManagment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;

public class CatalogManagement {

    @FXML
    private TreeTableView<?> productsTable;

    @FXML
    private TreeTableColumn<?, ?> imageColumn;

    @FXML
    private TreeTableColumn<?, ?> nameColumn;

    @FXML
    private TreeTableColumn<?, ?> detailsColumn;

    @FXML
    private TreeTableColumn<?, ?> priceColumn;

    @FXML
    private TreeTableColumn<?, ?> editColumn;

    @FXML
    void onAddBtn(ActionEvent event) {

    }

    @FXML
    void onBackBtn(ActionEvent event) {

    }

}
