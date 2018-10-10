package ru.otus.gwt.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import ru.otus.gwt.client.service.ApplicationService;
import ru.otus.gwt.shared.User;
import ru.otus.gwt.shared.exception.WrongCredentialException;
import ru.otus.gwt.shared.validation.ValidationRule;

import javax.servlet.ServletException;

public class ApplicationServiceImpl extends RemoteServiceServlet implements ApplicationService {
    // Implementation of sample interface method
    public String getMessage(String msg) {
        return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
    }

    @Override
    public void authorize(User user) throws WrongCredentialException {
        if (ValidationRule.isValid(user)){
            try {
                getThreadLocalRequest().login(user.getLogin(), user.getPassword());
            } catch (ServletException e) {
                throw new WrongCredentialException("Некорректные логин/пароль");
            }
        }
    }
}