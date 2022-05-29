package requests;

import enums.UserRole;

public enum RequestType
{
	// Users
	Login(UserRole.values()),
	Logout(UserRole.values()),
	Register(new UserRole[] { UserRole.BranchManager }),
	
	// Orders
	CreateOrder(new UserRole[] { UserRole.BranchManager, UserRole.Customer }),
	CreateDiscount(new UserRole[] { UserRole.ChainEmployee }),
	GetOrder(new UserRole[] { UserRole.BranchManager, UserRole.BranchEmployee, UserRole.Customer }),
	GetAllOrders(new UserRole[] { UserRole.CEO }),
	GetPendingOrders(new UserRole[] { UserRole.BranchManager, UserRole.BranchEmployee }),
	UpdateOrderStatus(new UserRole[] { UserRole.BranchManager, UserRole.DeliveryPerson, UserRole.BranchEmployee }),
	UpdateOrder(new UserRole[] { UserRole.BranchManager }),

	// Products/Catalog
	AddProduct(new UserRole[] { UserRole.ChainEmployee }),
	UpdateProduct(new UserRole[] { UserRole.ChainEmployee }),
	GetAllProducts(UserRole.values()),
	GetCatalogByBranch(UserRole.values()),
	GetDiscountsByBranch(UserRole.values()),
	//Survey
	CreateSurvey(new UserRole[]{UserRole.BranchManager}),
	GetSurveyByBranch(new UserRole[]{UserRole.BranchManager}),
	GetSurveyById(new UserRole[]{UserRole.BranchManager}),
	GetSurveyByDate(new UserRole[]{UserRole.BranchManager}),
	
	// Discounts
	GetAllDiscounts(new UserRole[]{ UserRole.BranchManager, UserRole.Customer,UserRole.ChainEmployee }),
	AddProductsDiscount(new UserRole[] { UserRole.ChainEmployee }),
	RemoveProductsDiscount(new UserRole[] { UserRole.ChainEmployee });
	
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
