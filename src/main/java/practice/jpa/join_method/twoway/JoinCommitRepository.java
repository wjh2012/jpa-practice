package practice.jpa.join_method.twoway;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JoinCommitRepository extends CrudRepository<JoinCommit, Long> {
    List<JoinCommit> findAllByMember(JoinMember member);
}
