package de.appsfactory.countryparser.country;

import java.util.List;
import java.util.Optional;

public interface CountryRepository {

    List<Country> getAllCountries();

    Optional<Country> getCountryById(Long id);

    Country saveOrUpdateCountry(Country country);

    void deleteCountry(Long id);

    List<Country> saveAllCountries(List<Country> countries);

    boolean countryExistsById(Long id);
}
