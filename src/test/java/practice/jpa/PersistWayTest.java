package practice.jpa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

@DisplayName("JPA 쿼리 방법")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SpringBootTest
public class PersistWayTest {
    @PersistenceContext
    private EntityManager em;

    @Test
    void 명시적_트랜잭션() {
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        em.persist(null);
        em.flush();
        em.clear();

        tx.commit();
        tx.rollback();

        em.close();
    }

    @Test
    @Transactional
    void 선언적_트랜잭션() {

    }
}
