package practice.jpa.basic.entity.sample.repository;

import org.springframework.data.repository.CrudRepository;
import practice.jpa.basic.entity.sample.entity.twoway.SampleUser;

public interface SampleUserRepository extends CrudRepository<SampleUser, Long> {
}
