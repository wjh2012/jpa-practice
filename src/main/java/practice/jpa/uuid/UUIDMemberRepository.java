package practice.jpa.uuid;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface UUIDMemberRepository extends CrudRepository<UUIDMember, UUID> {

    Optional<UUIDMember> findByName(String name);
}
