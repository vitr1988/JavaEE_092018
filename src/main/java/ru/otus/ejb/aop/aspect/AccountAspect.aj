package ru.otus.ejb.aop.aspect;

import java.text.MessageFormat;

public aspect AccountAspect {

    final int MIN_BALANCE = 10;

    pointcut callWithDraw(int amount, Account account):
            call(boolean Account.withdraw(int)) && args(amount) && target(account);

    before(int amount, Account account): callWithDraw(amount, account) {
        System.out.println(MessageFormat.format(" Balance before withdrawal: {0}", account.getBalance()));
        System.out.println(MessageFormat.format(" Withdraw amount: {0}", amount));
    }

    boolean around(int amount, Account account): callWithDraw(amount, account) {
        if (account.getBalance() < MIN_BALANCE) {
            System.out.println("Withdrawal Rejected because of limitations!");
            return false;
        }
        if (account.getBalance() < amount) {
            System.out.println("Withdrawal Rejected! Balance isn't enought to withdraw!");
            return false;
        }
        return proceed(amount, account);
    }

    after(int amount, Account balance): callWithDraw(amount, balance) {
        System.out.println(MessageFormat.format("Balance after withdrawal : {0}", balance.getBalance()));
    }
}
