package ru.otus.ejb;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.otus.ejb.session.embeddable.AppBean;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;
import java.io.File;
import java.util.Properties;

public class EmbeddableContainerTest {

    private static Context ctx;
    private static EJBContainer ejbContainer;

    @BeforeClass
    public static void setUpBeforeClass() {
        Properties props = new Properties();
        props.put(EJBContainer.MODULES, new File("target/classes"));
        ejbContainer = EJBContainer.createEJBContainer(props);
        System.out.println("Container Opening" );
        ctx = ejbContainer.getContext();
    }

    @AfterClass
    public static void tearDownAfterClass() {
        ejbContainer.close();
        System.out.println("Container Closing" );
    }

    public void test() throws NamingException {
        AppBean bean = (AppBean) ctx.lookup("java:global/classes/AppBean");
        Assert.assertNotNull(bean);
        Assert.assertEquals("Hello Vasya", bean.sayHello("Vasya"));
    }
}
