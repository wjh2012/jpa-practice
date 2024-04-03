package practice.jpa.basic.entity.sample.repository;

import org.springframework.data.repository.CrudRepository;
import practice.jpa.basic.entity.sample.entity.oneway.SampleTeam;

public interface SampleTeamRepository extends CrudRepository<SampleTeam, Long> {
}
