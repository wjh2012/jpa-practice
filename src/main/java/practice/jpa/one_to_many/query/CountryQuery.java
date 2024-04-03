package practice.jpa.one_to_many.query;


import static practice.jpa.one_to_many.entity.QCity.city;
import static practice.jpa.one_to_many.entity.QCountry.country;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import practice.jpa.one_to_many.entity.City;
import practice.jpa.one_to_many.entity.Country;

@Repository
public class CountryQuery {

    @PersistenceContext
    private EntityManager em;

    public void test() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        List<Country> countries = queryFactory
            .select(country)
            .from(country)
            .fetch();

        List<List<City>> collect = countries.stream().map(Country::getCities)
            .collect(Collectors.toList());
        collect.forEach(System.out::println);

        System.out.println("hello test");
    }

    public void test2() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        List<Country> fetch = queryFactory
            .select(country)
            .from(country)
            .join(country.cities, city)
            .fetch();

        System.out.println("hello test");
    }
}
