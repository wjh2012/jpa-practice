package practice.jpa;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import practice.jpa.jpaRepository.JpaMember;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@DisplayName("영속성 컨텍스트 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Rollback(false)
public class PersistenceContextTest {


    @PersistenceContext
    private EntityManager em;

    @Test
    @Transactional
    void persist_테스트() {
        JpaMember member = new JpaMember();
        member.setName("persist_test");

        System.out.println("before");

        em.persist(member); // 영속성 컨텍스트에 저장

        System.out.println("after");
    }
}
