package ru.otus.web;

import com.sun.xml.internal.fastinfoset.stax.events.Util;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class HttpClientTest extends Assert {

    @Test
    public void whenPostRequestUsingHttpClient() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8080/JavaEE/jsonb");

        httpPost.setHeader("Content-type", "application/json");

        CloseableHttpResponse response = client.execute(httpPost);
        assertEquals(200, response.getStatusLine().getStatusCode());

        final String content = new BufferedReader(new InputStreamReader(response.getEntity().getContent(),
                StandardCharsets.UTF_8)).lines().collect(Collectors.joining());
        assertFalse(Util.isEmptyString(content));
        client.close();
    }

    @Test
    public void whenPostRequestUsingHttpUrlConnection() throws IOException {
        // create the HttpURLConnection
        URL url = new URL("http://localhost:8080/JavaEE/jsonb");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        // give it 5 seconds to respond
        connection.setReadTimeout(5 * 1000);
        connection.connect();

        // read the output from the server
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),
                StandardCharsets.UTF_8));
        String content = reader.lines().collect(Collectors.joining());
        assertFalse(Util.isEmptyString(content));
        reader.close();
    }
}
