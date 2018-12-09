package ru.otus.ejb.model;

public class Calculator implements Countable {

    int lastCounter;

    @Override
    public int prefixIncrement() {
        return ++lastCounter;
    }

    @Override
    public int postfixIncrement() {
        return lastCounter++;
    }
}
