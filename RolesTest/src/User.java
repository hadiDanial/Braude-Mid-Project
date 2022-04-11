import java.util.Objects;

public class User
{
	private String name;
	private Role role;
	

	public Role getRole()
	{
		return role;
	}

	public void setRole(Role role)
	{
		this.role = role;
	}

	public User(String name, Role role)
	{
		super();
		this.name = name;
		this.role = role;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public String toString()
	{
		return "User [name=" + name + ", role=" + role + "]";
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(name, role);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(name, other.name) && role == other.role;
	}
	
}
