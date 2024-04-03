package practice.jpa.join_method.oneway;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface JoinPostRepository extends CrudRepository<JoinPost, Long> {

    List<JoinPost> findAllByUser(JoinUser user);
}
