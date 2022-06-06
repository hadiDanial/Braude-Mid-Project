package gui.ceo.reports;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

public class SalesReportPageCEO {

    @FXML
    private MenuButton quarterDropDown;

    @FXML
    private Label quarterErrLabel;

    @FXML
    private TextField yearField;

    @FXML
    private Label yearErrLabel;

    @FXML
    private ChoiceBox<?> branchChoice;

    @FXML
    private Label branchErrLabel;

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
