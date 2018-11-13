package ru.otus.ws;

import org.apache.log4j.Logger;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;

@ClientEndpoint
public class CustomClientEndpoint {

    private static final Logger logger = Logger.getLogger(CustomClientEndpoint.class.getName());

    private static Object waitLock = new Object();

    @OnMessage
    public void onMessage(String message) {
        //the new USD rate arrives from the websocket server side.
        logger.info("Received msg: " + message);
    }

    public static void wait4TerminateSignal() {
        synchronized(waitLock){
            try {
                waitLock.wait();
            } catch (InterruptedException e) {
            }
        }
    }
}
