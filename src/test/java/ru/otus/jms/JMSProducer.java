package ru.otus.jms;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.jms.*;
import javax.naming.InitialContext;

public class JMSProducer {

    private static TopicConnection topicConn;
    private static TopicSession topicSession;
    private static TopicPublisher topicPublisher;

    @BeforeClass
    public static void testPublishing() throws Exception {
        // get the initial context
        InitialContext ctx = new InitialContext();

        // lookup the topic object
        Topic topic = (Topic) ctx.lookup("jms/Topic");

        // lookup the topic connection factory
        TopicConnectionFactory connFactory = (TopicConnectionFactory) ctx.
                lookup("jms/TopicConnectionFactory");

        // create a topic connection
        topicConn = connFactory.createTopicConnection();

        // create a topic session
        topicSession = topicConn.createTopicSession(false,
                Session.AUTO_ACKNOWLEDGE);

        // create a topic publisher
        topicPublisher = topicSession.createPublisher(topic);
        topicPublisher.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

    }

    @AfterClass
    public static void tearDown() throws Exception {
        // close the topic connection
        topicConn.close();
    }

    @Test
    public void testProducing() throws Exception {
        // create the "Hello World" message
        TextMessage message = topicSession.createTextMessage();
        message.setText("Hello from class runner");
        // publish the messages
        topicPublisher.publish(message);
        // print what we did
        System.out.println("Message published: " + message.getText());
    }
}
