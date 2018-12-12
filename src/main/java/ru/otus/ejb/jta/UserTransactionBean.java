package ru.otus.ejb.jta;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.*;
import java.math.BigDecimal;
import java.util.Random;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class UserTransactionBean {

    @Resource
    UserTransaction transaction;

    public void executeTransfer(String fromAccontId, String toAccountId, BigDecimal amount)
            throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        transaction.begin();
        if(new Random().nextBoolean()) {
            transaction.rollback();
            throw new RuntimeException("Insufficient fund.");
        } else {
            transaction.commit();
        }
    }
}
