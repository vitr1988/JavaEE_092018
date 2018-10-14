package ru.otus.servlet;

import org.eclipse.jetty.http.HttpTester;
import org.eclipse.jetty.servlet.ServletTester;
import org.junit.Test;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ServletTest {

    @Test
    public void shouldTestHelloWorldTextPrinted() {
        try {
            ServletTester servletTester = new ServletTester();
            servletTester.addServlet(ServletForTestingPurpose.class, "/testing");
            servletTester.start();

            HttpTester.Request request = HttpTester.newRequest();
            request.setMethod("GET");
            request.setURI("/testing");
            request.setVersion("HTTP/1.0");
            HttpTester.Response response = HttpTester.parseResponse(servletTester.getResponses(request.generate()));

            assertEquals(200, response.getStatus());
            assertEquals("Hello World", response.getContent());
        }
        catch (Exception e) {
            fail();
        }
    }

    @Test
    public void shouldTestTextPrinted() {
        ClientBuilder clientBuilder = ClientBuilder.newBuilder().sslContext(getTrustedAllSSLContext());
        Client client = clientBuilder.build();
        WebTarget target = client.target("https://localhost:8443/JavaEE/testing");

        Response response = target.request(MediaType.TEXT_HTML).get();
        assertEquals(200, response.getStatus());
    }

    private SSLContext getTrustedAllSSLContext(){
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager(){
            public java.security.cert.X509Certificate[] getAcceptedIssuers(){return null;}
            public void checkClientTrusted(X509Certificate[] certs, String authType){}
            public void checkServerTrusted(X509Certificate[] certs, String authType){}
        }};

        // Install the all-trusting trust manager
        SSLContext sc = null;
        try {
            sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sc;
    }
}

