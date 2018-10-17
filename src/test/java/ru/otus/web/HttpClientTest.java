package ru.otus.web;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.ssl.SSLContextBuilder;
import org.junit.Assert;
import org.junit.Test;
import ru.otus.servlet.ServletTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Collectors;

public class HttpClientTest extends Assert {

    @Test
    public void whenPostRequestUsingHttpClient() throws IOException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        SSLContextBuilder builder = new SSLContextBuilder();
        builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());
        CloseableHttpClient client = HttpClients.custom().setSSLSocketFactory(
                sslsf).setRedirectStrategy(new LaxRedirectStrategy()).build();
        HttpPost httpPost = new HttpPost("http://localhost:8080/JavaEE/jsonb");
        httpPost.setHeader("Content-type", "application/json");

        HttpResponse response = client.execute(httpPost);
        assertEquals(200, response.getStatusLine().getStatusCode());

        final String content = new BufferedReader(new InputStreamReader(response.getEntity().getContent(),
                StandardCharsets.UTF_8)).lines().collect(Collectors.joining());
        assertFalse(content == null);

        client.close();
    }

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
