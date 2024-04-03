package practice.jpa.basic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import practice.jpa.basic.entity.country.City;
import practice.jpa.basic.entity.country.Country;
import practice.jpa.basic.entity.country.District;
import practice.jpa.basic.entity.country.Town;
import practice.jpa.basic.repository.country.CityRepository;
import practice.jpa.basic.repository.country.CountryRepository;
import practice.jpa.basic.repository.country.DistrictRepository;
import practice.jpa.basic.repository.country.TownRepository;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;
    private final DistrictRepository districtRepository;
    private final TownRepository townRepository;

    public void saveCountry(Country country) {
        countryRepository.save(country);
    }

    public void saveCity(City city) {
        cityRepository.save(city);
    }

    public void saveDistrict(District district) {
        districtRepository.save(district);
    }

    public void saveTown(Town town) {
        townRepository.save(town);
    }
}
