package ru.otus.ejb.session.stateful;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import java.util.concurrent.TimeUnit;

@Stateful
@StatefulTimeout(value = 15, unit = TimeUnit.SECONDS)
@LocalBean
public class AnotherSimpleBean {

    private int result;

    public int incrementLastValue(int value) {
        return result += value;
    }
}
