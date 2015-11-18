package cn.itcast.user.action.utils;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;

import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;
import cn.itcast.user.bean.User;

/**
 * 	This utility class is to send the email
 * @author Administrator
 *
 */
public class SendEmail {

	/*
	 * 	This part is to instantiate Properties & load the properties file "email_template.properties"
	 */
	private static Properties properties;
	static {
		InputStream tokenURL = SendEmail.class.getClassLoader().getResourceAsStream("email_template.properties");
		properties = new Properties();
		try {
			properties.load(tokenURL);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 	This is a static method to send the email to a user
	 * @param user
	 * @throws Exception
	 */
	public static void sendEmail (User user) throws Exception {
		
		/*	Get the initiation parameters	*/
		String host = properties.getProperty("host");
		String uname = properties.getProperty("uname");
		String pwd = properties.getProperty("pwd");
		String sender = properties.getProperty("sender");
		String receiver = user.getEmail();
		String subject = properties.getProperty("subject");
		String content = properties.getProperty("content");
		content = MessageFormat.format(content, user.getActivationuuid());	// use format() to replace the placeholder {0}
		
		/*	Send email	*/
		try {
			Session session = MailUtils.createSessionUsingSSL(host, "465", uname, pwd);
			Mail mail = new Mail(sender, receiver, subject, content);
			MailUtils.send(session, mail);
		} catch (MessagingException e) {
		/*	Can invoke a method to process re-sending email if sending email fails	*/
		}
	}
}
