package ru.otus.webservice.soap.server;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.xml.ws.Endpoint;

@WebListener
public class WeatherEndpointServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            String webServiceUrl = "http://localhost:8700" + sce.getServletContext().getContextPath() + "/weather";
            Endpoint.publish(webServiceUrl, new WeatherServiceAsRestfulSimulator());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
