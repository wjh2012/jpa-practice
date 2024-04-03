package practice.jpa.one_to_many.repository;


import org.springframework.data.repository.CrudRepository;
import practice.jpa.one_to_many.entity.District;

public interface DistrictRepository extends CrudRepository<District, Long> {

}
