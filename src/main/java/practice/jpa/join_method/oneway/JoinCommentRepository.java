package practice.jpa.join_method.oneway;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface JoinCommentRepository extends CrudRepository<JoinComment, Long> {

    List<JoinComment> findAllByUser(JoinUser user);
}
