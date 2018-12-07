package ru.otus.ejb;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.otus.ejb.session.singleton.SingletonBean;
import ru.otus.ejb.session.stateful.AnotherSimpleBean;
import ru.otus.ejb.session.stateless.ApplicationSimpleBean;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.Properties;

public class EJBClientTest {

    private static InitialContext ctxByPropertyFile;
    private static InitialContext defaultCtx;

    @BeforeClass
    public static void setup(){
        Properties props = new Properties();
        try {
            props.load(EJBClientTest.class.getResourceAsStream("/jndi.properties"));
            ctxByPropertyFile = new InitialContext(props);
            defaultCtx = new InitialContext();
        } catch (IOException | NamingException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testSimpleBeanInsideGlassfish() throws Exception {
        // glassfish specific test case (non-portable JNDI name)
        ApplicationSimpleBean bean = (ApplicationSimpleBean) ctxByPropertyFile.lookup("ejb/SimpleBean");
        Assert.assertEquals("Hello world", bean.saySmth());
    }

    @Test
    public void testSimpleBeanGlobally() throws Exception {
        // universal test case (portable JNDI name)
        ApplicationSimpleBean bean = (ApplicationSimpleBean) defaultCtx.lookup("java:global/JavaEE/SimpleBean!ru.otus.ejb.session.stateless.ApplicationSimpleBean");
        Assert.assertEquals("Hello world", bean.saySmth());
    }

    @Test
    public void testAnotherSimpleBeanGlobally() throws Exception {
        AnotherSimpleBean bean = (AnotherSimpleBean) defaultCtx.lookup("java:global/JavaEE/AnotherSimpleBeanImpl!ru.otus.ejb.session.stateful.AnotherSimpleBean");
        int lastResult = 15;
        Assert.assertEquals(lastResult, bean.incrementLastValue(lastResult));
        Assert.assertEquals(2 * lastResult, bean.incrementLastValue(lastResult));
    }

    @Test
    public void testSingletonBean() throws Exception {
        SingletonBean bean = (SingletonBean) defaultCtx.lookup("java:global/JavaEE/SingletonBeanImpl!ru.otus.ejb.session.singleton.SingletonBean");
        Long key = Long.MAX_VALUE;
        Assert.assertNull(bean.get(key));
        bean.put(key, key + "");
        Assert.assertNotNull(bean.get(key));
    }
}
