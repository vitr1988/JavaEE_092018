package ru.otus.misc.mail.bean;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;

@Named
@RequestScoped
//@Stateless
public class EmailBean {

    @Resource(name = "mail/Gmail")
    private Session mailSession;

    @Resource(name = "mail/MailRu")
    private Session mailRuSession;

    //    @Asynchronous
    public void sendEmail(String to, String subject, String body) throws MessagingException {
        MimeMessage message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress(mailSession.getProperty("mail.from")));

        String[] receivers = to.split(",");
        InternetAddress[] address = new InternetAddress[receivers.length];
        for (int i = 0; i < receivers.length; i++){
            address[i] = new InternetAddress(receivers[i].trim());
        }
        message.setRecipients(Message.RecipientType.TO, address);
        message.setSubject(subject);
        message.setSentDate(new Date());
        message.setText(body);
        Transport.send(message);
    }

    public String readEmail() throws MessagingException, IOException {
        Store store = mailRuSession.getStore();
        store.connect(mailRuSession.getProperty("mail.imap.host"), mailRuSession.getProperty("mail.imap.login"), mailRuSession.getProperty("mail.imap.password"));

        //получаем папку с входящими сообщениями
        Folder inbox = store.getFolder("INBOX");

        //открываем её только для чтения
        inbox.open(Folder.READ_ONLY);

        //получаем последнее сообщение (самое старое будет под номером 1)
        Message m = inbox.getMessage(inbox.getMessageCount());
        Object content = m.getContent();
        if (content instanceof Multipart) {
            Multipart mp = (Multipart) content;
            BodyPart bp = mp.getBodyPart(0);
            return bp.getContent().toString();
        }
        return content.toString();
    }
}

