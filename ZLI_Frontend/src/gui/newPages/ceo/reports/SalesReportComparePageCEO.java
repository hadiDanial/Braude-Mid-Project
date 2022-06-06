package gui.ceo.reports;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

public class SalesReportComparePageCEO {

    @FXML
    private MenuButton firstQuarterDropDown;

    @FXML
    private Label quarterErrLabel;

    @FXML
    private TextField firstYearField;

    @FXML
    private Label yearErrLabel;

    @FXML
    private ChoiceBox<?> branchChoice;

    @FXML
    private Label branchErrLabel1;

    @FXML
    private MenuButton secondQuarterDropDown;

    @FXML
    private Label quarterErrLabel1;

    @FXML
    private TextField secondYearField;

    @FXML
    private Label yearErrLabel1;

    @FXML
    private ChoiceBox<?> branchChoice1;

    @FXML
    private Label branchErrLabel2;

    @FXML
    private BarChart<?, ?> salesChart;

    @FXML
    void onBackBtn(ActionEvent event) {

    }

    @FXML
    void onSetQuarterBtn(ActionEvent event) {

    }

    @FXML
    void onViewComplaintsReportsBtn(ActionEvent event) {

    }

    @FXML
    void onViewOrdersReportsBtn(ActionEvent event) {

    }

    @FXML
    void onViewSalesReportsBtn(ActionEvent event) {

    }

}
