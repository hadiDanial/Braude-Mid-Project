package entities.users;

import java.io.Serializable;
import java.time.Instant;
import java.util.*;

import enums.AccountStatus;
import enums.UserRole;

public class User implements Serializable
{
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
	private boolean isLoggedIn = false;
	private Instant lastLoginDate;
	private ArrayList<Order> Orders;

	private static final long serialVersionUID = 2507221277433512324L;

	public User()
	{
		super();
		credit = 0;
		accountStatus = AccountStatus.Pending;
	}
	
	public User(String username, String password, String firstName, String lastName, String emailAddress,
			String phoneNumber, UserRole role, AccountStatus accountStatus, float credit)
	{
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
		this.role = role;
		this.accountStatus = accountStatus;
		this.credit = credit;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
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

	public ArrayList<Order> getOrders() {
		return Orders;
	}

	public void setOrders(ArrayList<Order> orders) {
		Orders = orders;
	}
	
	public Instant getLastLoginDate()
	{
		return lastLoginDate;
	}

	public void setLastLoginDate(Instant lastLoginDate)
	{
		this.lastLoginDate = lastLoginDate;
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

	@Override
	public String toString()
	{
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + ", emailAddress=" + emailAddress + ", phoneNumber="
				+ phoneNumber + ", role=" + role + ", accountStatus=" + accountStatus + ", credit=" + credit
				+ ", isLoggedIn=" + isLoggedIn + ", lastLoginDate=" + lastLoginDate + "]";
	}

	public String getFullName()
	{
		return firstName + " " + lastName; 
	}

}
