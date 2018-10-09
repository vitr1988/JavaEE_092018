package ru.otus.gwt.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import ru.otus.gwt.client.ApplicationService;

public class ApplicationServiceImpl extends RemoteServiceServlet implements ApplicationService {
    // Implementation of sample interface method
    public String getMessage(String msg) {
        return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
    }
}