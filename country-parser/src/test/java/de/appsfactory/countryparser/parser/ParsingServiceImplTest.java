package de.appsfactory.countryparser.parser;

import de.appsfactory.countryparser.CountryParserApplication;
import de.appsfactory.countryparser.country.Country;
import de.appsfactory.countryparser.util.DummyObjects;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = CountryParserApplication.class)
@RunWith(SpringRunner.class)
public class ParsingServiceImplTest {

    @Autowired
    private ParsingService parsingService;

    @MockBean
    @Qualifier("parser")
    private Parser parser;

    @Before
    public void setUp() {
        Assert.assertNotNull("parser is not set", parser);
        Assert.assertNotNull("parsingService is not set", parsingService);
    }

    @Test
    public void whenGetAllCountries_thenCountriesShouldBeParsed() {
        Country country = DummyObjects.getCountry();
        List<Country> countryList = Arrays.asList(country);
        Mockito.when(parser.getAllCountries())
                .thenReturn(countryList);

        List<Country> found = parsingService.getAllCountries();
        assertNotNull(found);
        assertEquals(found.size(), countryList.size());
    }
}
