package ru.otus.gwt.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ru.otus.gwt.shared.User;
import ru.otus.gwt.shared.exception.WrongCredentialException;

@RemoteServiceRelativePath("ApplicationService")
public interface ApplicationService extends RemoteService {
    String getMessage(String msg);
    void authorize(User user) throws WrongCredentialException;
}
