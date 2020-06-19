package de.appsfactory.countryparser.events;

import de.appsfactory.countryparser.country.Country;
import de.appsfactory.countryparser.country.CountryService;
import de.appsfactory.countryparser.parser.ParsingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AppStartupEvent {

    private final CountryService countryService;
    private final ParsingService parsingService;

    @Autowired
    public AppStartupEvent(ParsingService parsingService, CountryService countryService) {
        this.parsingService = parsingService;
        this.countryService = countryService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void parseCSVAndCreateDBAfterStartup() {
        List<Country> countries = parsingService.getAllCountries();
        countryService.saveAllCountries(countries);
    }
}
