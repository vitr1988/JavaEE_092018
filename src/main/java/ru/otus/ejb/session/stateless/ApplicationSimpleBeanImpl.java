package ru.otus.ejb.session.stateless;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

@Stateless(name="SimpleBean", mappedName = "ejb/SimpleBean")
public class ApplicationSimpleBeanImpl implements ApplicationSimpleBean {

    @Resource
    SessionContext context;

    @Override
    public void doSomething() {
        System.out.println(saySmth());
    }

    @Override
    public String saySmth() {
        return "Hello world";
    }
}
