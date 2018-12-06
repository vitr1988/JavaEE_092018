package ru.otus.ejb;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.otus.ejb.session.stateful.AnotherSimpleBean;
import ru.otus.ejb.session.stateless.ApplicationSimpleBean;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.Properties;

public class JNDIClientTest {

    private static InitialContext ctx;

    @BeforeClass
    public static void setup(){
        Properties props = new Properties();
        try {
            props.load(JNDIClientTest.class.getResourceAsStream("/jndi.properties"));
            ctx = new InitialContext(props);
        } catch (IOException | NamingException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testSimpleBeanInsideGlassfish() throws Exception {
        // glassfish specific test case (non-portable JNDI name)
        ApplicationSimpleBean bean = (ApplicationSimpleBean) ctx.lookup("ejb/SimpleBean");
        Assert.assertEquals("Hello world", bean.saySmth());
    }

    @Test
    public void testSimpleBeanGlobally() throws Exception {
        // universal test case (portable JNDI name)
        ApplicationSimpleBean bean = (ApplicationSimpleBean) new InitialContext().lookup("java:global/JavaEE/SimpleBean!ru.otus.ejb.session.stateless.ApplicationSimpleBean");
        Assert.assertEquals("Hello world", bean.saySmth());
    }

    @Test
    public void testAnotherSimpleBeanGlobally() throws Exception {
        AnotherSimpleBean bean = (AnotherSimpleBean) new InitialContext().lookup("java:global/JavaEE/AnotherSimpleBeanImpl!ru.otus.ejb.session.stateful.AnotherSimpleBean");
        int lastResult = 15;
        Assert.assertEquals(lastResult, bean.incrementLastValue(lastResult));
        Assert.assertEquals(2 * lastResult, bean.incrementLastValue(lastResult));
    }
}
