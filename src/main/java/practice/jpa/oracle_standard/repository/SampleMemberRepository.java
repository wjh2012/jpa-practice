package practice.jpa.oracle_standard.repository;

import org.springframework.data.repository.CrudRepository;
import practice.jpa.oracle_standard.entity.oneway.SampleMember;

public interface SampleMemberRepository extends CrudRepository<SampleMember, Long> {

}
