package server;

import java.util.ArrayList;

import controllers.BranchController;
import controllers.ComplaintController;
import controllers.DiscountController;
import controllers.OrderController;
import controllers.ProductController;
import controllers.UserController;
import entities.discounts.Discount;
import entities.other.Branch;
import entities.products.BaseProduct;
import entities.users.Order;
import entities.users.User;
import enums.OrderStatus;
import exceptions.UnauthenticatedUserException;
import exceptions.UnauthorizedRoleException;
import requests.Request;
import requests.RequestType;
import requests.EntityRequestWithId;

public class MessageParser
{
	private static UserController userController = UserController.getInstance();
	private static DiscountController discountController = DiscountController.getInstance();
	private static ProductController productController = ProductController.getInstance();
	private static OrderController orderController = OrderController.getInstance();
	private static BranchController branchController = BranchController.getInstance();
	private static ComplaintController complaintController = ComplaintController.getInstance();
	private static SurveyController surveyController = SurveyController.getInstance();
	/**
	 * Handle the request - activate the correct function based on the request type.
	 * 
	 * @param req Request containing a request type, a message, and the user that
	 *            sent the request.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Object handleRequest(Request req)
	{
		RequestType type = req.getRequestType();

		// Check if the user that sent this request is allowed to perform the required
		// action.
		try
		{
			checkPermissions(type, req.getUser());
		} catch (UnauthenticatedUserException e)
		{
			System.out.println(e.getMessage());
			return e;
		} catch (UnauthorizedRoleException e)
		{
			System.out.println(e.getMessage());
			return e;
		}

		switch (type)
		{
			// Users
			case REGISTER:
			{
				return userController.register((User) req.getMessage());
			}
			case LOGIN:
			{
				User loginRequest = (User) req.getMessage();
				return userController.login(loginRequest.getUsername(), loginRequest.getPassword());
			}
			case LOGOUT:
			{
				// Message = User Id
				if((Integer) req.getMessage() != req.getUser().getUserId()) return false;
				return userController.logout((Integer) req.getMessage());
			}
			
			case GET_USER_CREDIT_CARD:
			{
				if (req.getUser().equals(req.getMessage()))
					return userController.getCreditCard((User) req.getMessage());
				else
					return null;
			}
			
			case GET_ALL_CUSTOMERS:
			{
				return userController.getAllUsers();
			}

			// Orders
			case GET_ALL_ORDERS:
			{
				return orderController.getAllOrders();
			}
			case UPDATE_ORDER:
			{
				return orderController.updateOrder((Order) req.getMessage());
			}
			case CHECK_IF_FIRST_ORDER:
			{
				return orderController.getNumberOfUserOrders((Integer) req.getMessage()) == 0;
			}
			case CREATE_ORDER:
			{
				return orderController.createNewOrder((Order) req.getMessage());
			}
			case GET_ALL_ORDER_STATUS:
			{
				return orderController.getOrdersByStatus((OrderStatus) req.getMessage());
			}
			case UPDATES_ORDER_STATUS:
			{
				EntityRequestWithId<OrderStatus> entityRequestWithId = (EntityRequestWithId) req.getMessage();
				return orderController.updateOrderStatus(entityRequestWithId.getEntityId(),entityRequestWithId.getEntity());
			}
			
			
			case GET_ALL_DELIVERY_BRANCH:
			{
				EntityRequestWithId<OrderStatus> entityRequestWithId = (EntityRequestWithId) req.getMessage();
				return orderController.getOrdersByStatusAndBranch(entityRequestWithId.getEntityId(),entityRequestWithId.getEntity());
			}
			
			// Discounts
			case CREATE_DISCOUNT:
			{
				return discountController.createDiscount((Discount) req.getMessage());
			}
			case GET_ALL_DISCOUNTS:
			{
				return discountController.getAllDiscounts();
			}
			case ADD_PRODUCTS_TO_DISCOUNT:
			{
				discountController.addProductsDiscount((Discount) req.getMessage());
			}
			
			// Products
			case ADD_PRODUCT:
			{
				return productController.addProduct((BaseProduct) req.getMessage());
			}
			case UPDATE_PRODUCT:
			{
				return productController.updateBaseProduct((EntityRequestWithId<BaseProduct>) req.getMessage());
			}
			case GET_CATALOG:
			{
				return productController.getAllCatalogItems();
			}
			case GET_ALL_ITEMS:
			{
				return productController.getAllItems();
			}
			// Branches
			case GET_ALL_BRANCHES:
			{
				return branchController.getAllBranches();
			}
			case GET_WORKER_BRANCH:
			{
				return branchController.getWorkerBranch((Integer) req.getMessage());
			}
			
			// Complaints
			case GET_ALL_COMPLAINTS:
			{
				return complaintController.getAllComplaints();
			}

			case GET_ALL_SURVEY:
			{
				return surveyController.getAllSurvey(req.getMessage());
			}
			
			default:
			break;
		}
		return null;
	}

	/**
	 * Check if the user that sent this request is allowed to perform the required
	 * action.
	 * 
	 * @param type Requested action type.
	 * @param user User that sent the request.
	 * @throws UnauthenticatedUserException Thrown when the action was requested
	 *                                      without a user (unless the action is
	 *                                      Login).
	 * @throws UnauthorizedRoleException    Thrown if the user's role isn't allowed
	 *                                      for this particular request type.
	 */
	private static void checkPermissions(RequestType type, User user)
			throws UnauthenticatedUserException, UnauthorizedRoleException
	{
		if (type == RequestType.LOGIN)
			return;
		if (type != RequestType.LOGIN && user == null)
			throw new UnauthenticatedUserException("Cannot use system without being authenticated.");
		if (!type.isAuthorized(user.getRole()))
			throw new UnauthorizedRoleException(
					"User role " + user.getRole() + " cannot perform " + type.name() + " action!");
	}

}