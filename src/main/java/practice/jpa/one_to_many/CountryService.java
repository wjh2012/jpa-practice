package practice.jpa.one_to_many;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import practice.jpa.one_to_many.entity.City;
import practice.jpa.one_to_many.entity.Country;
import practice.jpa.one_to_many.entity.District;
import practice.jpa.one_to_many.entity.Town;
import practice.jpa.one_to_many.repository.CityRepository;
import practice.jpa.one_to_many.repository.CountryRepository;
import practice.jpa.one_to_many.repository.DistrictRepository;
import practice.jpa.one_to_many.repository.TownRepository;

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
