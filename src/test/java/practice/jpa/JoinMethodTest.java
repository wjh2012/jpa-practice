package practice.jpa;

import static practice.jpa.join_method.oneway.QJoinComment.joinComment;
import static practice.jpa.join_method.oneway.QJoinPost.joinPost;
import static practice.jpa.join_method.oneway.QJoinUser.joinUser;

import com.querydsl.jpa.impl.JPAQueryFactory;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import practice.jpa.join_method.oneway.JoinComment;
import practice.jpa.join_method.oneway.JoinCommentRepository;
import practice.jpa.join_method.oneway.JoinPost;
import practice.jpa.join_method.oneway.JoinPostRepository;
import practice.jpa.join_method.oneway.JoinUser;
import practice.jpa.join_method.oneway.JoinUserRepository;
import practice.jpa.join_method.twoway.JoinCode;
import practice.jpa.join_method.twoway.JoinCodeRepository;
import practice.jpa.join_method.twoway.JoinCommit;
import practice.jpa.join_method.twoway.JoinCommitRepository;
import practice.jpa.join_method.twoway.JoinMember;
import practice.jpa.join_method.twoway.JoinMemberRepository;

@DisplayName("조인 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SpringBootTest
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JoinMethodTest {

    @Autowired
    JoinUserRepository joinUserRepository;
    @Autowired
    JoinPostRepository joinPostRepository;
    @Autowired
    JoinCommentRepository joinCommentRepository;
    @Autowired
    JoinMemberRepository joinMemberRepository;
    @Autowired
    JoinCodeRepository joinCodeRepository;
    @Autowired
    JoinCommitRepository joinCommitRepository;

    private void insertOneWayData() {
        // 단방향
        JoinUser joinUser1 = new JoinUser();
        JoinUser joinUser2 = new JoinUser();

        JoinComment joinComment1 = new JoinComment();
        JoinComment joinComment2 = new JoinComment();
        JoinComment joinComment3 = new JoinComment();
        JoinComment joinComment4 = new JoinComment();
        JoinComment joinComment5 = new JoinComment();
        JoinComment joinComment6 = new JoinComment();

        JoinPost joinPost1 = new JoinPost();
        JoinPost joinPost2 = new JoinPost();
        JoinPost joinPost3 = new JoinPost();
        JoinPost joinPost4 = new JoinPost();
        JoinPost joinPost5 = new JoinPost();
        JoinPost joinPost6 = new JoinPost();

        joinUser1.setName("joinUser1");
        joinUser2.setName("joinUser2");

        joinComment1.setUser(joinUser1);
        joinComment2.setUser(joinUser1);
        joinComment3.setUser(joinUser1);
        joinComment4.setUser(joinUser2);
        joinComment5.setUser(joinUser2);
        joinComment6.setUser(joinUser2);

        joinPost1.setUser(joinUser1);
        joinPost2.setUser(joinUser1);
        joinPost3.setUser(joinUser1);
        joinPost4.setUser(joinUser2);
        joinPost5.setUser(joinUser2);
        joinPost6.setUser(joinUser2);

        joinUserRepository.save(joinUser1);
        joinUserRepository.save(joinUser2);

        joinPostRepository.save(joinPost1);
        joinPostRepository.save(joinPost2);
        joinPostRepository.save(joinPost3);
        joinPostRepository.save(joinPost4);
        joinPostRepository.save(joinPost5);
        joinPostRepository.save(joinPost6);

        joinCommentRepository.save(joinComment1);
        joinCommentRepository.save(joinComment2);
        joinCommentRepository.save(joinComment3);
        joinCommentRepository.save(joinComment4);
        joinCommentRepository.save(joinComment5);
        joinCommentRepository.save(joinComment6);
    }

    public void insertTwowayData() {

        // 양방향
        JoinMember joinMember1 = new JoinMember();
        JoinMember joinMember2 = new JoinMember();

        JoinCode joinCode1 = new JoinCode();
        JoinCode joinCode2 = new JoinCode();
        JoinCode joinCode3 = new JoinCode();
        JoinCode joinCode4 = new JoinCode();
        JoinCode joinCode5 = new JoinCode();
        JoinCode joinCode6 = new JoinCode();

        JoinCommit joinCommit1 = new JoinCommit();
        JoinCommit joinCommit2 = new JoinCommit();
        JoinCommit joinCommit3 = new JoinCommit();
        JoinCommit joinCommit4 = new JoinCommit();
        JoinCommit joinCommit5 = new JoinCommit();
        JoinCommit joinCommit6 = new JoinCommit();

        joinMember1.setName("joinMember1");
        joinMember2.setName("joinMember2");

        joinCode1.setMember(joinMember1);
        joinCode2.setMember(joinMember1);
        joinCode3.setMember(joinMember1);
        joinCode4.setMember(joinMember2);
        joinCode5.setMember(joinMember2);
        joinCode6.setMember(joinMember2);

        joinCommit1.setMember(joinMember1);
        joinCommit2.setMember(joinMember1);
        joinCommit3.setMember(joinMember1);
        joinCommit4.setMember(joinMember2);
        joinCommit5.setMember(joinMember2);
        joinCommit6.setMember(joinMember2);

        joinMemberRepository.save(joinMember1);
        joinMemberRepository.save(joinMember2);

        joinCodeRepository.save(joinCode1);
        joinCodeRepository.save(joinCode2);
        joinCodeRepository.save(joinCode3);
        joinCodeRepository.save(joinCode4);
        joinCodeRepository.save(joinCode5);
        joinCodeRepository.save(joinCode6);

        joinCommitRepository.save(joinCommit1);
        joinCommitRepository.save(joinCommit2);
        joinCommitRepository.save(joinCommit3);
        joinCommitRepository.save(joinCommit4);
        joinCommitRepository.save(joinCommit5);
        joinCommitRepository.save(joinCommit6);
    }

    @PersistenceContext
    private EntityManager em;


    @Test
    @Order(1)
    void 단방향_JPA_단건_조회_테스트() {
        insertOneWayData();

        JoinUser findUser = joinUserRepository.findByName("joinUser1").orElseThrow();
        List<JoinPost> joinPostAllByUser = joinPostRepository.findAllByUser(findUser);
        List<JoinComment> joinCommentAllByUser = joinCommentRepository.findAllByUser(findUser);

        Assertions.assertThat(joinPostAllByUser).isNotEmpty();
        Assertions.assertThat(joinCommentAllByUser).isNotEmpty();
    }

    @Test
    @Order(2)
    void 단방향_querydsl_단건_조회_테스트() {
        insertOneWayData();

        JPAQueryFactory query = new JPAQueryFactory(em);

        List<JoinUser> fetch = query
            .selectFrom(joinUser)
            .join(joinPost.user, joinUser).fetchJoin()
            .join(joinComment.user, joinUser).fetchJoin()
            .where(
                joinUser.name.eq("joinUser1")
            )
            .fetch();

    }

}

