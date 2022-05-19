package gui.guimanagement;

import gui.catalog.*;
import gui.client.*;
import gui.complaints.*;
import gui.orders.*;
import gui.reports.*;
import gui.surveys.*;
import gui.users.*;

public enum GUIPages
{
	// TODO: Add all pages
	Login("client/login/LoginPage.fxml", "Login"),
	MainContainer("client/main/MainView.fxml", "Zerli G13"),
	StartupPage("orders/OrdersPage.fxml", "Login (CHANGE TO LOGIN PAGE WHEN IT'S READY!"), 
	Settings("client/SettingsPage.fxml", "Settings"),
	Orders("orders/OrdersPage.fxml", "Orders"),
	UpdateOrder("orders/OrderUpdatePage.fxml", "Update Order"), 
	Loading("client/SettingsPage.fxml", "LOADING"); // TODO: Create loading page

	private final String fxmlFile;
//	private final Class<? extends GUIController> controllerClass;
	private final String pageTitle;

	private static final String packageName = "/gui/";

	GUIPages(String fxmlFile, String pageTitle)
	{
		this.fxmlFile = fxmlFile;
		this.pageTitle = pageTitle;
	}

	public String getFxmlFile()
	{
		return packageName + fxmlFile;
	}
	
	public String getPageTitle()
	{
		return pageTitle;
	}	
}
