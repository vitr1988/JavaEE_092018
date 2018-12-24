package ru.otus.jms.v2;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/consumer")
public class ConsumerServlet extends HttpServlet {

    @Resource(lookup ="jms/QueueConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "jms/Queue")
    private Destination dataQueue;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (JMSContext context = connectionFactory.createContext()) {
            JMSConsumer consumer = context.createConsumer(dataQueue);
            String messageText = consumer.receiveBody(String.class); //synchronous way of reading messages
            resp.getWriter().println(messageText);
        }
    }
}
