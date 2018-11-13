package ru.otus.ws;

import org.apache.log4j.Logger;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@ServerEndpoint(value = "/ratesrv")
public class RateServerEndpoint {

    private static final Logger logger = Logger.getLogger(RateServerEndpoint.class.getName());

    //queue holds the list of connected clients
    private static Queue<Session> queue = new ConcurrentLinkedQueue<>();

    static {
        //rate publisher thread, generates a new value for USD rate every 2 seconds.
        new Thread(() -> {
            DecimalFormat decimalFormat = new DecimalFormat("#.####");
            while(true) {
                double rate = 2 + Math.random();
                if(queue != null) {
                    sendAll("USD Rate: " + decimalFormat.format(rate));
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @OnMessage
    public void onMessage(Session session, String msg) {
        //provided for completeness, in out scenario clients don't send any msg.
        try {
            logger.info("Received msg " + msg + " from " + session.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnOpen
    public void open(Session session) {
        queue.add(session);
        logger.info("New session opened: " + session.getId());
    }

    @OnError
    public void error(Session session, Throwable t) {
        queue.remove(session);
        logger.error("Error on session " + session.getId());
    }

    @OnClose
    public void closedConnection(Session session) {
        queue.remove(session);
        logger.info("Session closed: " + session.getId());
    }

    private static void sendAll(String msg) {
        try {
            /* Send the new rate to all open WebSocket sessions */
            ArrayList<Session> closedSessions = new ArrayList<>();
            for (Session session : queue) {
                if(!session.isOpen()) {
                    logger.error("Closed session: " + session.getId());
                    closedSessions.add(session);
                }
                else {
                    session.getBasicRemote().sendText(msg);
                }
            }
            queue.removeAll(closedSessions);
            logger.info("Sending " + msg + " to " + queue.size() + " clients");
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}

