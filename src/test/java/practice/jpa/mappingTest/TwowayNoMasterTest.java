package practice.jpa.mappingTest;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import practice.jpa.mapping.twoway.noMaster.Twoway_Member;
import practice.jpa.mapping.twoway.noMaster.Twoway_Team;

@DisplayName("양방향 연관관계 주인없음 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SpringBootTest
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TwowayNoMasterTest {

    @PersistenceContext
    private EntityManager em;

    @Test
    @Order(1)
    @DisplayName("N에서 연관관계 insert-성공")
    void MANY_MASTER_MANY_INSERT_SUCCESS() {
        // given
        Twoway_Team team1 = new Twoway_Team();
        team1.setName("team_name1");
        Twoway_Team team2 = new Twoway_Team();
        team2.setName("team_name2");

        Twoway_Member member1 = new Twoway_Member();
        member1.setName("member1_name");
        Twoway_Member member2 = new Twoway_Member();
        member2.setName("member2_name");
        Twoway_Member member3 = new Twoway_Member();
        member3.setName("member3_name");

        // when
        member1.setTeam(team1);
        member2.setTeam(team1);
        member3.setTeam(team1);

        em.persist(team1);
        em.persist(team2);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);

        em.flush();
        em.clear();

        // then
        Twoway_Member findMember1 = em.find(Twoway_Member.class, member1.getId());
        Twoway_Member findMember2 = em.find(Twoway_Member.class, member2.getId());
        Twoway_Member findMember3 = em.find(Twoway_Member.class, member3.getId());

        Assertions.assertThat(findMember1.getTeam()).isNotNull();
        Assertions.assertThat(findMember2.getTeam()).isNotNull();
        Assertions.assertThat(findMember2.getTeam()).isNotNull();
        Assertions.assertThat(findMember3.getTeam()).isNotNull();
    }

    @Test
    @Order(2)
    @DisplayName("1에서 연관관계 insert-성공")
    void MANY_MASTER_ONE_INSERT_FAIL() {
        // given
        Twoway_Team team1 = new Twoway_Team();
        team1.setName("team_name1");
        Twoway_Team team2 = new Twoway_Team();
        team2.setName("team_name2");

        Twoway_Member member1 = new Twoway_Member();
        member1.setName("member1_name");
        Twoway_Member member2 = new Twoway_Member();
        member2.setName("member2_name");
        Twoway_Member member3 = new Twoway_Member();
        member3.setName("member3_name");

        // when
        List<Twoway_Member> members = team1.getMembers();
        members.add(member1);
        members.add(member2);
        members.add(member3);

        em.persist(team1);
        em.persist(team2);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);

        em.flush();
        em.clear();

        // then
        Twoway_Member findMember1 = em.find(Twoway_Member.class, member1.getId());
        Twoway_Member findMember2 = em.find(Twoway_Member.class, member2.getId());
        Twoway_Member findMember3 = em.find(Twoway_Member.class, member3.getId());

        Assertions.assertThat(findMember1.getTeam()).isNotNull();
        Assertions.assertThat(findMember2.getTeam()).isNotNull();
        Assertions.assertThat(findMember3.getTeam()).isNotNull();
    }

    @Test
    @Order(3)
    @DisplayName("N에서 연관관계 insert-업데이트-성공")
    void MANY_MASTER_MANY_INSERT_UPDATE_SUCCESS() {
        // given
        Twoway_Team team1 = new Twoway_Team();
        team1.setName("team_name1");
        Twoway_Team team2 = new Twoway_Team();
        team2.setName("team_name2");

        Twoway_Member member1 = new Twoway_Member();
        member1.setName("member1_name");
        Twoway_Member member2 = new Twoway_Member();
        member2.setName("member2_name");
        Twoway_Member member3 = new Twoway_Member();
        member3.setName("member3_name");

        // when
        member1.setTeam(team1);
        member2.setTeam(team1);
        member3.setTeam(team1);

        em.persist(team1);
        em.persist(team2);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);

        em.flush();
        em.clear();

        Twoway_Member findMember3 = em.find(Twoway_Member.class, member3.getId());
        findMember3.setTeam(team2);

        em.flush();
        em.clear();

        Twoway_Member findMember33 = em.find(Twoway_Member.class, member3.getId());

        Assertions.assertThat(findMember33.getTeam().getId()).isEqualTo(team2.getId());
    }

    @Test
    @Order(4)
    @DisplayName("N에서 연관관계 insert-업데이트-실패")
    void MANY_MASTER_MANY_INSERT_UPDATE_FAIL() {
        // given
        Twoway_Team team1 = new Twoway_Team();
        team1.setName("team_name1");
        Twoway_Team team2 = new Twoway_Team();
        team2.setName("team_name2");

        Twoway_Member member1 = new Twoway_Member();
        member1.setName("member1_name");
        Twoway_Member member2 = new Twoway_Member();
        member2.setName("member2_name");
        Twoway_Member member3 = new Twoway_Member();
        member3.setName("member3_name");

        // when
        member1.setTeam(team1);
        member2.setTeam(team1);
        member3.setTeam(team1);

        em.persist(team1);
        em.persist(team2);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);

        em.flush();
        em.clear();

        Twoway_Member findMember3 = em.find(Twoway_Member.class, member3.getId());
        team2.getMembers().add(findMember3);

        em.flush();
        em.clear();

        Twoway_Member findMember33 = em.find(Twoway_Member.class, member3.getId());
        Assertions.assertThat(findMember33.getTeam().getId()).isNotEqualTo(team2.getId());
    }

}
