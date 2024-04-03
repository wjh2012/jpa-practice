package practice.jpa.basic.entity.sample.repository;

import org.springframework.data.repository.CrudRepository;
import practice.jpa.basic.entity.sample.entity.twoway.SampleGroup;

public interface SampleGroupRepository extends CrudRepository<SampleGroup, Long> {
}
