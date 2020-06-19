package de.appsfactory.countryparser.country;

import java.util.Optional;

public interface CountryFactory {

    Country toCountry(CountryEntity entity);

    CountryEntity fromCountry(Country country);

    Optional<Country> toOptionalCountry(Optional<CountryEntity> entity);
}
