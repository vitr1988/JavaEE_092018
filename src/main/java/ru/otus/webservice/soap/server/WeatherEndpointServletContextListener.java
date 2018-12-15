package ru.otus.webservice.soap.server;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.xml.ws.Endpoint;

@WebListener
public class WeatherEndpointServletContextListener implements ServletContextListener {

    private Endpoint publishedEndpoint;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String webServiceUrl = "http://localhost:8700" + sce.getServletContext().getContextPath() + "/weather";
        publishedEndpoint = Endpoint.publish(webServiceUrl, new WeatherServiceAsRestfulSimulator());

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (publishedEndpoint != null && publishedEndpoint.isPublished()) {
            publishedEndpoint.stop();
        }
    }
}
