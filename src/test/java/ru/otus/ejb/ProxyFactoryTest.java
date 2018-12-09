package ru.otus.ejb;

import org.junit.Assert;
import org.junit.Test;
import ru.otus.ejb.model.Calculator;
import ru.otus.ejb.model.Countable;
import ru.otus.ejb.session.proxy.ProxyFactory;

public class ProxyFactoryTest {

    @Test
    public void testCreate(){
        Countable origin = new Calculator();
        Countable countable = ProxyFactory.create(origin);
        Assert.assertEquals(1, countable.prefixIncrement());
        Assert.assertEquals(1, countable.postfixIncrement());
        Assert.assertEquals(3, countable.prefixIncrement());
    }
}
