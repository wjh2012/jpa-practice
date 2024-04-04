package practice.jpa.jpaRepository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaJpaMemberRepository extends CrudRepository<JpaMember, Long>,
    JpaMemberRepositoryCustom {

    Optional<JpaMember> findByName(String name);
}
