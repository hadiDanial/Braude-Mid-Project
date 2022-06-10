package gui.reports.sales;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import controllers.ReportController;
import entities.other.Report;
import gui.guimanagement.GUIController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import utility.IResponse;

public class SalesReportPageCEO extends GUIController
{

	private ObservableList<Report> reportsList = FXCollections.observableArrayList();

	@FXML
	private ChoiceBox quarterDropDown;

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
	private BarChart<String, Number> salesChart;

	/**
	 * @param event
	 */
	@FXML
	void onBackBtn(ActionEvent event)
	{

	}

	/**
	 * @param event
	 */
	@FXML
	void onSetQuarterBtn(ActionEvent event)
	{

	}

	/**
	 * @param event
	 */
	@FXML
	void onViewComplaintsReportsBtn(ActionEvent event)
	{

	}

	/**
	 * @param event
	 */
	@FXML
	void onViewOrdersReportsBtn(ActionEvent event)
	{

	}

	/**
	 * @param event
	 */
	@FXML
	void onViewSalesReportsBtn(ActionEvent event)
	{

	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{

		ReportController.getInstance().getAllReports(new IResponse<ArrayList<Report>>()
		{

			private ArrayList<Report> reports;

			@Override
			public void executeAfterResponse(Object message)
			{
				this.reports = (ArrayList<Report>) message;
//				for (Report report : reportsList)
//				{
					XYChart.Series<String, Number> series = new XYChart.Series<>();
					series.setName("Sales");
					HashMap<String, Number> map = reports.get(0).getDataMap();
					for (Map.Entry<String, Number> entry : map.entrySet())
					{
						String key = entry.getKey();
						Number val = entry.getValue();
						series.getData().add(new XYChart.Data<>(key, val));
					}
					salesChart.getData().addAll(series);

//				}
			}
		});

	}

}
