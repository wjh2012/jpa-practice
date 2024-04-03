package practice.jpa.oracle_standard.repository;

import org.springframework.data.repository.CrudRepository;
import practice.jpa.oracle_standard.entity.twoway.SampleUser;

public interface SampleUserRepository extends CrudRepository<SampleUser, Long> {
}
