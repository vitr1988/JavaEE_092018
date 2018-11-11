package ru.otus.db.demo;

import ru.otus.db.demo.util.Generator;
import ru.otus.db.entity.DeptEntity;
import ru.otus.db.entity.EmpEntity;

import javax.persistence.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/jpa")
public class JPAServlet extends HttpServlet {

    public static final String PERSISTENCE_UNIT_NAME = "jpa";

    @PersistenceUnit(unitName = PERSISTENCE_UNIT_NAME)
    EntityManagerFactory emf;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = emf.createEntityManager(); // for Tomcat
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Query q = em.createQuery(
                    "select emp from EmpEntity emp JOIN emp.deptNo dept " +
                    "where emp.deptNo.id = :deptId");
            q.setParameter("deptId", 10L);
            List<EmpEntity> result = q.getResultList();
            try (PrintWriter pw = response.getWriter()){
                result.stream().forEach(pw::println);
            }
            transaction.commit();
        }
        catch (Exception e){
            transaction.rollback();
            throw new ServletException(e);
        }
        finally {
            em.close();
        }
    }

    private Long empNo;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = emf.createEntityManager(); // for Tomcat
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            EmpEntity empEntity = new EmpEntity();
            empEntity.setEmpno(empNo = Generator.generateDigits(4));
            String lastName = Generator.generateName();
            empEntity.setEname(lastName);
            empEntity.setJob("DEVELOPER");
            empEntity.setDeptNo(em.find(DeptEntity.class, 10L));
            em.persist(empEntity);
            em.flush();
            transaction.commit();
            response.getWriter().println("Employee '" + lastName + "' has been successfully created");
        }
        catch (Exception e){
            transaction.rollback();
            throw new ServletException(e);
        }
        finally {
            em.close();
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (empNo == null) return;

        EntityManager em = emf.createEntityManager(); // for Tomcat
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            EmpEntity empEntity = em.find(EmpEntity.class, empNo);
            empEntity.setJob("Boss");
            empEntity.setDeptNo(em.find(DeptEntity.class, 20L));
            em.merge(empEntity);
            em.flush();
            transaction.commit();
            response.getWriter().println("Employee '" + empEntity.getEname() + "' has been successfully updated");
        }
        catch (Exception e){
            transaction.rollback();
            throw new ServletException(e);
        }
        finally {
            em.close();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (empNo == null) return;
        EntityManager em = emf.createEntityManager(); // for Tomcat
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            EmpEntity empEntity = em.find(EmpEntity.class, empNo);
            em.remove(empEntity);
            em.flush();
            transaction.commit();
            response.getWriter().println("Employee '" + empEntity.getEname() + "' has been successfully deleted");
        }
        catch (Exception e){
            transaction.rollback();
            throw new ServletException(e);
        }
        finally {
            em.close();
        }
    }
}
