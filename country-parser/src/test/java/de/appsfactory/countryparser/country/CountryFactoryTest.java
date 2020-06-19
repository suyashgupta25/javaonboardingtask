package de.appsfactory.countryparser.country;

import de.appsfactory.countryparser.CountryParserApplication;
import de.appsfactory.countryparser.util.DummyObjects;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = CountryParserApplication.class)
@RunWith(SpringRunner.class)
public class CountryFactoryTest {

    @Autowired
    private CountryFactory countryFactory;

    @Before
    public void setUp() {
        Assert.assertNotNull("countryFactory is not set", countryFactory);
    }

    @Test
    public void whenConvertToCountryFromEntity_thenItShouldBeSuccess() {
        CountryEntity countryEntity = DummyObjects.getCountryEntity();
        Country found = countryFactory.toCountry(countryEntity);
        assertNotNull(found);
        assertEquals(found.getNameEN(), countryEntity.getNameEN());
    }

    @Test
    public void whenConvertToEntityFromCountry_thenItShouldBeSuccess() {
        Country country = DummyObjects.getCountry();
        CountryEntity found = countryFactory.fromCountry(country);
        assertNotNull(found);
        assertEquals(found.getNameEN(), country.getNameEN());
    }

    @Test
    public void whenConvertToOptionalCountryFromEntity_thenItShouldBeSuccess() {
        CountryEntity countryEntity = DummyObjects.getCountryEntity();
        Optional<Country> found = countryFactory.toOptionalCountry(Optional.of(countryEntity));
        assertNotNull(found);
        assertTrue(found.isPresent());
        assertEquals(found.get().getNameEN(), countryEntity.getNameEN());
    }
}
