package de.appsfactory.countryparser.country;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.config.AbstractFactoryBean;

public class CountryRepoFactory extends AbstractFactoryBean<CountryRepository> {

    @Getter
    @Setter
    private CountryRepository countryRepository;

    @Override
    public Class<?> getObjectType() {
        return CountryRepository.class;
    }

    @Override
    protected CountryRepository createInstance() {
        return countryRepository;
    }
}
