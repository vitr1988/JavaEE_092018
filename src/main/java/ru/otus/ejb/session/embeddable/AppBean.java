package ru.otus.ejb.session.embeddable;

import javax.ejb.Stateless;

@Stateless//(mappedName = "ejb/AppBean")
public class AppBean {
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
