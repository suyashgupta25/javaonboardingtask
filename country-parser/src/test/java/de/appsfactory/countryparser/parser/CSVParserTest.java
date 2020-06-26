package de.appsfactory.countryparser.parser;

import de.appsfactory.countryparser.CountryParserApplication;
import de.appsfactory.countryparser.country.Country;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = CountryParserApplication.class)
@RunWith(SpringRunner.class)
public class CSVParserTest {

    @Autowired
    private CSVParser csvParser;

    @Test
    public void whenParseAllCountries_thenCSVShouldBeParsed() {
        List<Country> found = csvParser.getAllCountries();
        assertNotNull(found);
        assertEquals(found.size(), 248);
        assertNotNull(found.get(0).getNameEN());
    }

}
