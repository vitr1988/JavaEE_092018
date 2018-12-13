package ru.otus.ejb.entity;

import ru.otus.db.entity.EmpEntity;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.persistence.*;
import javax.transaction.*;
import javax.transaction.RollbackException;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class EntityBean {

//    @Resource
//    UserTransaction tx;

    @PersistenceContext(unitName = "jpa")
    private EntityManager entityManager;

    @Resource
    EJBContext context;

//    @PersistenceUnit(unitName = "jpa")
//    private EntityManagerFactory entityManagerFactory;

    public EmpEntity loadEmployee(Long id) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        // for transaction-type="RESOURCE_LOCAL"
//        EntityTransaction transaction = entityManager.getTransaction();
//        transaction.begin();
//        try {
//        tx.begin();
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EmpEntity employee = entityManager.find(EmpEntity.class, id);
            // true
            boolean contains = entityManager.contains(employee);
            // will issue a second query
            EmpEntity other = entityManager.find(EmpEntity.class, id);
            // true

            boolean equal = (employee == other);
            if (!equal) {
                context.setRollbackOnly();
            }
//            tx.commit();
            return employee;
//        }
//        finally {
//            transaction.commit();
//        }
    }
}
