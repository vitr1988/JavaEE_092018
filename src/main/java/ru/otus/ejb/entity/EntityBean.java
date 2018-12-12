package ru.otus.ejb.entity;

import ru.otus.db.entity.EmpEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

@Stateless
public class EntityBean {

    @PersistenceContext(unitName = "jpa")
    private EntityManager entityManager;

    public EmpEntity loadEmployee(Long id) {
        // for transaction-type="RESOURCE_LOCAL"
//        EntityTransaction transaction = entityManager.getTransaction();
//        transaction.begin();
//        try {
            EmpEntity employee = entityManager.find(EmpEntity.class, id);
            // true
            boolean contains = entityManager.contains(employee);
            // will issue a second query
            EmpEntity other = entityManager.find(EmpEntity.class, id);
            // true
            boolean equal = (employee == other);
            return employee;
//        }
//        finally {
//            transaction.commit();
//        }
    }
}
