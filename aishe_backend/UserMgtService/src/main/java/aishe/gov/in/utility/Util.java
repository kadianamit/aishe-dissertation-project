package aishe.gov.in.utility;


import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


/**
 * This class provides some of the common utility functions
 */
public class Util
{
	public static boolean sendEmail(String emailAddress, String subject, String body) {

		if(emailAddress == null || emailAddress.equals("")) {
			return false;
		}

		try {


			Properties emailProperties = new Properties();

			emailProperties.put("mail.smtp.host", "relay.nic.in");
			emailProperties.put("mail.smtp.auth", "false");
			emailProperties.put("mail.smtp.port", "25");
			emailProperties.put("mail.smtp.user", "noreply-aishe@nic.in");

			Session session = Session.getDefaultInstance(emailProperties);
			Message emailMessage = new MimeMessage(session);

			InternetAddress addressFrom = new InternetAddress(emailProperties.getProperty("mail.smtp.user"));
			emailMessage.setFrom(addressFrom);
			InternetAddress[] addressTo = new InternetAddress[1];
			addressTo[0] = new InternetAddress(emailAddress);
			emailMessage.setRecipients(Message.RecipientType.TO, addressTo);

			InternetAddress[] addressBCC = new InternetAddress[2];
			addressBCC[0] = new InternetAddress("prakash.bhanu@nic.in");
			if(subject.contains("Uploaded"))
			{
			}
			else
			{
				addressBCC[1] = new InternetAddress("prakash.bhanu@nic.in");
			}

			emailMessage.setSubject(subject);

			emailMessage.setContent(body, "text/html");

			emailMessage.saveChanges();

			String toAddressString = "";

			for(InternetAddress toAddress : addressTo) {

				toAddressString = toAddressString + ", " + toAddress.getAddress();
			}
			Transport.send(emailMessage);
		}
		catch (Exception e) {

			e.printStackTrace();

			return false;
		}

		return true;
	}
}