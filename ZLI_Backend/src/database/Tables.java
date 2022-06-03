package database;

public class Tables
{
	public static final String USERS_TABLE_NAME = "Users";
	public static final String[] usersColumnNames =
	{ "userId", "username", "password", "firstName", "lastName", "emailAddress", "phoneNumber", "role", "status",
			"credit", "isLoggedIn", "lastLoginDate" };

	public static final String ORDERS_TABLE_NAME = "Orders";
	public static final String[] ordersColumnNames =
	{ "userId", "branchId", "orderStatus", "totalCost", "greetingCard", "color", "details", "orderDate",
			"deliveryDate" };

	public static final String ORDERS_PRODUCTS_TABLE_NAME = "Orders_Products";
	public static final String[] productsInOrderColumnNames =
	{ "orderId", "catalogId", "quantity" };

	public static final String DELIVERIES_TABLE_NAME = "Deliveries";
	public static final String[] deliveriesColumnNames =
	{ "orderId", "recipientName", "recipientPhoneNumber", "locationId", "delivered" };

	public static final String ORDERS_DISCOUNTS_TABLE_NAME = "Orders_Discounts";
	
	public static final String ALL_PRODUCTS_TABLE_NAME = "Catalog";
	public static final String[] allProductsColumnNames = 
		{ "catalogId", "productName", "price", "image", "type", "primaryColor", "productOrItem" };
	
	public static final String PRODUCTS_IN_BRANCH_TABLE_NAME = "CatalogItemInBranch";

	public static final String LOCATIONS_TABLE_NAME = "Locations";
	public static final String[] locationColumns =
	{ "locationId", "city", "zipCode", "street", "building", "notes" };
	
	public static final String DISCOUNTS_TABLE_NAME = "Discounts";
	public static final String[] discountColumnNames =
	{ "discountId", "discountStartDate", "discountEndDate", "discountName", "discountValue", "discountType", "details",
			"orderDate", "deliveryDate" };
	public static final String DISCOUNTS_PRODUCTS_TABLE_NAME ="Discounts_Products";
	public static final String[] discountsProductsColumnNames =
	{ "catalogId", "branchId", "discountId" };
	public static final String[] ordersDiscountsColumnNames =
	{ "orderId", "discountId" };

	public static final String BRANCHES_TABLE_NAME = "Branches";
	public static final String[] branchColumnNames =
	{ "branchId", "managerId", "branchName", "locationId" };

	public static final String COMPLAINTS_TABLE_NAME = "Complaints";
	public static final String[] complaintsColumnNames = 
	{ "complaintId", "customerId", "customerServiceEmployeeId", "complaintDetails", "complaintResult", "submissionTime", "wasHandled"};

	public static final String CREDIT_CARD_TABLE_NAME = "Credit_Cards";
	public static final String[] creditCardColumnNames = 
	{ "creditCardId", "customerId", "creditCardNumber", "cvv", "expirationDate", "cardHolderName" };
				
}
