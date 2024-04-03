package practice.jpa.querydsl;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@DisplayName("페치 조인 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SpringBootTest
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FetchJoinTest {
    @PersistenceContext
    private EntityManager em;

    @Test
    void 기본_fetch_조인_테스트() {
        
    }
}
