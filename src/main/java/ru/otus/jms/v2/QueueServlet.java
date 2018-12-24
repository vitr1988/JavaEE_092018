package ru.otus.jms.v2;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSPasswordCredential;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/queue")
public class QueueServlet extends HttpServlet {

    @Inject
    @JMSConnectionFactory("jms/QueueConnectionFactory")
//    @JMSPasswordCredential(userName = "admin", password = "admin")
    private JMSContext context;

    @Resource(lookup = "jms/Queue")
    private Destination dataQueue;

    public void sendMessage(String body) {
        context.createProducer().send(dataQueue, body);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        sendMessage("Hello from servlet in the queue");

        response.setContentType("text/html");
        try(PrintWriter pw = response.getWriter()){
            pw.println("OK");
        }
    }
}
