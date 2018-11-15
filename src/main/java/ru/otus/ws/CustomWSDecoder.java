package ru.otus.ws;

import com.google.gson.Gson;
import ru.otus.ws.model.Message;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class CustomWSDecoder implements Decoder.Text<Message> {
    @Override
    public Message decode(String s) throws DecodeException {
        return new Gson().fromJson(s, Message.class);
    }

    @Override
    public boolean willDecode(String s) {
        return false;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
