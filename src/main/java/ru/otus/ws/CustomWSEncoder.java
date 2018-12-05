package ru.otus.ws;

import ru.otus.ws.model.Message;

import javax.json.bind.JsonbBuilder;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class CustomWSEncoder implements Encoder.Text<Message> {

    @Override
    public String encode(Message message) throws EncodeException {
        return JsonbBuilder.create().toJson(message);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
