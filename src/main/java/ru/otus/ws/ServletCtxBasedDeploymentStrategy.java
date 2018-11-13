package ru.otus.ws;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.websocket.server.ServerContainer;

@WebListener
public class ServletCtxBasedDeploymentStrategy implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce){
        //obtain the instance of container
        ServerContainer sc = (ServerContainer) sce.getServletContext().getAttribute("javax.websocket.server.ServerContainer");
        //trigger endpoint deployment
        deployAnnotatedEndpoint(sc);
        deployProgEndpoint(sc);
    }

    private void deployAnnotatedEndpoint(ServerContainer container) {
//        container.addEndpoint(StockTicker.class);
    }

    private void deployProgEndpoint(ServerContainer container) {
//        container.addEndpoint(ServerEndpointConfig.Builder.create(ChatClub.class, "/chatclub").build());
    }
}
