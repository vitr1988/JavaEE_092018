package ru.otus.webservice.soap;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.otus.webservice.soap.client.Calculator;

import javax.xml.ws.Endpoint;

public class CalculatorTest extends Assert {

    @BeforeClass
    public static void init(){
        Endpoint.publish (
            "http://localhost:2304/JavaEE/calculator",
                new CalculatorImpl());
    }

    @Test
    public void test() throws InterruptedException {
        CalculatorImplService service = new CalculatorImplService();
        Calculator calculatorPort = service.getCalculatorImplPort();
        assertEquals(45, calculatorPort.add(19, 26));
        assertEquals(0, calculatorPort.add(19, -19));
        assertEquals(-72, calculatorPort.add(-25, -47));
    }
}
