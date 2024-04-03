package practice.jpa.uuid;

import org.springframework.data.repository.CrudRepository;
import practice.jpa.uuid.UUIDMember;

import java.util.Optional;
import java.util.UUID;

public interface UUIDMemberRepository extends CrudRepository<UUIDMember, UUID> {

    Optional<UUIDMember> findByName(String name);
}
