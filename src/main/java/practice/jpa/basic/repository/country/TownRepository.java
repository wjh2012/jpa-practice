package practice.jpa.basic.repository.country;

import org.springframework.data.repository.CrudRepository;
import practice.jpa.basic.entity.country.Town;

public interface TownRepository extends CrudRepository<Town, Long> {

}
