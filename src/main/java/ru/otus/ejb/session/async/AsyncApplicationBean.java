package ru.otus.ejb.session.async;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import java.util.concurrent.Future;

@Stateless
public class AsyncApplicationBean {
    @Asynchronous
    public Future<String> helloWorld() {
        return new AsyncResult<>("Hi there!");
    }

    @Asynchronous
    public void generate() {

    }
}
