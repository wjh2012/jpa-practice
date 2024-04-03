package practice.jpa.join_method.twoway;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface JoinMemberRepository extends CrudRepository<JoinMember, Long> {

    Optional<JoinMember> findByName(String name);
}
