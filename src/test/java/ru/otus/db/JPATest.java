package ru.otus.db;

import org.junit.*;
import ru.otus.db.entity.EmpEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class JPATest {

    public static final String PERSISTENCE_UNIT_NAME = "test-jpa";

    private static EntityManagerFactory emf;

    private EntityManager entityManager;

    @BeforeClass
    public static void setUp() {
         emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    @Before
    public void initialize() {
        entityManager = emf.createEntityManager();
    }

    @Test
    public void testEmpEntity(){
        long key = 9_999L;
        EmpEntity empEntity = entityManager.find(EmpEntity.class, key);
        assertNull(empEntity);

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        empEntity = new EmpEntity();
        empEntity.setEmpno(key);
        empEntity.setEname("Ivanov Vitalii");
        empEntity.setJob("Java expert");
        empEntity.setComm(15_000L);
        empEntity.setSal(1_000_000L);

        entityManager.persist(empEntity);
        transaction.commit();

        empEntity = entityManager.find(EmpEntity.class, key);
        assertNotNull(empEntity);
    }

    @After
    public void destroy() {
        entityManager.close();
    }

    @AfterClass
    public static void tearDown() {
        emf.close();
    }
}
