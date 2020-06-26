package de.appsfactory.countryparser.country;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CountryService {
    ResponseEntity<List<Country>> allCountries();

    ResponseEntity<Country> getCountry(Long id);

    ResponseEntity<Country> addCountry(Country country);

    ResponseEntity<Country> updateCountry(Country country);

    void deleteCountry(Long id);

    void saveAllCountries(List<Country> countries);
}
