package practice.jpa.join_method.twoway;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface JoinCodeRepository extends CrudRepository<JoinCode, Long> {

    List<JoinCode> findAllByMember(JoinMember member);
}
