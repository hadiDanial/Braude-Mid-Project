package entities.users;

import java.io.Serializable;
import java.time.Instant;

public class CreditCard implements Serializable
{
	private int creditCardId;
	private User customer;
	private String creditCardNumber;
	private int cvv;
	private Instant expirationDate;
	private String cardHolderName;
	
	private static final long serialVersionUID = -8841530215525127937L;

	public CreditCard(User customer, String creditCardNumber, int cvv, Instant expirationDate, String cardHolderName)
	{
		super();
		this.customer = customer;
		this.creditCardNumber = creditCardNumber;
		this.cvv = cvv;
		this.expirationDate = expirationDate;
		this.cardHolderName = cardHolderName;
	}
	
	/** 
	 * @return int
	 */
	public int getCreditCardId()
	{
		return creditCardId;
	}
	
	/** 
	 * @param creditCardId
	 */
	public void setCreditCardId(int creditCardId)
	{
		this.creditCardId = creditCardId;
	}
	
	/** 
	 * @return User
	 */
	public User getCustomer()
	{
		return customer;
	}
	
	/** 
	 * @param customer
	 */
	public void setCustomer(User customer)
	{
		this.customer = customer;
	}
	
	/** 
	 * @return String
	 */
	public String getCreditCardNumber()
	{
		return creditCardNumber;
	}
	
	/** 
	 * @param creditCardNumber
	 */
	public void setCreditCardNumber(String creditCardNumber)
	{
		this.creditCardNumber = creditCardNumber;
	}
	
	/** 
	 * @return int
	 */
	public int getCvv()
	{
		return cvv;
	}
	
	/** 
	 * @param cvv
	 */
	public void setCvv(int cvv)
	{
		this.cvv = cvv;
	}
	
	/** 
	 * @return Instant
	 */
	public Instant getExpirationDate()
	{
		return expirationDate;
	}
	
	/** 
	 * @param expirationDate
	 */
	public void setExpirationDate(Instant expirationDate)
	{
		this.expirationDate = expirationDate;
	}
	
	/** 
	 * @return String
	 */
	public String getCardHolderName()
	{
		return cardHolderName;
	}
	
	/** 
	 * @param cardHolderName
	 */
	public void setCardHolderName(String cardHolderName)
	{
		this.cardHolderName = cardHolderName;
	}
	
	

}