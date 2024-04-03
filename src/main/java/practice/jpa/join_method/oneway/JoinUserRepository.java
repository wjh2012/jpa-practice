package practice.jpa.join_method.oneway;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface JoinUserRepository extends CrudRepository<JoinUser, Long> {

    Optional<JoinUser> findByName(String name);

}
