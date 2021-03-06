package gui.guimanagement;


public enum GUIPages
{
	// TODO: Add all pages
	MAIN_CONTAINER("client/main/MainView.fxml", "Zerli G13"),
	ERROR("client/error/ErrorPage.fxml", "Error"),
	SETTINGS("client/settings/Settings.fxml", "Settings"),
	OPERATION_SUCCESSFUL("client/success/SuccessPage.fxml", "Success"),
	LOGIN("client/login/LoginPage.fxml", "Login"),
	STARTUP_PAGE("orders/OrdersPage.fxml", "Login (CHANGE TO LOGIN PAGE WHEN IT'S READY!"), 
	NEW_USER("users/customers/NewUser.fxml","Add new user"),
	CUSTOMERS_LIST("users/customers/CustomersList.fxml","Customers List"),
	
	ORDERS("orders/OrdersPage.fxml", "Orders"),
	UPDATE_ORDER("orders/OrderUpdatePage.fxml", "Update Order"), 
	CUSTOMER_ORDERS("orders/customerOrders/CustomerOrdersList.fxml", "My Orders"),
	LOADING("client/main/LoadingPage.fxml", "LOADING"),
	CATALOG_PAGE("catalog/CatalogPage.fxml", "Catalog"),
	PRODUCT_ELEMENT("catalog/ProductElement.fxml", ""),
	OPEN_ORDERS_LIST("orders/openOrders/OpenOrdersList.fxml", "Pending Orders"),
	ADD_PRODUCT("catalog/ProductEditor.fxml", "Add Product"),
	EDIT_PRODUCT("catalog/ProductEditor.fxml", "Edit Product"),
	CATALOG_MANAGEMENT("catalog/CatalogManagement.fxml", "Catalog Management"),
	
	CART("orders/cart/CartPage.fxml", "Cart"),
	CART_ELEMENT("orders/cart/CartProductElement.fxml", ""),
	CHECKOUT_GREETING("orders/delivery/info/SenderInfoPage.fxml", "Checkout"),
	CHECKOUT_DELIVERY("orders/delivery/DeliveryPage.fxml", "Checkout"),
	CHECKOUT_PAYMENT("orders/payment/PaymentPage.fxml", "Checkout - Payment"),
	CHECKOUT_END("orders/checkoutEnd/CheckOutEnd.fxml", "Sending order..."),
	NewDiscount("discountManagement/NewDiscount.fxml", "Add new discount"),
	DISCOUNT_MANAGEMENT("discountManagement/DiscountManagement.fxml", "Discount Management"),
	NEW_PRODUCT("catalog/ProductEditor.fxml", "Add new product"),
	
	OPENED_COMPLAINTS_PAGE("complaints/OpenedComplaints.fxml", "Opened Complaints"),
	NEW_COMPLAINT_PAGE("complaints/NewComplaint.fxml", "New Complaint"),
	ADD_SURVEY("surveys/csEmployee/AddSurvey.fxml", "Add new Survey"),
	ADD_SURVEY_ANALYSIS("surveys/specialist/AddAnalysis.fxml", "Add survey analysis"),
	VIEW_SURVEY_SPECIALIST("surveys/specialist/SurveyAnalysis.fxml", "Surveys - Specialist"),
	ADD_SURVEY_RESULT("surveys/branchEmployee/AddSurveyResult.fxml", "Add Survey Result"),
	VIEW_SURVEYS_BRANCH_EMPLOYEE("surveys/branchEmployee/SurveysList.fxml", "Surveys"),
	
	DELIVERY_LIST("orders/delivery/deliveryOperator/DeliveryList.fxml", "Delivery List"),
	DELIVERY_OPERATOR_PORTAL("users/loginPortals/DeliveryOperatorPortal.fxml", "Delivery Operator Portal"),

	BRANCH_EMPLOYEE_PORTAL("users/loginPortals/BranchEmployeePortal.fxml", "Home - Branch Employee"),
	BRANCH_MANAGER_PORTAL("users/loginPortals/BranchManagerPortal.fxml", "Home - Branch Manager"),
	CEO_PORTAL("users/loginPortals/CEOPortal.fxml", "Home - CEO"),
	CHAIN_EMPLOYEE_PORTAL("users/loginPortals/ChainEmployeePortal.fxml", "Home - Chain Employee"),
	CS_EMPLOYEE_PORTAL("users/loginPortals/EmployeePortalCS.fxml", "Home - Customer Service Employee"),
	SPECIALIST_EMPLOYEE_PORTAL("users/loginPortals/ServiceSpecialistPortal.fxml", "Home - Service Specialist Employee"),
	
	
	VIEW_REPORTS_COMPLAINTS_COMPARE("reports/complaints/ComplaintsReportComparePage.fxml", "Compare Complaints"),
	VIEW_REPORTS_COMPLAINTS_COMPARE_CEO("reports/complaints/ComplaintsReportComparePageCEO.fxml", "Compare Complaints"),
	VIEW_REPORTS_COMPLAINTS("reports/complaints/ComplaintsReportPage.fxml", "View Complaints"),
	VIEW_REPORTS_COMPLAINTS_CEO("reports/complaints/ComplaintsReportPageCEO.fxml", "View Complaints"),

	VIEW_REPORTS_ORDERS_COMPARE("reports/orders/OrdersReportComparePage.fxml", "Compare Orders"),
	VIEW_REPORTS_ORDERS_COMPARE_CEO("reports/orders/OrdersReportComparePageCEO.fxml", "Compare Orders"),
	VIEW_REPORTS_ORDERS("reports/orders/OrdersReportPage.fxml", "View Orders"),
	VIEW_REPORTS_ORDERS_CEO("reports/orders/OrdersReportPageCEO.fxml", "View Orders"),
	
	VIEW_REPORTS_SALES_COMPARE("reports/sales/SalesReportComparePage.fxml", "Compare Sales"),
	VIEW_REPORTS_SALES_COMPARE_CEO("reports/sales/SalesReportComparePageCEO.fxml", "Compare Sales"),
	VIEW_REPORTS_SALES("reports/sales/SalesReportPage.fxml", "View Sales"),
	VIEW_REPORTS_SALES_CEO("reports/sales/SalesReportPageCEO.fxml", "View Sales")
	
	
	
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
