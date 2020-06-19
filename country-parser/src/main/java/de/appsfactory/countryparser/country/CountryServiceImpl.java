package de.appsfactory.countryparser.country;

import de.appsfactory.countryparser.error.exception.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    @Qualifier("countryRepository")
    private CountryRepository countryRepository;

    @Override
    public ResponseEntity<List<Country>> allCountries() {
        return ResponseEntity.ok(countryRepository.getAllCountries());
    }

    @Override
    public ResponseEntity<Country> getCountry(Long id) {
        Optional<Country> country = countryRepository.getCountryById(id);
        if (!country.isPresent()) {
            log.error("country not found for id=" + id);
            throw new EntityNotFoundException(Country.class, "id", Objects.toString(id));
        }
        return ResponseEntity.ok(country.get());
    }

    @Override
    public ResponseEntity<Country> addCountry(Country country) {
        Country savedCountry = countryRepository.saveOrUpdateCountry(country);
        return new ResponseEntity<>(savedCountry, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Country> updateCountry(Country country) {
        Long id = country.getId();
        if (id == null || !countryRepository.countryExistsById(id)) {
            log.error("customer not found={}", country);
            throw new EntityNotFoundException(Country.class, "id", Objects.toString(id));
        }
        Country updatedCountry = countryRepository.saveOrUpdateCountry(country);
        return ResponseEntity.ok(updatedCountry);
    }

    @Override
    public void deleteCountry(Long id) {
        if (!countryRepository.countryExistsById(id)) {
            log.error("id not found");
            throw new EntityNotFoundException(Country.class, "id", Objects.toString(id));
        }
        countryRepository.deleteCountry(id);
    }

    @Override
    public void saveAllCountries(List<Country> countries) {
        countryRepository.saveAllCountries(countries);
    }
}
