package ru.otus.servlet.listener.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

@Data
@AllArgsConstructor
public class User implements HttpSessionBindingListener {

    private int id;
    private String name;

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        this.name = "Petrov petr";
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {

    }
}
