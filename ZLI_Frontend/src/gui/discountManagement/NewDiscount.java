package gui.discountManagement;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;

import controllers.DiscountController;
import controllers.ProductController;
import entities.discounts.Discount;
import entities.products.BaseProduct;
import entities.products.CatalogItem;
import gui.guimanagement.ButtonAnimator;
import gui.guimanagement.FormController;
import gui.guimanagement.SceneManager;
import gui.guimanagement.forms.DateValidator;
import gui.guimanagement.forms.DigitsOnlyValidator;
import gui.guimanagement.forms.EmptyValidator;
import gui.guimanagement.forms.InputLengthValidator;
import gui.guimanagement.forms.TimeValidator;
import gui.guimanagement.forms.Validator;
import gui.guimanagement.forms.ValidatorList;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import utility.IResponse;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class NewDiscount extends FormController {

    @FXML
    private TextField nameField;

    @FXML
    private JFXTimePicker startTimePicker;

    @FXML
    private JFXDatePicker startDatePicker;

    @FXML
    private JFXTimePicker endTimePicker;

    @FXML
    private JFXDatePicker endDatePicker;

    @FXML
    private TextField discountAmountField;

    @FXML
    private TextField productToAdd;

    @FXML
    private TableView<CatalogItem> productsTable;

    @FXML
    private TableColumn<BaseProduct, Integer> idColumn;

    @FXML
    private TableColumn<BaseProduct, String> nameColumn;

    @FXML
    private TableColumn<BaseProduct, String> priceColumn;

    @FXML
    private TableColumn<CatalogItem, CatalogItem> removeBtnColumn;

    @FXML
    private Label nameErrorLabel;

    @FXML
    private Label startTimeErrorLabel;

    @FXML
    private Label startDateErrorLabel;

    @FXML
    private Label endTimeErrorLabel;

    @FXML
    private Label endDateErrorLabel;

    @FXML
    private Label discountAmountErrorLabel;

    @FXML
    private Label discountAmountErrorLabel1;

    @FXML
    private Label productsToAddErrorLabel;

    @FXML
    private Button addBtn;

    @FXML
    private Button backBtn;

    @FXML
    private Button addProductBtn;

    private Discount discount;

    private ObservableList<CatalogItem> productsList = FXCollections.observableArrayList();

    private ArrayList<CatalogItem> products;

    @FXML
    void onAddBtn(ActionEvent event) {
        LocalDateTime startDateTime, endDateTime;
        discount.setDiscountName(nameField.getText());
        discount.setDiscountValue(Float.parseFloat(discountAmountField.getText()));

        startDateTime = LocalDateTime.of(startDatePicker.getValue(), startTimePicker.getValue());
        endDateTime = LocalDateTime.of(endDatePicker.getValue(), endTimePicker.getValue());
        discount.setDiscountStartDate(startDateTime.toInstant(ZoneOffset.UTC));
        discount.setDiscountEndDate(endDateTime.toInstant(ZoneOffset.UTC));

        DiscountController.getInstance().createDiscount(discount, new IResponse<Boolean>() {
            @Override
            public void executeAfterResponse(Object message) {
                if ((boolean) message) {
                    stage.close();
                } else {
                    SceneManager.displayErrorMessage("Failed to add new discount");
                }
            }
        });
    }

    @FXML
    void onAddProductBtn(ActionEvent event) {
        if (!products.isEmpty()) {
            for (CatalogItem product : products) {
                if (product.getBaseProduct().getProductId() == Integer.parseInt(productToAdd.getText())) {
                    discount.addProduct(product);
                    productsList.setAll(discount.getProducts());
                }
            }
        }
    }

    private void addProductToColumn() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        removeBtnColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<CatalogItem>(param.getValue()));
        removeBtnColumn.setCellFactory(param -> new TableCell<CatalogItem, CatalogItem>() {
            private final Button removeButton = new Button("Remove");

            @Override
            protected void updateItem(CatalogItem item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(removeButton);
                removeButton.setOnAction(event -> {
                    discount.removeProduct(item);
                    productsList.setAll(discount.getProducts());
                });
            }

        });
    }

    @FXML
    void onBackBtn(ActionEvent event) {
        SceneManager.loadPreviousPage();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeValidation();
        if (products == null) {
            ProductController.getInstance().getAllCatalogItems(new IResponse<ArrayList<CatalogItem>>() {
                @Override
                public void executeAfterResponse(Object message) {
                    if (message != null) {
                        try {
                            products = (ArrayList<CatalogItem>) message;
                        } catch (Exception e) {
                            products = new ArrayList<>();
                        }
                    }
                }
            });
        }

        addProductToColumn();
    }

    private void initializeValidation() {

        List<Validator> nameValidators = Arrays.asList(new Validator[] {
                new InputLengthValidator(nameField, nameErrorLabel, true, "invalid name", this, 2, 50) });
        ValidatorList nameChecker = new ValidatorList(nameValidators);

        List<Validator> startTimeValidators = Arrays.asList(new Validator[] {
                new TimeValidator(startTimePicker, true, startTimeErrorLabel, true, "invalid Time", this) });
        ValidatorList startTimeChecker = new ValidatorList(startTimeValidators);
        List<Validator> startDateValidators = Arrays.asList(new Validator[] {
                new DateValidator(startDatePicker, true, startDateErrorLabel, true, "invalid Date", this) });
        ValidatorList startDateChecker = new ValidatorList(startDateValidators);
        List<Validator> endTimeValidators = Arrays.asList(new Validator[] {
                new TimeValidator(endTimePicker, true, endTimeErrorLabel, true, "invalid Time", this) });
        ValidatorList endTimeChecker = new ValidatorList(endTimeValidators);
        List<Validator> endDateValidators = Arrays.asList(new Validator[] {
                new DateValidator(endDatePicker, true, endDateErrorLabel, true, "invalid Date", this) });
        ValidatorList endDateChecker = new ValidatorList(endDateValidators);

        List<Validator> discountValidator = Arrays.asList(new Validator[] {
                new DigitsOnlyValidator(discountAmountField, discountAmountErrorLabel, true, this),
                new EmptyValidator(discountAmountField, discountAmountErrorLabel1, true, "Discount Amount", this) });
        ValidatorList discountChecker = new ValidatorList(discountValidator);

        setupFormController(Arrays.asList(new ValidatorList[] { nameChecker, startTimeChecker, startDateChecker,
                endTimeChecker, endDateChecker, discountChecker }), addBtn);

        List<Validator> productIDValidator = Arrays
                .asList(new Validator[] { new DigitsOnlyValidator(productToAdd, productsToAddErrorLabel, true, this),
                        new EmptyValidator(productToAdd, productsToAddErrorLabel, true, "Product ID", this) });
        ValidatorList productIDChecker = new ValidatorList(productIDValidator);
        setupFormController(Arrays.asList(new ValidatorList[] { productIDChecker }), addProductBtn);

        ButtonAnimator.addButtonAnimations(addBtn);
        ButtonAnimator.addButtonAnimations(backBtn);
    }

}