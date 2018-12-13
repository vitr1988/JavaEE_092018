package ru.otus.ejb.session.async;

import javax.ejb.AccessTimeout;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Stateless
public class AsyncApplicationBean {

    @Asynchronous
    public Future<String> helloWorld() {
        // simulate some long running process
        waitFor(5000);
        String text = "Hi there from thread : " + Thread.currentThread().getId() + "!";
        return new AsyncResult<>(text);
    }

    @Asynchronous
    @AccessTimeout(value = 2, unit = TimeUnit.SECONDS)
    public void generate() {
        waitFor(1500);
    }

    public void waitFor(int miliseconds) {
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupted();
        }
    }
}
