package ru.otus.ws;

import org.junit.Assert;
import org.junit.Test;

import javax.websocket.ContainerProvider;
import javax.websocket.MessageHandler;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.net.URI;

public class ClientEndpointTest {

    @Test
    public void testConnection() throws Exception {
        Session session = null;
        try{
            //Tyrus is plugged via ServiceLoader API. See notes above
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            //JavaEE is the context-root of my web.app
            //ratesrv is the path given in the ServerEndPoint annotation on server implementation
            session = container.connectToServer(CustomClientEndpoint.class, URI.create("ws://localhost:8080/JavaEE/ratesrv"));
            Assert.assertTrue(session.isOpen());
        }
        finally{
            if(session != null){
                try {
                    session.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
