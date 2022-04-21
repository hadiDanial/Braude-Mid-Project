package gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import server.Server;

public class ServerUI extends Application {
	private Server sv;

	@FXML
	private Button exitBtn;

	@FXML
	private Button connectBtn;

	@FXML
	private TextField portField;

	@FXML
	private TextField hostField;

	@FXML
	private TextField passwordField;

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
	private TableColumn<String, String> ipColumn;

	@FXML
	private TableColumn<String, String> hostColumn;

	@FXML
	private TableColumn<String, String> statusColumn;

	private void disconnectServer() {
		try {
			sv.close();
		} catch (Exception e) {

		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("/gui/ServerUI.fxml"));

		Scene scene = new Scene(root);
		// scene.getStylesheets().add(getClass().getResource("/gui/ServerPort.css").toExternalForm());
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
	public void onConnectBtn(ActionEvent event) {
		String port = portField.getText();
		sv = Server.runServer(port);

		console.appendText("IP: " + sv.getHostAddress() + "\n");

		connectBtn.setDisable(true);
		disconnectBtn.setDisable(false);
	}

	@FXML
	public void onDisconnectBtn(ActionEvent event) {
		disconnectServer();
		console.appendText("Server Disconnected\n");
		connectBtn.setDisable(false);
		disconnectBtn.setDisable(true);
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void updateConsole(String textToAdd) {

	}

}