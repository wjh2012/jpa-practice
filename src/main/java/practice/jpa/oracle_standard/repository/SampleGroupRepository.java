package practice.jpa.oracle_standard.repository;

import org.springframework.data.repository.CrudRepository;
import practice.jpa.oracle_standard.entity.twoway.SampleGroup;

public interface SampleGroupRepository extends CrudRepository<SampleGroup, Long> {

}
