package gui.guimanagement;

public enum GUIPages
{
	// TODO: Add all pages
	LOGIN("client/login/LoginPage.fxml", "Login"),
	MAIN_CONTAINER("client/main/MainView.fxml", "Zerli G13"),
	ERROR("client/error/ErrorPage.fxml", "Error"),
	STARTUP_PAGE("orders/OrdersPage.fxml", "Login (CHANGE TO LOGIN PAGE WHEN IT'S READY!"), 
	SETTINGS("client/SettingsPage.fxml", "Settings"),
	ORDERS("orders/OrdersPage.fxml", "Orders"),
	UPDATE_ORDER("orders/OrderUpdatePage.fxml", "Update Order"), 
	LOADING("client/main/LoadingPage.fxml", "LOADING"),
	CATALOG_PAGE("catalog/CatalogPage.fxml", "Catalog"),
	PRODUCT_ELEMENT("catalog/ProductElement.fxml", ""),
	CART("orders/cart/CartPage.fxml", "Cart"),
	CART_ELEMENT("orders/cart/CartProductElement.fxml", ""),
	CHECKOUT_GREETING("orders/delivery/info/SenderInfoPage.fxml", "Checkout"),
	CHECKOUT_DELIVERY("orders/delivery/DeliveryPage.fxml", "Checkout"); 

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
