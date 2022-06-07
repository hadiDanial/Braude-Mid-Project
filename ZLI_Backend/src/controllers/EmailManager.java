package controllers;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;

import entities.users.User;

public class EmailManager
{
	private static final String email = "zerli.g13@gmail.com";
	private static final String password = "thlvanlofmpfrxuh";
	
	
	/** 
	 * @param title
	 * @param content
	 * @param recipientEmailAddress
	 */
	public static void sendEmail(String title, String content, String recipientEmailAddress)
	{
		Thread thread = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				send(title, content, recipientEmailAddress);
			}
		});
		thread.run();
	}
	
	/** 
	 * @param title
	 * @param content
	 * @param recipient
	 */
	public static void sendEmail(String title, String content, User recipient)
	{
		Thread thread = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				send(title, content, recipient.getEmailAddress());
			}
		});
		thread.run();
	}
	
	
	/** 
	 * @param title
	 * @param content
	 * @param recipientEmailAddress
	 */
	private static void send(String title, String content, String recipientEmailAddress)
	{
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(properties, new Authenticator()
		{
			@Override
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication(email, password);
		    }
		});

		try
		{
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(email));
			message.setRecipients(RecipientType.TO, InternetAddress.parse(recipientEmailAddress));
			message.setSubject(title);
			
			MimeBodyPart mimeBodyPart = new MimeBodyPart();
			mimeBodyPart.setContent(content, "text/html; charset=utf-8");
			
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(mimeBodyPart);

			message.setContent(multipart);

			Transport.send(message);
		} 
		catch (AddressException e)
		{
			e.printStackTrace();
		} 
		catch (MessagingException e)
		{
			e.printStackTrace();
		}
	}
}
