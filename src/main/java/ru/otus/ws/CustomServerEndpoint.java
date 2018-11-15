package ru.otus.ws;

import ru.otus.ws.model.Message;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.nio.ByteBuffer;

@ServerEndpoint(value = "/wsserver/{user}", encoders = CustomWSEncoder.class, decoders = CustomWSDecoder.class)
public class CustomServerEndpoint {
    @OnOpen
    public void onOpen(Session session, @PathParam("user") String user) throws IOException {
        // Get session and WebSocket connection
    }
    @OnMessage
    public void onMessage(Session session, Message msg) throws IOException {
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
