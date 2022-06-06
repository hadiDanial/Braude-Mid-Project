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
	public int getCreditCardId()
	{
		return creditCardId;
	}
	public void setCreditCardId(int creditCardId)
	{
		this.creditCardId = creditCardId;
	}
	public User getCustomer()
	{
		return customer;
	}
	public void setCustomer(User customer)
	{
		this.customer = customer;
	}
	public String getCreditCardNumber()
	{
		return creditCardNumber;
	}
	public void setCreditCardNumber(String creditCardNumber)
	{
		this.creditCardNumber = creditCardNumber;
	}
	public int getCvv()
	{
		return cvv;
	}
	public void setCvv(int cvv)
	{
		this.cvv = cvv;
	}
	public Instant getExpirationDate()
	{
		return expirationDate;
	}
	public void setExpirationDate(Instant expirationDate)
	{
		this.expirationDate = expirationDate;
	}
	public String getCardHolderName()
	{
		return cardHolderName;
	}
	public void setCardHolderName(String cardHolderName)
	{
		this.cardHolderName = cardHolderName;
	}
	
	

}