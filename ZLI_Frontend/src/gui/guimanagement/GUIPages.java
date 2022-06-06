package gui.guimanagement;

import gui.discountManagement.DiscountManagement;

public enum GUIPages
{
	// TODO: Add all pages
	LOGIN("client/login/LoginPage.fxml", "Login"),
	MAIN_CONTAINER("client/main/MainView.fxml", "Zerli G13"),
	OPERATION_SUCCESSFUL("client/success/SuccessPage.fxml", "Success"),
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
	CHECKOUT_DELIVERY("orders/delivery/DeliveryPage.fxml", "Checkout"),
	CHECKOUT_PAYMENT("orders/payment/PaymentPage.fxml", "Checkout - Payment"),
	CHECKOUT_END("orders/checkoutEnd/CheckOutEnd.fxml", "Sending order..."),
	NewDiscount("discountManagement/NewDiscount.fxml", "Add new discount"),
	DISCOUNT_MANAGEMENT("discountManagement/DiscountManagement.fxml", "Discount Management"),
	NEW_PRODUCT("catalog/NewProduct.fxml", "Add new product"),
	
	COMPLAINT_OPENED_PAGE("complaints/OpenedComplaints.fxml", "Opened Complaints"),
	NEW_COMPLAINT_PAGE("complaints/NewComplaint.fxml", "New Complaint"),
	ADD_SURVEY("surveys/csEmployee/AddSurvey.fxml", "Add new Survey"),
	ADD_SURVEY_ANALYSIS("surveys/specialist/AddAnalysis.fxml", "Add survey analysis"),
	VIEW_SURVEY_SPECIALIST("surveys/specialist/SurveyAnalysis.fxml", "Surveys - Specialist"),
	ADD_SURVEY_RESULT("surveys/branchEmployee/AddSurveyResult.fxml", "Add Survey Result"),
	VIEW_SURVEYS_BRANCH_EMPLOYEE("surveys/branchEmployee/SurveysList.fxml", "Surveys"),
	
	BRANCH_EMPLOYEE_PORTAL("gui.users.loginPortals.BranchEmployeePortal.fxml", "Home - Branch Employee"),
	CHAIN_EMPLOYEE_PORTAL("gui.users.loginPortals.ChainEmployeePortal.fxml", "Home - Chain Employee"),
	CS_EMPLOYEE_PORTAL("gui.users.loginPortals.EmployeePortalCS.fxml", "Home - Customer Service Employee"),
	SPECIALIST_EMPLOYEE_PORTAL("gui.users.loginPortals.ServiceSpecialistPortal.fxml", "Home - Service Specialist Employee"),
	
	
	
	;

	private final String fxmlFile;
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
