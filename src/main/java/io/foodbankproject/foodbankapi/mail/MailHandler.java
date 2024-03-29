package io.foodbankproject.foodbankapi.mail;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

public class MailHandler implements Runnable{
	
	private Properties properties;
	
	private String username;
	private String password;
	
	private String recipient;
	
	private String recipientName;
	
	
	public MailHandler(String recipient, String recipientName) {
		properties = new Properties();
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		this.recipient = recipient;
		this.recipientName = recipientName;
		this.username = System.getenv("AUTOMATION_EMAIL_USERNAME");
		this.password = System.getenv("AUTOMATION_EMAIL_PASSWORD");
	}

	/**
	 * Method for sending mail, currently sends a message to a recipient and allows you to 
	 * declare what is being donated. 
	 * @param recipient
	 * @param recipientName
	 * @param donation
	 * @throws Exception
	 */
	public void sendMail() throws Exception {

		Session session = createAuthenticationSession();
		Message message = prepareMessage(session, username, recipient, recipientName);
		Transport.send(message);
	}
	
	private Session createAuthenticationSession() {
		return Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
	}

	private static Message prepareMessage(Session session, String myEmail, 
			String recipient, String recipientName) {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myEmail)); // set from email

			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

			message.setSubject("Example email");
			
			message.setText("Thank you " + recipientName + " for donating." + "\nWe have received"
					+ " your donation.");

			return message;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@Override
	public void run() {
		try {
			this.sendMail();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
