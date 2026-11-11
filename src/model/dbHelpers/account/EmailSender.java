package model.dbHelpers.account;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {

	public int sendemail(String send_to, String msg_subject, String msg_body)
	{
		int success = 1;
		java.util.Properties props = new java.util.Properties();
		//props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.host", "email-smtp.eu-west-1.amazonaws.com");		
		props.put("mail.smtp.socketFactory.port", "465");  
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); 		
		props.put("mail.smtp.auth", "true");  
		props.put("mail.smtp.port", "465");  
		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {  
					protected PasswordAuthentication getPasswordAuthentication() {  
						//return new PasswordAuthentication("eduhappylms@gmail.com","eduhappy_Live_demo");
						return new PasswordAuthentication("AKIA2JHFBDXIMBBDUUO6","BDbKxla0MhfyExU++7vS64V/P38FKxl7Q6PFXbnT+qaX");
					}
			});
		
		
		// Construct the message
		String to = send_to;
		String from = "info@edu-happy.com";
		String subject = msg_subject;
		try {
			
			Message msg = new MimeMessage(session);
			String html = msg_body; //"\n<a href='"+base+"/Activate_Account?Activation_Key="+Activation_Key+"'>Click Here to activate your account</a>";
			msg.setFrom(new InternetAddress(from));
		    msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
		    msg.setSubject(subject);
		    msg.setContent(html, "text/html; charset=utf-8");
		    // Send the message.
		    Transport.send(msg);
		} catch (MessagingException e) {
		    // Error.
			success = 0;
			 e.printStackTrace();
		}
		return success;
	}

}

