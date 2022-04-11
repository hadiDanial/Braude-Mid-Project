package entities.users;

import java.time.Instant;

public class CreditCardDetails
{
	private int creditCardId;
	private User customer;
	private int creditCardNumber;
	private Instant expirationDate;
	private String cardHolderName;
	public CreditCardDetails(User customer, int creditCardNumber, Instant expirationDate, String cardHolderName)
	{
		super();
		this.customer = customer;
		this.creditCardNumber = creditCardNumber;
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
	public int getCreditCardNumber()
	{
		return creditCardNumber;
	}
	public void setCreditCardNumber(int creditCardNumber)
	{
		this.creditCardNumber = creditCardNumber;
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