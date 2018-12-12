package ru.otus.ejb.entity;

import ru.otus.db.entity.EmpEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class EntityBean {

    @PersistenceContext(unitName = "jpa")
    private EntityManager entityManager;

    public EmpEntity loadEmployee(Long id) {
        EmpEntity employee = entityManager.find(EmpEntity.class, id);
        // false
        boolean contains = entityManager.contains(employee);
        // will issue a second query
        EmpEntity other = entityManager.find(EmpEntity.class, id);
        // false
        boolean equal = (employee == other);
        return employee;
    }
}
