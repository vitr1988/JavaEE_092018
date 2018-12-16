package ru.otus.ejb.aop.aspect;

public class Account {

    private int balance = 100;

    public boolean withdraw(int amount) {
//        if (balance < amount) {
//            return false;
//        }
        balance = balance - amount;
        return true;
    }

    public int getBalance() {
        return balance;
    }
}
