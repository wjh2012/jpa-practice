package practice.jpa.join_method.twoway;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JoinCodeRepository extends CrudRepository<JoinCode, Long> {
    List<JoinCode> findAllByMember(JoinMember member);
}
