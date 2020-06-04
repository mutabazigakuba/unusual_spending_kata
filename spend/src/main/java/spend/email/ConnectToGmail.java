package spend.email;

import java.net.PasswordAuthentication;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ConnectToGmail 
{
    final String senderEmail = "gakubar2@gmail.com";
    final String senderPassword = "@Lp135542";  
        
    public void sendEmail(String to, String title, String html) throws MessagingException
    {
        System.out.println("Sending email to " + to);

        Session session = createSession();

        MimeMessage message = new MimeMessage(session);
        prepareEmailMessage(message, to, title, html);

        Transport.send(message);
        System.out.println("Done");
    }

    private void prepareEmailMessage(MimeMessage message, String to, String title, String html) throws MessagingException
    {
        message.setContent(html, "text/html; charset=utf-8");
        message.setFrom(new InternetAddress(senderEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(title);
    }

    private Session createSession() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.1and1.com"); 
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() 
        {
            protected PasswordAuthentication getPasswordAuthentication() 
            {
                return new PasswordAuthentication(senderEmail, senderPassword);
          }
        });
        return session;
    }
}