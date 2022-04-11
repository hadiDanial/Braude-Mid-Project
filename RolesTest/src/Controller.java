import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Controller
{
	ArrayList<User> users = new ArrayList<User>();

	public ArrayList<User> getAllUsers(User requester) throws RoleNotAllowedException
	{
		checkUserRole(requester, new Role[]{ Role.CEO, Role.Manager });
		return users;
	}

	private void checkUserRole(User requester, Role[] allowedRoles) throws RoleNotAllowedException
	{
		for (Role r : allowedRoles)
		{
			if (requester.getRole() == r)
				return;
		}
		throw new RoleNotAllowedException(requester, allowedRoles);
	}

	public void addUser(User user)
	{
		users.add(user);
	}
}
