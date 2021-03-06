package requests;

import entities.users.User;
import enums.UserRole;

public enum RequestType
{
	// Users
	LOGIN(UserRole.values()),
	LOGOUT(UserRole.values()),
	REGISTER(new UserRole[] { UserRole.BranchManager }),
	GET_ALL_USERS(UserRole.values()),
	GET_ALL_CUSTOMERS(new UserRole[] {UserRole.BranchManager}),
	GET_ALL_USERS_BY_ROLE(new UserRole[] {UserRole.ChainEmployee, UserRole.CEO, UserRole.BranchManager} ),
	CREATE_NEW_USER(new UserRole[] {UserRole.BranchManager}),
	// Orders
	CREATE_ORDER(new UserRole[] { UserRole.BranchManager, UserRole.Customer }),
	CREATE_DISCOUNT(new UserRole[] { UserRole.ChainEmployee }),
	GET_ORDER(new UserRole[] { UserRole.BranchManager, UserRole.BranchEmployee, UserRole.Customer }),
	GET_ALL_ORDERS(new UserRole[] { UserRole.CEO }),
	GET_PENDING_ORDERS(new UserRole[] { UserRole.BranchManager, UserRole.BranchEmployee }),
	UPDATE_ORDER_STATUS(new UserRole[] { UserRole.BranchManager, UserRole.DeliveryPerson, UserRole.BranchEmployee }),
	UPDATE_ORDER(new UserRole[] { UserRole.BranchManager }),
	CHECK_IF_FIRST_ORDER(new UserRole[] {UserRole.Customer }), 
	GET_ALL_ORDER_STATUS(new UserRole[] { UserRole.BranchManager, UserRole.BranchEmployee }),
	GET_ALL_ORDER_STATUS_AND_BRANCH(new UserRole[] { UserRole.BranchManager, UserRole.BranchEmployee }),
	UPDATES_ORDER_STATUS(UserRole.values()),
	GET_UPDATED_CREDIT(UserRole.values()),
	GET_ALL_USER_ORDERS(new UserRole[] {UserRole.Customer, UserRole.BranchManager, UserRole.CustomerServiceEmployee }),
	// Products/Catalog
	ADD_PRODUCT(new UserRole[] { UserRole.ChainEmployee }),
	UPDATE_PRODUCT(new UserRole[] { UserRole.ChainEmployee }),
	GET_CATALOG(UserRole.values()),
	GET_CATALOG_BY_BRANCH(UserRole.values()),
	GET_ALL_ITEMS(UserRole.values()),
	GET_ALL_DELIVERY_BRANCH(new UserRole[] { UserRole.DeliveryPerson }),
	
	//Survey
	CREATE_SURVEY(new UserRole[]{UserRole.CustomerServiceEmployee}),
	GET_SURVEY_BY_BRANCH(new UserRole[]{UserRole.BranchManager, UserRole.CustomerServiceEmployee, UserRole.CustomerServiceSpecialist }),
	GET_SURVEY_BY_ID(new UserRole[]{UserRole.BranchManager, UserRole.CustomerServiceEmployee, UserRole.CustomerServiceSpecialist }),
	GET_SURVEY_BY_DATE(new UserRole[]{UserRole.BranchManager, UserRole.CustomerServiceEmployee, UserRole.CustomerServiceSpecialist }),
	GET_ALL_SURVEY(new UserRole[]{UserRole.BranchManager, UserRole.CustomerServiceEmployee, UserRole.CustomerServiceSpecialist }),
	GET_ALL_SURVEY_ANALYSYS(new UserRole[]{UserRole.CustomerServiceSpecialist}),
	
	// Discounts
	GET_DISCOUNTS_BY_BRANCH(UserRole.values()),
	GET_ALL_DISCOUNTS(new UserRole[]{ UserRole.BranchManager, UserRole.Customer,UserRole.ChainEmployee }),
	ADD_PRODUCTS_TO_DISCOUNT(new UserRole[] { UserRole.ChainEmployee }),
	REMOVE_PRODUCTS_FROM_DISCOUNT(new UserRole[] { UserRole.ChainEmployee }), 
	
	// Branches
	GET_ALL_BRANCHES(UserRole.values()), 
	GET_USER_CREDIT_CARD(new UserRole[] {UserRole.Customer }),
	GET_WORKER_BRANCH(new UserRole[] { UserRole.BranchEmployee, UserRole.BranchManager, 
			UserRole.CEO, UserRole.ChainEmployee, UserRole.CustomerServiceEmployee, 
			UserRole.CustomerServiceSpecialist, UserRole.DeliveryPerson } ),
	// Complaints
	CREATE_COMPLAINTS(new UserRole[] {UserRole.Customer}),
	GET_ALL_COMPLAINTS(new UserRole[] { UserRole.CustomerServiceEmployee}), GET_ALL_REPORTS(new UserRole[] {UserRole.BranchManager, UserRole.CEO}), 
	
	
	;
	
	private UserRole[] permittedRoles;

	RequestType(UserRole[] permittedRoles)
	{
		this.permittedRoles = permittedRoles;
	}
	
	/**
	 * Is the current user role allowed to perform this function?
	 * 
	 * @param roleToCheck Role of the current user.
	 * @return True if user has permission to perform the function, otherwise false.
	 */
	public boolean isAuthorized(UserRole roleToCheck)
	{
		for (UserRole role : permittedRoles)
		{
			if (roleToCheck == role)
				return true;
		}
		return false;
	}
}
