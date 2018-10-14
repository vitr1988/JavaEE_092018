package ru.otus.servlet;

import org.eclipse.jetty.http.HttpTester;
import org.eclipse.jetty.servlet.ServletTester;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ServletTest {

    @Test
    public void shouldTestHelloWorldTextPrinted() {
        try {
            ServletTester servletTester = new ServletTester();
            servletTester.addServlet(HelloWorldServlet.class, "/testing");
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
}
