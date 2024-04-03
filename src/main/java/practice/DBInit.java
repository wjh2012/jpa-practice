package practice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import practice.jpa.basic.entity.country.City;
import practice.jpa.basic.entity.country.Country;
import practice.jpa.basic.entity.country.District;
import practice.jpa.basic.entity.country.Town;
import practice.jpa.basic.entity.sample.entity.oneway.SampleMember;
import practice.jpa.basic.entity.sample.entity.oneway.SampleTeam;
import practice.jpa.basic.entity.sample.entity.twoway.SampleGroup;
import practice.jpa.basic.entity.sample.entity.twoway.SampleUser;
import practice.jpa.basic.entity.sample.repository.SampleGroupRepository;
import practice.jpa.basic.entity.sample.repository.SampleMemberRepository;
import practice.jpa.basic.entity.sample.repository.SampleTeamRepository;
import practice.jpa.basic.entity.sample.repository.SampleUserRepository;
import practice.jpa.basic.entity.uuid.UUIDMember;
import practice.jpa.basic.repository.UUIDMember.UUIDMemberRepository;
import practice.jpa.basic.service.CountryService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DBInit {

    private final CountryService countryService;
    private final UUIDMemberRepository UUIDMemberRepository;
    private final SampleMemberRepository sampleMemberRepository;
    private final SampleTeamRepository sampleTeamRepository;
    private final SampleUserRepository sampleUserRepository;
    private final SampleGroupRepository sampleGroupRepository;


    @PostConstruct
    public void init() {
//        saveSampleCountry();
        sampleTest();
    }

    private void sampleTest() {
        SampleMember sampleMember = SampleMember.builder().build();
        SampleTeam sampleTeam = SampleTeam.builder().build();
        SampleUser sampleUser = SampleUser.builder().build();
        SampleGroup sampleGroup = SampleGroup.builder().build();

        sampleGroupRepository.save(sampleGroup);
        sampleUserRepository.save(sampleUser);
        sampleTeamRepository.save(sampleTeam);
        sampleMemberRepository.save(sampleMember);

        SampleUser sampleUser2 = SampleUser.builder().build();
        sampleUser2.setSampleGroup(sampleGroup);
        sampleUserRepository.save(sampleUser2);

        SampleMember sampleMember2 = SampleMember.builder().build();
        sampleMember2.setSampleTeam(sampleTeam);
        sampleMemberRepository.save(sampleMember2);
    }

    private void saveSampleCountry() {

        List<Town> towns = new ArrayList<>();
        List<District> districts = new ArrayList<>();
        List<City> cities = new ArrayList<>();

        for (int i = 0; i < 84; i++) {
            Town t = new Town();
            t.setName("Town" + (i + 1));
            towns.add(t);
            countryService.saveTown(t);
        }

        for (int i = 0; i < 27; i++) {
            District t = new District();
            t.setName("District" + (i + 1));
            t.setTowns(towns.subList(i * 3, i * 3 + 3));
            districts.add(t);
            countryService.saveDistrict(t);
        }

        for (int i = 0; i < 9; i++) {
            City t = new City();
            t.setName("City" + (i + 1));
            t.setDistricts(districts.subList(i * 3, i * 3 + 3));
            cities.add(t);
            countryService.saveCity(t);
        }

        for (int i = 0; i < 3; i++) {
            Country t = new Country();
            t.setName("Country" + (i + 1));
            t.setCities(cities.subList(i * 3, i * 3 + 3));
            countryService.saveCountry(t);
        }

        UUIDMember UUIDMember = new UUIDMember();
        UUIDMemberRepository.save(UUIDMember);
    }
}
