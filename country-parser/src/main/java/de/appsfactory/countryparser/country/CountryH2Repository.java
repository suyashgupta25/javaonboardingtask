package de.appsfactory.countryparser.country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CountryH2Repository implements CountryRepository {

    private CountryJpaRepository countryJpaRepository;

    @Autowired
    public void setCountryJpaRepository(CountryJpaRepository countryJpaRepository) {
        this.countryJpaRepository = countryJpaRepository;
    }

    @Override
    public List<Country> getAllCountries() {
        return countryJpaRepository.findAll();
    }

    @Override
    public Optional<Country> getCountryById(Long id) {
        return countryJpaRepository.findById(id);
    }

    @Override
    public Country saveOrUpdateCountry(Country country) {
        return countryJpaRepository.saveAndFlush(country);
    }

    @Override
    public void deleteCountry(Long id) {
        if(countryExistsById(id)) {
            countryJpaRepository.deleteById(id);
        }
    }

    @Override
    public List<Country> saveAllCountries(List<Country> countries) {
        return countryJpaRepository.saveAll(countries);
    }

    @Override
    public boolean countryExistsById(Long id) {
        return countryJpaRepository.existsById(id);
    }
}
