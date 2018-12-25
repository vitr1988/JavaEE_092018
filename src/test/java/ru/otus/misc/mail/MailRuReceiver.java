package ru.otus.misc.mail;

import org.junit.Test;

import javax.mail.*;
import java.util.Properties;

public class MailRuReceiver {

    @Test
    public void test() throws Exception {
        final String user = "ii5557261@mail.ru"; // имя пользователя
        final String pass = "Test5557";    // пароль
        final String host = "imap.mail.ru";     // адрес почтового сервера

        // Создание свойств
        Properties props = new Properties();

        //включение debug-режима
        props.put("mail.debug", "true");

        //Указываем протокол - IMAP с SSL
        props.put("mail.store.protocol", "imaps");
        Session session = Session.getInstance(props);
        Store store = session.getStore();

        //подключаемся к почтовому серверу
        store.connect(host, user, pass);

        //получаем папку с входящими сообщениями
        Folder inbox = store.getFolder("INBOX");

        //открываем её только для чтения
//        inbox.open(Folder.READ_WRITE);
        inbox.open(Folder.READ_ONLY);

        //получаем последнее сообщение (самое старое будет под номером 1)
        Message m = inbox.getMessage(inbox.getMessageCount());
        Object content = m.getContent();
        if (content instanceof Multipart) {
            Multipart mp = (Multipart) content;
            BodyPart bp = mp.getBodyPart(0);
            content = bp.getContent();
            //Выводим содержимое на экран
        }
//        m.setFlag(Flags.Flag.DELETED, true);
        System.out.println(content);
    }
}
