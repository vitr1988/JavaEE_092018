package ru.otus.misc.mail;

import org.junit.Test;
import ru.otus.misc.mail.config.MailConfig;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class SimpleEmail {

    @Test
    public void test() {
        final String fromEmail = MailConfig.APP_EMAIL; //requires valid gmail id
        final String password = MailConfig.APP_PASSWORD; // correct password for gmail id
        final String toEmail = MailConfig.RECEIVE_EMAIL; // can be any email id

        System.out.println("TLSEmail Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", MailConfig.HOST_NAME); //SMTP Host
        props.put("mail.smtp.port", MailConfig.TSL_PORT); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);

//        EmailUtil.sendEmail(session, toEmail,"TLSEmail Testing Subject", "TLSEmail Testing Body");
        EmailUtil.sendAttachmentEmail(session, toEmail,"TLSEmail Testing Subject", "TLSEmail Testing Body");
    }
}
