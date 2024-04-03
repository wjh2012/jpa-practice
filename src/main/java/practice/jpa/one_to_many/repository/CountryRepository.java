package practice.jpa.one_to_many.repository;


import org.springframework.data.repository.CrudRepository;
import practice.jpa.one_to_many.entity.Country;

public interface CountryRepository extends CrudRepository<Country, Long> {

}
