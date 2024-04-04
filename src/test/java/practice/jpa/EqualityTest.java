package practice.jpa;

import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import practice.jpa.jpaRepository.JpaJpaMemberRepository;
import practice.jpa.jpaRepository.JpaMember;
import practice.jpa.uuid.UUIDMember;
import practice.jpa.uuid.UUIDMemberRepository;

@DisplayName("JPA 엔티티 동등성 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SpringBootTest
public class EqualityTest {

    @Autowired
    JpaJpaMemberRepository jpaMemberRepository;

    @Autowired
    UUIDMemberRepository uuidMemberRepository;

    @Test
    void 저장_조회_시_엔티티_불일치() {
        // given
        final String name = "testMember";

        JpaMember jpaMember = new JpaMember();
        jpaMember.setName("testMember");
        jpaMemberRepository.save(jpaMember);

        // when
        Optional<JpaMember> findMemberOptional = jpaMemberRepository.findByName(name);
        JpaMember findJpaMember = findMemberOptional.orElseThrow();

        // then
        Assertions.assertThat(findJpaMember).isNotEqualTo(jpaMember);
    }

    @Test
    @Transactional
    void Transactional_저장_조회_시_엔티티_일치() {
        // given
        final String name = "testMember2";

        JpaMember jpaMember = new JpaMember();
        jpaMember.setName("testMember2");
        jpaMemberRepository.save(jpaMember);

        // when
        Optional<JpaMember> findMemberOptional = jpaMemberRepository.findByName(name);
        JpaMember findJpaMember = findMemberOptional.orElseThrow();

        // then
        Assertions.assertThat(findJpaMember).isEqualTo(jpaMember);
    }


    @Test
    void Transactional_EqualsAndHashCode_저장_조회_시_엔티티_일치() {
        // given
        final String username = "uuidMember1";

        UUIDMember uuidMember = new UUIDMember();
        uuidMember.setName(username);

        uuidMemberRepository.save(uuidMember);

        // when
        Optional<UUIDMember> findMemberOptional = uuidMemberRepository.findByName(username);
        UUIDMember findMember = findMemberOptional.orElseThrow();

        // then
        Assertions.assertThat(findMember).isEqualTo(uuidMember);
    }
}
