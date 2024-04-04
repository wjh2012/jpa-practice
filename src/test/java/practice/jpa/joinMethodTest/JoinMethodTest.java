package practice.jpa.joinMethodTest;


import static practice.jpa.join_method.oneway.QJoinImage.joinImage;
import static practice.jpa.join_method.oneway.QJoinPost.joinPost;
import static practice.jpa.join_method.oneway.QJoinUser.joinUser;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import practice.jpa.join_method.dto.MemberCodeCommitDTO2;
import practice.jpa.join_method.dto.UserPostImageDTO;
import practice.jpa.join_method.oneway.JoinImage;
import practice.jpa.join_method.oneway.JoinImageRepository;
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
@Rollback(value = false)
public class JoinMethodTest {

    @Autowired
    JoinUserRepository joinUserRepository;
    @Autowired
    JoinPostRepository joinPostRepository;
    @Autowired
    JoinImageRepository joinImageRepository;
    @Autowired
    JoinMemberRepository joinMemberRepository;
    @Autowired
    JoinCodeRepository joinCodeRepository;
    @Autowired
    JoinCommitRepository joinCommitRepository;

    @PersistenceContext
    private EntityManager em;


    @Test
    @Order(1)
    void 단방향_JPA_단건_조회_테스트() {
        insertOneWayData();
        em.flush();
        em.clear();

        JoinUser findUser = joinUserRepository.findByName("joinUser1").orElseThrow();
        List<JoinPost> joinPostAllByUser = joinPostRepository.findAllByUser(findUser);
        List<JoinImage> joinImageAllByUser = joinImageRepository.findAllByUser(findUser);

        Assertions.assertThat(joinPostAllByUser).isNotEmpty();
        Assertions.assertThat(joinImageAllByUser).isNotEmpty();
    }

    @Test
    @Order(2)
    void 단방향_querydsl_단건_조회_테스트() {
        insertOneWayData();
        em.flush();
        em.clear();

        JPAQueryFactory query = new JPAQueryFactory(em);

        List<JoinUser> fetch1 = query
            .select(joinUser)
            .from(joinUser)
            .where(joinUser.name.eq("joinUser1"))
            .fetch();

        List<JoinPost> fetch2 = query
            .select(joinPost)
            .from(joinPost)
            .leftJoin(joinPost.user, joinUser).fetchJoin()
            .where(joinUser.name.eq("joinUser1"))
            .fetch();

        List<JoinImage> fetch3 = query
            .select(joinImage)
            .from(joinImage)
            .leftJoin(joinImage.user, joinUser).fetchJoin()
            .where(joinUser.name.eq("joinUser1"))
            .fetch();
    }

    @Test
    @Order(3)
    void 단방향_querydsl_단건_튜플_조회_테스트() {
        insertOneWayData();
        em.flush();
        em.clear();

        JPAQueryFactory query = new JPAQueryFactory(em);

        List<Tuple> result = query
            .selectDistinct(joinUser, joinPost, joinImage)
            .from(joinUser)
            .leftJoin(joinImage).on(joinImage.user.eq(joinUser))
            .leftJoin(joinPost).on(joinPost.user.eq(joinUser))
            .where(joinUser.name.eq("joinUser1"))
            .fetch();

        result.forEach(t -> {
            System.out.println(
                t.get(joinUser).getName() + t.get(joinPost).getName() + t.get(joinImage).getName());
        });
    }

    @Test
    @Order(4)
    void 단방향_querydsl_단건_DTO_조회_테스트() {
        insertOneWayData();
        em.flush();
        em.clear();

        JPAQueryFactory query = new JPAQueryFactory(em);

        List<UserPostImageDTO> result = query
            .select(Projections.bean(UserPostImageDTO.class,
                joinUser.name.as("userName"),
                joinPost.name.as("postName"),
                joinImage.name.as("imageName")))
            .from(joinUser)
            .leftJoin(joinImage).on(joinImage.user.eq(joinUser))
            .leftJoin(joinPost).on(joinPost.user.eq(joinUser))
            .where(joinUser.name.eq("joinUser1"))
            .fetch();

        result.forEach(System.out::println);

    }


    @Test
    @Order(5)
    void 양방향_JPA_단건_조회_테스트() {
        insertTwowayData();
        em.flush();
        em.clear();

        JoinMember findMember = joinMemberRepository.findByName("joinMember1").orElseThrow();

        MemberCodeCommitDTO2 memberCodeCommitDTO2 = new MemberCodeCommitDTO2();
        memberCodeCommitDTO2.setMemberName("joinMember1");
        memberCodeCommitDTO2.setCodeNames(
            findMember.getCodes().stream().map(JoinCode::getName).collect(Collectors.toList()));
        memberCodeCommitDTO2.setCommitNames(
            findMember.getCommits().stream().map(JoinCommit::getName).collect(Collectors.toList()));

        System.out.println(memberCodeCommitDTO2);
    }


    @Test
    @Order(6)
    void 양방향_JPA_다건_조회_테스트() {
        insertTwowayData();
        em.flush();
        em.clear();

        Iterable<JoinMember> allMember = joinMemberRepository.findAll();

        List<MemberCodeCommitDTO2> memberCodeCommitDTO2s = new ArrayList<>();

        allMember.forEach(member -> {
            MemberCodeCommitDTO2 memberCodeCommitDTO2 = new MemberCodeCommitDTO2();
            memberCodeCommitDTO2.setMemberName(member.getName());
            memberCodeCommitDTO2.setCodeNames(
                member.getCodes().stream().map(JoinCode::getName).collect(Collectors.toList()));
            memberCodeCommitDTO2.setCommitNames(
                member.getCommits().stream().map(JoinCommit::getName).collect(Collectors.toList()));
        });

        System.out.println(memberCodeCommitDTO2s);
    }


    public void insertOneWayData() {
        // 단방향
        JoinUser joinUser1 = new JoinUser();
        JoinUser joinUser2 = new JoinUser();

        JoinImage joinImage1 = new JoinImage();
        JoinImage joinImage2 = new JoinImage();
        JoinImage joinImage3 = new JoinImage();
        JoinImage joinImage4 = new JoinImage();
        JoinImage joinImage5 = new JoinImage();
        JoinImage joinImage6 = new JoinImage();

        JoinPost joinPost1 = new JoinPost();
        JoinPost joinPost2 = new JoinPost();
        JoinPost joinPost3 = new JoinPost();
        JoinPost joinPost4 = new JoinPost();
        JoinPost joinPost5 = new JoinPost();
        JoinPost joinPost6 = new JoinPost();

        joinUser1.setName("joinUser1");
        joinUser2.setName("joinUser2");

        joinImage1.setName("imageee");
        joinImage2.setName("imageee");
        joinImage3.setName("imageee");
        joinImage4.setName("imageee");
        joinImage5.setName("imageee");
        joinImage6.setName("imageee");

        joinImage1.setUser(joinUser1);
        joinImage2.setUser(joinUser1);
        joinImage3.setUser(joinUser1);
        joinImage4.setUser(joinUser2);
        joinImage5.setUser(joinUser2);
        joinImage6.setUser(joinUser2);

        joinPost1.setName("posttttt");
        joinPost2.setName("posttttt");
        joinPost3.setName("posttttt");
        joinPost4.setName("posttttt");
        joinPost5.setName("posttttt");
        joinPost6.setName("posttttt");

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

        joinImageRepository.save(joinImage1);
        joinImageRepository.save(joinImage2);
        joinImageRepository.save(joinImage3);
        joinImageRepository.save(joinImage4);
        joinImageRepository.save(joinImage5);
        joinImageRepository.save(joinImage6);
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

        joinCode1.setName("codeNameee");
        joinCode2.setName("codeNameee");
        joinCode3.setName("codeNameee");
        joinCode4.setName("codeNameee");
        joinCode5.setName("codeNameee");
        joinCode6.setName("codeNameee");

        joinCode1.setMember(joinMember1);
        joinCode2.setMember(joinMember1);
        joinCode3.setMember(joinMember1);
        joinCode4.setMember(joinMember2);
        joinCode5.setMember(joinMember2);
        joinCode6.setMember(joinMember2);

        joinCommit1.setName("commitnamee");
        joinCommit2.setName("commitnamee");
        joinCommit3.setName("commitnamee");
        joinCommit4.setName("commitnamee");
        joinCommit5.setName("commitnamee");
        joinCommit6.setName("commitnamee");

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


}

