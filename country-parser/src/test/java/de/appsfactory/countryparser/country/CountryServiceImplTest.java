package de.appsfactory.countryparser.country;

import de.appsfactory.countryparser.CountryParserApplication;
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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = CountryParserApplication.class)
@RunWith(SpringRunner.class)
public class CountryServiceImplTest {

    @Autowired
    private CountryService countryService;

    @MockBean
    @Qualifier("countryRepository")
    private CountryRepository countryRepository;

    @Before
    public void setUp() {
        Assert.assertNotNull("customerService is not set", countryService);
        Assert.assertNotNull("countryRepository is not set", countryRepository);
    }

    @Test
    public void whenGetAllCountries_thenCountriesShouldBeFetched() {
        Country country = DummyObjects.getCountry();
        List<Country> countryList = Arrays.asList(country);
        Mockito.when(countryRepository.getAllCountries())
                .thenReturn(countryList);

        ResponseEntity<List<Country>> found = countryService.allCountries();
        assertNotNull(found.getBody());
        assertEquals(found.getBody().size(), countryList.size());
    }

    @Test
    public void whenGetCountry_thenCountryShouldBeFetched() {
        Country country = DummyObjects.getCountry();
        Mockito.when(countryRepository.getCountryById(anyLong()))
                .thenReturn(Optional.of(country));

        ResponseEntity<Country> found = countryService.getCountry(country.getId());
        assertNotNull(found.getBody());
        assertEquals(found.getBody().getNameEN(), country.getNameEN());
    }

    @Test
    public void whenAddCountry_thenCountryShouldBeAdded() {
        Country country = DummyObjects.getCountry();
        doReturn(country).when(countryRepository).saveOrUpdateCountry(any(Country.class));

        ResponseEntity<Country> found = countryService.addCountry(country);
        assertNotNull(found.getBody());
        assertThat(found.getBody().getNameEN()).isEqualTo(country.getNameEN());
    }

    @Test
    public void whenUpdateCountry_thenCountryShouldBeUpdated() {
        Country country = DummyObjects.getCountry();
        doReturn(true).when(countryRepository).countryExistsById(anyLong());
        doReturn(country).when(countryRepository).saveOrUpdateCountry(any(Country.class));

        ResponseEntity<Country> found = countryService.updateCountry(country);
        assertNotNull(found.getBody());
        assertThat(found.getBody().getNameEN()).isEqualTo(country.getNameEN());
    }
}
