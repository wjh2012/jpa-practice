package practice.jpa.basic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import practice.jpa.basic.query.CountryQuery;

@RestController("country")
@RequiredArgsConstructor
public class CountryController {

    private final CountryQuery countryQuery;

    @GetMapping("/test")
    public void createMember() {
        countryQuery.test();
    }
}
