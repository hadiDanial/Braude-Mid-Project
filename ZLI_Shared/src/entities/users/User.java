package entities.users;

import java.io.Serializable;
import java.time.Instant;
import java.util.*;

import enums.AccountStatus;
import enums.UserRole;
import utility.DateFormatter;

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

	
	/** 
	 * @return boolean
	 */
	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	
	/** 
	 * @param isLoggedIn
	 */
	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	
	/** 
	 * @return int
	 */
	public int getUserId()
	{
		return userId;
	}

	
	/** 
	 * @param userId
	 */
	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	
	/** 
	 * @return String
	 */
	public String getUsername()
	{
		return username;
	}

	
	/** 
	 * @param username
	 */
	public void setUsername(String username)
	{
		this.username = username;
	}

	
	/** 
	 * @return String
	 */
	public String getPassword()
	{
		return password;
	}

	
	/** 
	 * @param password
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}

	
	/** 
	 * @return String
	 */
	public String getFirstName()
	{
		return firstName;
	}

	
	/** 
	 * @param firstName
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	
	/** 
	 * @return String
	 */
	public String getLastName()
	{
		return lastName;
	}

	
	/** 
	 * @param lastName
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	
	/** 
	 * @return String
	 */
	public String getEmailAddress()
	{
		return emailAddress;
	}

	
	/** 
	 * @param emailAddress
	 */
	public void setEmailAddress(String emailAddress)
	{
		this.emailAddress = emailAddress;
	}

	
	/** 
	 * @return String
	 */
	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	
	/** 
	 * @param phoneNumber
	 */
	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	
	/** 
	 * @return UserRole
	 */
	public UserRole getRole()
	{
		return role;
	}

	
	/** 
	 * @param role
	 */
	public void setRole(UserRole role)
	{
		this.role = role;
	}

	
	/** 
	 * @return AccountStatus
	 */
	public AccountStatus getAccountStatus()
	{
		return accountStatus;
	}

	
	/** 
	 * @param accountStatus
	 */
	public void setAccountStatus(AccountStatus accountStatus)
	{
		this.accountStatus = accountStatus;
	}

	
	/** 
	 * @return float
	 */
	public float getCredit()
	{
		return credit;
	}

	
	/** 
	 * @param credit
	 */
	public void setCredit(float credit)
	{
		this.credit = credit;
	}

	
	/** 
	 * @return ArrayList<Order>
	 */
	public ArrayList<Order> getOrders() {
		return Orders;
	}

	
	/** 
	 * @param orders
	 */
	public void setOrders(ArrayList<Order> orders) {
		Orders = orders;
	}
	
	
	/** 
	 * @return String
	 */
	public String getFormattedLastLoginDate()
	{
		return DateFormatter.formatInstant(lastLoginDate, true);
	}

	
	/** 
	 * @return Instant
	 */
	public Instant getLastLoginDate()
	{
		return lastLoginDate;
	}

	
	/** 
	 * @param lastLoginDate
	 */
	public void setLastLoginDate(Instant lastLoginDate)
	{
		this.lastLoginDate = lastLoginDate;
	}

	
	/** 
	 * @return int
	 */
	@Override
	public int hashCode()
	{
		return Objects.hash(userId, username);
	}

	
	/** 
	 * @param obj
	 * @return boolean
	 */
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

	
	/** 
	 * @return String
	 */
	@Override
	public String toString()
	{
		return getFullName() + " (" + username + ")";
	}

	
	/** 
	 * @return String
	 */
	public String getFullName()
	{
		return firstName + " " + lastName; 
	}

}
