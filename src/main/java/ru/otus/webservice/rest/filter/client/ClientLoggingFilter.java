package ru.otus.webservice.rest.filter.client;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Provider
public class ClientLoggingFilter implements ClientRequestFilter, ClientResponseFilter {
    @Override
    public void filter(ClientRequestContext crc) throws IOException {
        String method = crc.getMethod();
        URI uri = crc.getUri();
        for (Map.Entry<String, List<Object>> e : crc.getHeaders().entrySet()) {
            String key = e.getKey();
            List<Object> value = e.getValue();
            System.out.println("Header name " + key + " with value : " + value.stream().map(Object::toString)
                    .collect(Collectors.joining(", ")));
        }
    }

    @Override
    public void filter(ClientRequestContext crc, ClientResponseContext crc1)
            throws IOException {
        for (Map.Entry<String, List<Object>> e : crc.getHeaders().entrySet()) {
            String key = e.getKey();
            List<Object> value = e.getValue();
            System.out.println("Header name " + key + " with value : " + value.stream().map(Object::toString)
                    .collect(Collectors.joining(", ")));
        }
    }
}
