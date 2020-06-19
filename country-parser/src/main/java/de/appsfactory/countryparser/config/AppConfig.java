package de.appsfactory.countryparser.config;

import de.appsfactory.countryparser.country.CountryH2Repository;
import de.appsfactory.countryparser.country.CountryRepoFactory;
import de.appsfactory.countryparser.country.CountryRepository;
import de.appsfactory.countryparser.parser.CSVParser;
import de.appsfactory.countryparser.parser.Parser;
import de.appsfactory.countryparser.parser.ParserFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean(name = "countryFactory")
    public CountryRepoFactory countryFactory() {
        CountryRepoFactory factory = new CountryRepoFactory();
        factory.setCountryRepository(new CountryH2Repository());
        return factory;
    }

    @Bean(name = "countryRepository")
    public CountryRepository countryRepo() throws Exception {
        return countryFactory().getObject();
    }

    @Bean(name = "parserFactory")
    public ParserFactory parserFactory() {
        ParserFactory factory = new ParserFactory();
        factory.setParser(new CSVParser());
        return factory;
    }

    @Bean(name = "parser")
    public Parser parserRepo() throws Exception {
        return parserFactory().getObject();
    }
}