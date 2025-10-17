package aishe.gov.in.utility;

import javax.mail.PasswordAuthentication;


public  class SMTPAuthenticator extends javax.mail.Authenticator {
	public PasswordAuthentication getPasswordAuthentication(){
	String username = "admin@grabadealnow.com"; // user name  
	String password = "admin@grabadealnow"; // password
	return new PasswordAuthentication(username, password);
	
	}
	
}
