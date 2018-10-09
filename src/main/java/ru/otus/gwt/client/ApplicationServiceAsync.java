package ru.otus.gwt.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ApplicationServiceAsync {
    void getMessage(String msg, AsyncCallback<String> async);
}
