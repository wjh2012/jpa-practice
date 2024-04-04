package practice.jpa.join_method.oneway;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface JoinImageRepository extends CrudRepository<JoinImage, Long> {

    List<JoinImage> findAllByUser(JoinUser user);

}
