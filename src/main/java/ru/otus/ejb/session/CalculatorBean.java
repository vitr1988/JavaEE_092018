package ru.otus.ejb.session;

import javax.ejb.Stateless;

@Stateless
public class CalculatorBean {

    public int summa(int a, int b){
        return a + b;
    }

    public int subtract(int a, int b){
        return a - b;
    }
}
