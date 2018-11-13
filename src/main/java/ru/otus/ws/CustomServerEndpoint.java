package ru.otus.ws;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint("/wsserver")
public class CustomServerEndpoint {
    @OnOpen
    public void onOpen(Session session) throws IOException {
        // Get session and WebSocket connection
    }
    @OnMessage
    public void onMessage(Session session, String msg) throws IOException {
        session.getBasicRemote().sendText("Hello " + msg);
    }


    @OnClose
    public void onClose(Session session) throws IOException {
        // WebSocket connection closes
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
    }
}
