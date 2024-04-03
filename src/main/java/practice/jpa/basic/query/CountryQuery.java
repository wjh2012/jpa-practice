package practice.jpa.basic.query;


import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import practice.jpa.basic.entity.country.City;
import practice.jpa.basic.entity.country.Country;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

import static practice.jpa.basic.entity.country.QCity.city;
import static practice.jpa.basic.entity.country.QCountry.country;

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
