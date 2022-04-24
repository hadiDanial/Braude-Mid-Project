package gui;

import java.net.URL;
import java.util.ResourceBundle;

import database.DataBase;
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
			sv.closeServer();
		} catch (Exception e) {

		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("/gui/ServerUI.fxml"));

		Scene scene = new Scene(root);
		// scene.getStylesheets().add(getClass().getResource("/gui/ServerUI.css").toExternalForm());
		primaryStage.setTitle("Client");
		primaryStage.setScene(scene);

		primaryStage.show();
	}

	@FXML
	public void onExitBtnClick(ActionEvent event) {
		disconnectServer();
		System.exit(0);
	}

	@FXML
	public void onConnectBtn(ActionEvent event) throws Exception {
		try {
			DataBase.getInstance().connectToDB(hostField.getText(), dbField.getText(), userField.getText(),
					passwordField.getText());

		} catch (Exception e) {
			throw e;
		}
		try {
			String port = portField.getText();
			sv = Server.runServer(port);

			connectBtn.setDisable(true);
			disconnectBtn.setDisable(false);

		} catch (Exception e) {
			throw e;
		}
	}

	@FXML
	public void onDisconnectBtn(ActionEvent event) {
		disconnectServer();
		connectBtn.setDisable(false);
		disconnectBtn.setDisable(true);
	}

	public void disableConsole() {
		try {

			console.appendText("Hello User\n");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ipColumn.setCellValueFactory(new PropertyValueFactory<>("ip"));
		hostColumn.setCellValueFactory(new PropertyValueFactory<>("host"));
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
		clientsTable.setItems(clients);

		consoleColumn.setCellValueFactory(new PropertyValueFactory<>("str"));
		consoleTable.setItems(consoleTxtList);
	}

	public static void main(String[] args) {
		launch(args);
	}
}