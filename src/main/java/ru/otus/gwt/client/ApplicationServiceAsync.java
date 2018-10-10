package ru.otus.gwt.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.otus.gwt.shared.User;

public interface ApplicationServiceAsync {
    void getMessage(String msg, AsyncCallback<String> async);
    void authorize(User user, AsyncCallback<Void> async);
}
