package ru.otus.db.demo;

import ru.otus.db.entity.EmpEntity;

import javax.persistence.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/jpa")
public class JPAServlet extends HttpServlet {

    public static final String PERSISTENCE_UNIT_NAME = "jpa";

    private static final EntityManagerFactory emf =
          Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME); // for Tomcat

//    @PersistenceUnit(unitName = PERSISTENCE_UNIT_NAME)
//    EntityManagerFactory emf; // for Glassfish

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
}
