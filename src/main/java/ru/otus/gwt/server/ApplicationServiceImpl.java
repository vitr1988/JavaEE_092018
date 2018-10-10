package ru.otus.gwt.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import ru.otus.gwt.client.ApplicationService;
import ru.otus.gwt.shared.User;
import ru.otus.gwt.shared.exception.WrongCredentialException;

import javax.servlet.annotation.WebServlet;
import java.util.Objects;

public class ApplicationServiceImpl extends RemoteServiceServlet implements ApplicationService {
    // Implementation of sample interface method
    public String getMessage(String msg) {
        return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
    }

    @Override
    public void authorize(User user) throws WrongCredentialException {
        if (!Objects.equals(user.getLogin(), user.getPassword())) {
            throw new WrongCredentialException("Некорректные логин/пароль");
        }
    }
}