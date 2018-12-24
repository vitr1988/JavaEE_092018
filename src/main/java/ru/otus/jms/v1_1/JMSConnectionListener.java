package ru.otus.jms.v1_1;

import javax.annotation.Resource;
import javax.jms.*;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class JMSConnectionListener implements ServletContextListener {
//    mq://localhost:7676
    //получаем ресурсы сервера для отправки сообщений
    @Resource(name = "jms/TopicConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(name = "jms/Topic")
    private Destination destination;

    private Connection connection;

    public void sendString(String enterString) {
        try {
            //создаем подключение
            this.connection = connectionFactory.createConnection();
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(destination);
            TextMessage message = session.createTextMessage();
            //добавим в JMS сообщение собственное свойство в поле сообщения со свойствами
            message.setStringProperty("clientType", "web client");
            message.setIntProperty("age", 18);
            //добавляем payload в сообщение
            message.setText(enterString);
            //отправляем сообщение
            producer.send(message);
            System.out.println("message sent");
            //закрываем соединения
            session.close();

        } catch (JMSException ex) {
            System.err.println("Sending message error");
            ex.printStackTrace();
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sendString("Hello World!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            this.connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}