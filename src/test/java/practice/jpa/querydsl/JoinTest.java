package practice.jpa.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import practice.jpa.mapping.twoway.ManyToOne.QTwoway_Member_Master;
import practice.jpa.mapping.twoway.ManyToOne.QTwoway_Team_Slave;
import practice.jpa.mapping.twoway.ManyToOne.Twoway_Member_Master;
import practice.jpa.mapping.twoway.ManyToOne.Twoway_Team_Slave;

@DisplayName("조인 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SpringBootTest
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JoinTest {

    @PersistenceContext
    private EntityManager em;

    @Test
    void queryDSL_조인_테스트() {

        // given
        Twoway_Team_Slave team1 = new Twoway_Team_Slave();
        team1.setName("team_name1");
        Twoway_Team_Slave team2 = new Twoway_Team_Slave();
        team2.setName("team_name2");

        Twoway_Member_Master member1 = new Twoway_Member_Master();
        member1.setName("member1_name");
        Twoway_Member_Master member2 = new Twoway_Member_Master();
        member2.setName("member2_name");
        Twoway_Member_Master member3 = new Twoway_Member_Master();
        member3.setName("member3_name");
        Twoway_Member_Master member4 = new Twoway_Member_Master();
        member4.setName("member4_name");
        Twoway_Member_Master member5 = new Twoway_Member_Master();
        member4.setName("member5_name");

        member1.setTeam(team1);
        member2.setTeam(team1);
        member3.setTeam(team1);
        member4.setTeam(team2);
        member5.setTeam(team2);

        em.persist(team1);
        em.persist(team2);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
        em.persist(member5);

        em.flush();
        em.clear();

        // when
        JPAQueryFactory query = new JPAQueryFactory(em);

        QTwoway_Member_Master member = QTwoway_Member_Master.twoway_Member_Master;
        QTwoway_Team_Slave team = QTwoway_Team_Slave.twoway_Team_Slave;

        List<Twoway_Member_Master> fetch = query
            .select(member)
            .from(member)
            .join(member.team, team)
            .fetch();

        Map<Twoway_Team_Slave, List<Twoway_Member_Master>> collect = fetch.stream()
            .collect(Collectors.groupingBy(Twoway_Member_Master::getTeam));
        Assertions.assertThat(collect.size()).isEqualTo(2);

    }
}
