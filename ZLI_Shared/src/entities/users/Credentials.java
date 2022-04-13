package entities.users;

import java.util.Objects;

public class Credentials
{
	private User user;
	private String userLogin;
	private String password;
	private boolean isLoggedIn;
	
	public Credentials(User user, String userLogin, String password, boolean isLoggedIn)
	{
		super();
		this.user = user;
		this.userLogin = userLogin;
		this.password = password;
		this.isLoggedIn = isLoggedIn;
	}
	
	public User getUser()
	{
		return user;
	}
	
	public void setUser(User user)
	{
		this.user = user;
	}
	
	public String getUserLogin()
	{
		return userLogin;
	}
	
	public void setUserLogin(String userLogin)
	{
		this.userLogin = userLogin;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public boolean isLoggedIn()
	{
		return isLoggedIn;
	}
	
	public void setLoggedIn(boolean isLoggedIn)
	{
		this.isLoggedIn = isLoggedIn;
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(user, userLogin);
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
		Credentials other = (Credentials) obj;
		return Objects.equals(user, other.user) && Objects.equals(userLogin, other.userLogin);
	}
}
