package com.carpark.helper;

import java.util.Properties;
import java.util.regex.*;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailer {
	private String to, subj, msg;
	public Mailer(String To, String Subj, String Msg) {
		to = To;
		subj = Subj;
		msg = Msg;
	}
	public void send() {
	      final String from = "carparkingsols@gmail.com";
	      final String username = "carparkingsols";
	      final String password = "1234Car1234";

	      String host = "smtp.gmail.com";

	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "587");
	      props.put("mail.smtp.ssl.protocols", "TLSv1.2");

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	      new javax.mail.Authenticator() {
	         protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	         }
	      });

	      try {
	         // Create a default MimeMessage object.
	         Message message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.setRecipients(Message.RecipientType.TO,
	         InternetAddress.parse(to));

	         // Set Subject: header field
	         message.setSubject(subj);

	         // Now set the actual message
	         message.setText(msg);

	         // Send message
	         Transport.send(message);

	         System.out.println("Sent message successfully....");

	      } catch (MessagingException e) {
	            throw new RuntimeException(e);
	      }
	}
	public static boolean isValid(String email) {
		Matcher m = Pattern.compile("^[a-z0-9.]+@[a-z.-]+\\.\\w{2,3}$").matcher(email);
		return m.find();
	}
}
