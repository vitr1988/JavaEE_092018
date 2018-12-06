package ru.otus.ejb.session.stateless;

import javax.ejb.Remote;

@Remote
public interface ApplicationSimpleBean {
    void doSomething();
    String saySmth();
}
