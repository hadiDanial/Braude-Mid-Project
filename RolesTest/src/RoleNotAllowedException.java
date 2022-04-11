
public class RoleNotAllowedException extends Exception
{

	public RoleNotAllowedException(User requester, Role[] allowedRoles)
	{
		super(generateRoleExceptionMessage(requester, allowedRoles));
	}

	private static String generateRoleExceptionMessage(User requester, Role[] allowedRoles)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for (Role role : allowedRoles)
		{
			sb.append(role);
			sb.append(", ");
		}
		sb.delete(sb.length() - 2, sb.length());
		sb.append("}");

		return "User [" + requester.getName() + ", " + requester.getRole() + "] must have one of these roles: "
				+ sb.toString() + ".";
	}

	private static final long serialVersionUID = 1L;
}
