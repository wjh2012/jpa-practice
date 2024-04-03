package practice.jpa.one_to_many.repository;


import org.springframework.data.repository.CrudRepository;
import practice.jpa.one_to_many.entity.City;

public interface CityRepository extends CrudRepository<City, Long> {

}
