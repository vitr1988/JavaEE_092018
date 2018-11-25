package ru.otus.webservice.rest;

import org.glassfish.jersey.client.ClientConfig;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class RestClientTest {

    static Client client;

    @BeforeClass
    public static void init() {
        ClientConfig config = new ClientConfig();
        client = ClientBuilder.newClient(config);
    }

    @Test
    public void testSqrt() {
        WebTarget target = client.target(getBaseURI());
        double response = target.path("api").
                path("calculator").
                path("sqrt").
                path("25").
                request().
                accept(MediaType.TEXT_PLAIN).
                get(Double.class);
        Assert.assertEquals(5, response, 0);
    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/JavaEE").build();
    }
}
