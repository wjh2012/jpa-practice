package practice.jpa.basic.repository.country;

import org.springframework.data.repository.CrudRepository;
import practice.jpa.basic.entity.country.City;

public interface CityRepository extends CrudRepository<City, Long> {

}
