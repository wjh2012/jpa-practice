package practice;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import practice.jpa.uuid.UUIDMember;
import practice.jpa.uuid.UUIDMemberRepository;
import practice.jpa.one_to_many.CountryService;
import practice.jpa.one_to_many.entity.City;
import practice.jpa.one_to_many.entity.Country;
import practice.jpa.one_to_many.entity.District;
import practice.jpa.one_to_many.entity.Town;
import practice.jpa.oracle_standard.entity.oneway.SampleMember;
import practice.jpa.oracle_standard.entity.oneway.SampleTeam;
import practice.jpa.oracle_standard.entity.twoway.SampleGroup;
import practice.jpa.oracle_standard.entity.twoway.SampleUser;
import practice.jpa.oracle_standard.repository.SampleGroupRepository;
import practice.jpa.oracle_standard.repository.SampleMemberRepository;
import practice.jpa.oracle_standard.repository.SampleTeamRepository;
import practice.jpa.oracle_standard.repository.SampleUserRepository;

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
