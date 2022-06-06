package gui.users.customers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controllers.UserController;
import entities.users.User;
import enums.UserRole;
import gui.guimanagement.GUIController;
import gui.guimanagement.GUIPages;
import gui.guimanagement.SceneManager;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import utility.IResponse;

public class CustomersList extends GUIController {


    public static ObservableList<User> usersList = FXCollections.observableArrayList();
    private UserController userController;

    @FXML
    private TableView<User> usersTable;

    @FXML
    private TableColumn<User, Integer> idColumn;

    @FXML
    private TableColumn<User, String> nameColumn;

    @FXML
    private TableColumn<User, UserRole> roleColumn;

    @FXML
    private TableColumn<User, String> lastLoginDateColumn;


    @FXML
    void onAddBtn(ActionEvent event) {
        SceneManager.loadModalWindow(GUIPages.NEW_USER, null);
    }

    @FXML
    void onBackBtn(ActionEvent event) {
        SceneManager.loadPreviousPage();
    }

    
    @Override
    public void initialize(URL location, ResourceBundle resources)
	{
        userController = UserController.getInstance();
        initializeTableColumn();
        usersTable.setItems(usersList);						
		userController.getAllUsersByRole(UserRole.Customer, new IResponse<ArrayList<User>>()
		{
			
			@Override
			public void executeAfterResponse(Object message)
			{
				Platform.runLater(new Runnable()
				{
					
					@Override
					public void run()
					{
						if(message == null)
							SceneManager.displayErrorMessage("Failed to load customers!");
						else
						{
							usersList.setAll((ArrayList<User>) message);
						}
					}
				});
			}
		});
	}

    private void initializeTableColumn()
    {
        idColumn.setCellValueFactory(new PropertyValueFactory<User,Integer >("UserId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<User,String >("Username"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<User,UserRole >("Role"));
        lastLoginDateColumn.setCellValueFactory(new PropertyValueFactory<User,String >("FormattedLastLoginDate"));
    }
}
