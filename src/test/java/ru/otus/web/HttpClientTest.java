package ru.otus.web;

import org.junit.Assert;
import org.junit.Test;
import ru.otus.servlet.ServletTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class HttpClientTest extends Assert {

    @Test
    public void whenPostRequestUsingHttpUrlConnection() throws IOException {
        ServletTest.initTrustedAllSSLContext();
        // create the HttpURLConnection
        URL url = new URL("http://localhost:8080/JavaEE/jsonb");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Content-type", "application/json");
        connection.setRequestMethod("POST");
        // give it 5 seconds to respond
        connection.setReadTimeout(5 * 1000);
        connection.connect();

        // read the output from the server
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),
                StandardCharsets.UTF_8));
        String content = reader.lines().collect(Collectors.joining());
        assertFalse(content == null);
        reader.close();
    }
}
