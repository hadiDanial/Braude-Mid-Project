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
	Error("client/error/ErrorPage.fxml", "Error"),
	StartupPage("orders/OrdersPage.fxml", "Login (CHANGE TO LOGIN PAGE WHEN IT'S READY!"), 
	Settings("client/SettingsPage.fxml", "Settings"),
	Orders("orders/OrdersPage.fxml", "Orders"),
	UpdateOrder("orders/OrderUpdatePage.fxml", "Update Order"), 
	Loading("client/main/LoadingPage.fxml", "LOADING"),
	CatalogPage("catalog/CatalogPage.fxml", "Catalog"),
	ProductElement("catalog/ProductElement.fxml", ""),
	Cart("orders/cart/CartPage.fxml", "Cart"),
	CartElement("orders/cart/CartProductElement.fxml", ""),
	CheckoutPage_Info("orders/delivery/info/SenderInfoPage.fxml", "Checkout"),
	CheckoutPage_Delivery("orders/delivery/DeliveryPage.fxml", "Checkout"); 

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
