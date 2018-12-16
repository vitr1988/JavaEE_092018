package ru.otus.ejb.aop;

import org.junit.Assert;
import org.junit.Test;
import ru.otus.ejb.aop.aspect.AOPCalculator;

public class CalculatorTest {

    @Test
    public void testCalculate(){
        AOPCalculator calculator = new AOPCalculator();
        Assert.assertEquals(5, calculator.sqrt(25), 0);
    }
}
