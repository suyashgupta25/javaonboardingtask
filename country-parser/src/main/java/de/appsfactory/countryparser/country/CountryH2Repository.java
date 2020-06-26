package de.appsfactory.countryparser.country;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
public class CountryH2Repository implements CountryRepository {

    private CountryJpaRepository countryJpaRepository;
    private CountryFactory countryFactory;

    @Override
    public List<Country> getAllCountries() {
        List<CountryEntity> all = countryJpaRepository.findAll();
        log.debug("getAllCountries: {}", all);
        return all.stream()
                .map(countryEntity -> countryFactory.toCountry(countryEntity))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Country> getCountryById(Long id) {
        Optional<CountryEntity> byId = countryJpaRepository.findById(id);
        log.debug("getCountryById: {}", byId);
        return countryFactory.toOptionalCountry(byId);
    }

    @Override
    public Country saveOrUpdateCountry(Country country) {
        CountryEntity countryEntity = countryFactory.fromCountry(country);
        log.debug("saveOrUpdateCountry countryEntity={}", countryEntity);
        CountryEntity savedEntity = countryJpaRepository.saveAndFlush(countryEntity);
        log.debug("saveOrUpdateCountry savedEntity={}", savedEntity);
        return countryFactory.toCountry(savedEntity);
    }

    @Override
    public void deleteCountry(Long id) {
        if (countryExistsById(id)) {
            countryJpaRepository.deleteById(id);
        }
    }

    @Override
    public List<Country> saveAllCountries(List<Country> countries) {
        List<CountryEntity> collect = countries.stream()
                .map(country -> countryFactory.fromCountry(country))
                .collect(Collectors.toList());
        log.debug("saveAllCountries collect={}", collect);
        List<CountryEntity> countryEntities = countryJpaRepository.saveAll(collect);
        log.debug("saveAllCountries countryEntities={}", countryEntities);
        return countryEntities.stream()
                .map(countryEntity -> countryFactory.toCountry(countryEntity))
                .collect(Collectors.toList());
    }

    @Override
    public boolean countryExistsById(Long id) {
        return countryJpaRepository.existsById(id);
    }

    @Autowired
    public void setCountryJpaRepository(CountryJpaRepository countryJpaRepository) {
        this.countryJpaRepository = countryJpaRepository;
    }

    @Autowired
    public void setCountryFactory(CountryFactoryImpl countryFactory) {
        this.countryFactory = countryFactory;
    }
}
