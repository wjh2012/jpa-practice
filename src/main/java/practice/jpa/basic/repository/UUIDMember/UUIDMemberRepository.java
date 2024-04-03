package practice.jpa.basic.repository.UUIDMember;

import org.springframework.data.repository.CrudRepository;
import practice.jpa.basic.entity.uuid.UUIDMember;

import java.util.Optional;
import java.util.UUID;

public interface UUIDMemberRepository extends CrudRepository<UUIDMember, UUID> {

    Optional<UUIDMember> findByName(String name);
}
