package practice.jpa.basic.repository.country;

import org.springframework.data.repository.CrudRepository;
import practice.jpa.basic.entity.country.District;

public interface DistrictRepository extends CrudRepository<District, Long> {

}
