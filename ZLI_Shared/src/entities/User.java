package entities;

import java.util.*;

import enums.AccountStatus;
import enums.UserRole;

public class User
{
//	ArrayList<Order> PendingOrder;
	private int userId;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String phoneNumber;
	private UserRole role;
	private AccountStatus accountStatus;
	private float credit;
	
	public User()
	{
		super();
	}

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getEmailAddress()
	{
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress)
	{
		this.emailAddress = emailAddress;
	}

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	public UserRole getRole()
	{
		return role;
	}

	public void setRole(UserRole role)
	{
		this.role = role;
	}

	public AccountStatus getAccountStatus()
	{
		return accountStatus;
	}

	public void setAccountStatus(AccountStatus accountStatus)
	{
		this.accountStatus = accountStatus;
	}

	public float getCredit()
	{
		return credit;
	}

	public void setCredit(float credit)
	{
		this.credit = credit;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(userId, username);
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
		return userId == other.userId && Objects.equals(username, other.username);
	}

}
