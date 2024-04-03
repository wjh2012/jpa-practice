package practice.jpa.basic.entity.sample.repository;

import org.springframework.data.repository.CrudRepository;
import practice.jpa.basic.entity.sample.entity.oneway.SampleMember;

public interface SampleMemberRepository extends CrudRepository<SampleMember, Long> {
}
