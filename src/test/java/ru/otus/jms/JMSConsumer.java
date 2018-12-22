package ru.otus.jms;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.jms.*;
import javax.naming.InitialContext;
import java.util.Objects;

public class JMSConsumer {

    private static TopicConnection topicConn;
    private static TopicSubscriber topicSubscriber;

    @BeforeClass
    public static void setup() throws Exception {
        // get the initial context
        InitialContext ctx = new InitialContext();

        // lookup the topic object
        Topic topic = (Topic) ctx.lookup("jms/Topic");

        // lookup the topic connection factory
        TopicConnectionFactory connFactory = (TopicConnectionFactory) ctx.lookup("jms/TopicConnectionFactory");

        // create a topic connection
        topicConn = connFactory.createTopicConnection();

        // create a topic session
        TopicSession topicSession = topicConn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

        // create a topic subscriber
        topicSubscriber = topicSession.createSubscriber(topic);

        // start the connection
        topicConn.start();
    }

    @Test
    public void testConsuming() throws Exception {
        int index = 1;
        while (true) {
            // receive the message
            TextMessage message = (TextMessage) topicSubscriber.receive();
            if (index == 5) {
                System.out.println("Message received: " + message.getText());
                break;
            } else if (Objects.nonNull(message.getText())) {
                // print the message
                System.out.println("Message received: " + message.getText());
                index++;
            }
            else {
                Thread.sleep(3 * 1000);
            }

//            topicSubscriber.setMessageListener(new MessageListener() {
//                @Override
//                public void onMessage(Message message) {
//                    if (index == 5) {
//                        System.out.println("Message received: " + message.getText());
//                        break;
//                    } else if (Objects.nonNull(message.getText())) {
//                        // print the message
//                        System.out.println("Message received: " + message.getText());
//                        index++;
//                    }
//                    else {
//                        Thread.sleep(3 * 1000);
//                    }
//                }
//            });
        }
    }

    @AfterClass
    public static void tearDown() throws Exception {
        // close the topic connection
        topicConn.close();
    }
}
