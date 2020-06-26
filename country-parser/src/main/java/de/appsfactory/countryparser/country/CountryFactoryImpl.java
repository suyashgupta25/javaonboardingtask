package de.appsfactory.countryparser.country;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class CountryFactoryImpl implements CountryFactory {

    @Override
    public Country toCountry(CountryEntity entity) {
        Country country = null;
        if (entity != null) {
            country = Country.builder().id(entity.getId())
                    .nameEN(entity.getNameEN())
                    .nameDE(entity.getNameDE())
                    .countryCode(entity.getCountryCode())
                    .latitude(entity.getLatitude())
                    .longitude(entity.getLongitude())
                    .altNameDE(entity.getAltNameDE())
                    .numericISO3166(entity.getNumericISO3166())
                    .alpha2ISO3166(entity.getAlpha2ISO3166())
                    .alpha3ISO3166(entity.getAlpha3ISO3166())
                    .fips10(entity.getFips10())
                    .stagnag1059(entity.getStagnag1059())
                    .domain(entity.getDomain())
                    .ioc(entity.getIoc())
                    .licensePlate(entity.getLicensePlate())
                    .unLocode(entity.getUnLocode())
                    .active(entity.getActive())
                    .build();
        }
        return country;
    }

    @Override
    public CountryEntity fromCountry(Country country) {
        CountryEntity countryEntity = null;
        if (country != null) {
            countryEntity = CountryEntity.builder().id(country.getId())
                    .nameEN(country.getNameEN())
                    .nameDE(country.getNameDE())
                    .countryCode(country.getCountryCode())
                    .latitude(country.getLatitude())
                    .longitude(country.getLongitude())
                    .altNameDE(country.getAltNameDE())
                    .numericISO3166(country.getNumericISO3166())
                    .alpha2ISO3166(country.getAlpha2ISO3166())
                    .alpha3ISO3166(country.getAlpha3ISO3166())
                    .fips10(country.getFips10())
                    .stagnag1059(country.getStagnag1059())
                    .domain(country.getDomain())
                    .ioc(country.getIoc())
                    .licensePlate(country.getLicensePlate())
                    .unLocode(country.getUnLocode())
                    .active(country.getActive())
                    .build();
        }
        return countryEntity;
    }

    @Override
    public Optional<Country> toOptionalCountry(Optional<CountryEntity> entity) {
        Country country;
        if (entity != null && entity.isPresent()) {
            country = toCountry(entity.get());
            return Optional.of(country);
        } else {
            return Optional.empty();
        }
    }
}
