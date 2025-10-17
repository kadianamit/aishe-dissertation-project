package aishe.gov.in.utility;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
//import com.aishe.pojo.StateMaster;


public class Mail extends HttpServlet {

    private static final long serialVersionUID = -1021519817538724892L;
    HttpServletRequest request = null;

    public static void emailOtpForForgotPassword(String toMailList, String subject, String messageText) {
        try {
            Authenticator auth = new SMTPAuthenticator();
            Properties properties = new Properties();
            properties.put("mail.smtp.host", "164.100.17.96");
            properties.put("mail.smtp.auth", "false");
            properties.put("mail.smtp.port", "25");
            InternetAddress from = new InternetAddress("helpdesk-aishe@nic.in");
            Session session = Session.getDefaultInstance(properties, auth);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(from);
            InternetAddress internetAddressTo;
            String toMailId = toMailList;
            internetAddressTo = new InternetAddress(toMailId);
            message.addRecipient(Message.RecipientType.TO, internetAddressTo);
            message.setSubject(subject);
            message.setText(messageText.toString());
            message.setContent(messageText.toString(), "text/html");
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  javax.mail.MessagingException: Could not connect to SMTP host: 164.100.17.96, port: 25;
//   nested exception is:
// 	java.net.ConnectException: Connection timed out: connect

}
