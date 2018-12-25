package ru.otus.misc.mail;

import org.junit.Test;
import ru.otus.misc.mail.config.MailConfig;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class GmailSSLSender {

    @Test
    public void testSend(){
        // Get properties object
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", MailConfig.HOST_NAME);
        props.put("mail.smtp.socketFactory.port", MailConfig.SSL_PORT);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.port", MailConfig.SSL_PORT);

        props.put("mail.debug", "true");

        // get Session
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MailConfig.APP_EMAIL, MailConfig.APP_PASSWORD);
            }
        });

        // compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(MailConfig.RECEIVE_EMAIL));
            message.setSubject("Subject");
            message.setText("Welcome to otus.ru");
            // send message
            Transport.send(message);
            System.out.println("Message sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
