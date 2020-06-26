package de.appsfactory.countryparser.country;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @RequestMapping(value = "/country", method = RequestMethod.GET)
    public ResponseEntity<List<Country>> all() {
        log.debug("getting all Countries");
        return countryService.allCountries();
    }

    @RequestMapping(value = "/country/{id}", method = RequestMethod.GET)
    public ResponseEntity<Country> getCountry(@PathVariable @Valid Long id) {
        log.debug("get a Country");
        return countryService.getCountry(id);
    }

    @PostMapping("/country")
    public ResponseEntity<Country> createCountry(@RequestBody @Valid Country country) {
        log.debug("creating Country={}", country);
        return countryService.addCountry(country);
    }

    @PutMapping(value = "/country")
    public ResponseEntity<Country> updateCountry(@RequestBody @Valid Country country) {
        log.debug("updating Country={}", country);
        return countryService.updateCountry(country);
    }

    @DeleteMapping(value = "/country/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCountry(@PathVariable Long id) {
        log.debug("deleting Country");
        countryService.deleteCountry(id);
    }
}
