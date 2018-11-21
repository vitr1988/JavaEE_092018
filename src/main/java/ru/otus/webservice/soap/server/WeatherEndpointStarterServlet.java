package ru.otus.webservice.soap.server;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Endpoint;
import java.io.IOException;

@WebServlet(name = "WeatherEndpointStarterServlet", urlPatterns = "/weatherStarterServlet", loadOnStartup = 1)
public class WeatherEndpointStarterServlet extends HttpServlet {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) {
        Endpoint.publish(
                request.getScheme() + "://" + request.getServerName() + ":8700"
                    + request.getContextPath() + "/weather",
                new WeatherServiceAsRestfulSimulator());
    }
}
