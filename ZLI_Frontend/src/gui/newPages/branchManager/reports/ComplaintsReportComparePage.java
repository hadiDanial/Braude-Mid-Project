package gui.branchManager.reports;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

public class ComplaintsReportComparePage {

    @FXML
    private ChoiceBox firstQuarterDropDown;

    @FXML
    private Label quarterErrLabel;

    @FXML
    private TextField firstYearField;

    @FXML
    private Label yearErrLabel;

    @FXML
    private ChoiceBox secondQuarterDropDown;

    @FXML
    private Label quarterErrLabel1;

    @FXML
    private TextField secondYearField;

    @FXML
    private Label yearErrLabel1;

    @FXML
    private BarChart<?, ?> complaintsChart;

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
