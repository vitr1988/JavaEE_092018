package ru.otus.ejb.aop.bean;

import ru.otus.ejb.aop.interceptor.Logger;

import javax.ejb.Stateful;
import javax.interceptor.ExcludeClassInterceptors;
import javax.interceptor.Interceptors;
import java.io.Serializable;

@Stateful
@Interceptors(Logger.class)
//@ExcludeDefaultInterceptors
public class Count implements Serializable {

    private int count = 1;

//    @Interceptors(Logger.class)
    public int getResult() {
        return count;
    }

//    @ExcludeClassInterceptors
    public void increment(){
        count++;
    }

}
