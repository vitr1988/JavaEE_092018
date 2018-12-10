package ru.otus.ejb.session.stateful;

import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import java.util.concurrent.TimeUnit;

@Stateful(passivationCapable = true)
@StatefulTimeout(value = 15, unit = TimeUnit.SECONDS)
@Remote(AnotherSimpleBean.class)
//@LocalBean
public class AnotherSimpleBeanImpl implements AnotherSimpleBean {

    private int result;

    @Override
    public int incrementLastValue(int value) {
        return result += value;
    }
}
