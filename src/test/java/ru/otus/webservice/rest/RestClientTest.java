package ru.otus.webservice.rest;

import org.glassfish.jersey.client.ClientConfig;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.otus.webservice.rest.filter.client.ClientLoggingFilter;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Future;

public class RestClientTest {

    static Client client;

    @BeforeClass
    public static void init() {
        ClientConfig config = new ClientConfig();
        client = ClientBuilder.newClient(config);
        client.register(ClientLoggingFilter.class);
    }

    @Test
    public void testSqrt() throws Exception {
        WebTarget target = client.target(getBaseURI()).path("api").
                path("calculator").
//                path(CalculatorImpl.class).
                path("{operation}").
                queryParam("value", 25).
                resolveTemplate("operation", "sqrt");
        // synchronous fetching data
        final Invocation.Builder invocationBuilder = target.request().accept(MediaType.APPLICATION_JSON);
        double response = invocationBuilder.get(Double.class);
        Response response1 = invocationBuilder.get();
        Assert.assertEquals(5, response, 0);

        // asynchronous
        Future<Double> result = target.request().async().get(new InvocationCallback<Double>() {
            @Override
            public void completed(Double customer) {
                // Do something with the customer object
            }
            @Override
            public void failed(Throwable throwable) {
                // handle the error
            }
        });

        CompletionStage<Double> csf = target.request().rx().get(Double.class);
        csf.thenAccept(System.out::println);

        Invocation i1 = target.request().buildGet();
        Assert.assertEquals(5, response1.readEntity(Double.class), 0);
        Assert.assertEquals(5, result.get(), 0);
        Assert.assertEquals(5, i1.invoke(Double.class), 0);
    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/JavaEE").build();
    }
}
