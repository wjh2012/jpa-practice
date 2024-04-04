package practice.jpa.oracle_standard.repository;

import org.springframework.data.repository.CrudRepository;
import practice.jpa.oracle_standard.entity.oneway.SampleTeam;

public interface SampleTeamRepository extends CrudRepository<SampleTeam, Long> {

}
