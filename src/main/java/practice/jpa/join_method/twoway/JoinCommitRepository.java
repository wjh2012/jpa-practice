package practice.jpa.join_method.twoway;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface JoinCommitRepository extends CrudRepository<JoinCommit, Long> {

    List<JoinCommit> findAllByMember(JoinMember member);
}
