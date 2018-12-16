package ru.otus.ejb.aop;

import org.junit.Assert;
import org.junit.Test;
import ru.otus.ejb.aop.aspect.Account;

public class AspectTest {

    @Test
    public void testWithdraw() {
        Account account = new Account();
        Assert.assertTrue(account.withdraw(5));
        Assert.assertEquals(95, account.getBalance());
        Assert.assertFalse(account.withdraw(100));
        Assert.assertEquals(95, account.getBalance());
    }

}
