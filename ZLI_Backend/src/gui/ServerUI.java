package gui;

import java.net.URL;
import java.util.ResourceBundle;

import controllers.TaskScheduler;
import database.DatabaseConnection;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import server.ConnectedClient;
import server.ConsoleString;
import server.Server;

public class ServerUI extends Application implements Initializable {
	private Server sv;

	// ? usage ServerUI.consoleTxtList.add(new ConsoleString("e"));
	public static ObservableList<ConsoleString> consoleTxtList = FXCollections.observableArrayList();
	public static ObservableList<ConnectedClient> clients = FXCollections.observableArrayList();

	@FXML
	private Button exitBtn;

	@FXML
	private Button connectBtn;

	@FXML
	private TextField portField;

	@FXML
	private TextField hostField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private TextField userField;

	@FXML
	private Button disconnectBtn;

	@FXML
	private TextArea console;

	@FXML
	private TextField ipField;

	@FXML
	private TextField dbField;

	@FXML
	private TableColumn<ConnectedClient, String> ipColumn;

	@FXML
	private TableColumn<ConnectedClient, String> hostColumn;

	@FXML
	private TableColumn<ConnectedClient, String> statusColumn;

	@FXML
	private TableView<ConnectedClient> clientsTable;

	@FXML
	private TableView<ConsoleString> consoleTable;

	@FXML
	private TableColumn<ConsoleString, String> consoleColumn;

	private void disconnectServer() {
		try {
			DatabaseConnection.getInstance().disconnect();
			sv.closeServer();
		} catch (Exception e) {

		}
	}

	
	/** 
	 * @param primaryStage
	 * @throws Exception
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("/gui/ServerUI.fxml"));

		Scene scene = new Scene(root);
		// scene.getStylesheets().add(getClass().getResource("/gui/ServerUI.css").toExternalForm());
		primaryStage.setTitle("Server");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);

		primaryStage.show();
	}

	
	/** 
	 * @param event
	 */
	@FXML
	public void onExitBtnClick(ActionEvent event) {
		disconnectServer();
		System.exit(0);
	}

	
	/** 
	 * @param event
	 * @throws Exception
	 */
	@FXML
	public void onConnectBtn(ActionEvent event) throws Exception {
		try {
			DatabaseConnection.getInstance().connectToDB(hostField.getText(), dbField.getText(), userField.getText(),
					passwordField.getText());

		} catch (Exception e) {
			throw e;
		}
		try {
			String port = portField.getText();
			sv = Server.runServer(port);

			connectBtn.setDisable(true);
			disconnectBtn.setDisable(false);

			portField.setDisable(true);
			hostField.setDisable(true);
			dbField.setDisable(true);
			userField.setDisable(true);
			passwordField.setDisable(true);

		} catch (Exception e) {
			throw e;
		}
	}

	
	/** 
	 * @param event
	 */
	@FXML
	public void onDisconnectBtn(ActionEvent event) {
		disconnectServer();
		connectBtn.setDisable(false);
		disconnectBtn.setDisable(true);

		portField.setDisable(false);
		hostField.setDisable(false);
		dbField.setDisable(false);
		userField.setDisable(false);
		passwordField.setDisable(false);
	}

	
	/** 
	 * @param location
	 * @param resources
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ipField.setText(Server.getInstance().getHostAddress());

		ipColumn.setCellValueFactory(new PropertyValueFactory<>("ip"));
		hostColumn.setCellValueFactory(new PropertyValueFactory<>("host"));
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
		clientsTable.setItems(clients);

		consoleColumn.setCellValueFactory(new PropertyValueFactory<>("str"));
		consoleTable.setItems(consoleTxtList);
	}

	
	/** 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
		TaskScheduler.scheduleReportGeneration();
	}
}