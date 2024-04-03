package practice.jpa.jpaRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaJpaMemberRepository extends CrudRepository<JpaMember, Long>,
    JpaMemberRepositoryCustom {

    Optional<JpaMember> findByName(String name);
}
