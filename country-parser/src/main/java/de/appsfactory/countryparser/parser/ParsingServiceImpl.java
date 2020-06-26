package de.appsfactory.countryparser.parser;

import de.appsfactory.countryparser.country.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParsingServiceImpl implements ParsingService {

    @Autowired
    @Qualifier("parser")
    private Parser parser;

    @Override
    public List<Country> getAllCountries() {
        return parser.getAllCountries();
    }
}
