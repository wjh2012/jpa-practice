package practice.jpa.basic.repository.country;

import org.springframework.data.repository.CrudRepository;
import practice.jpa.basic.entity.country.Country;

public interface CountryRepository extends CrudRepository<Country, Long> {

}
