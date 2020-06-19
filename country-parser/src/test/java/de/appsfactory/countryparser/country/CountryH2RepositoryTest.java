package de.appsfactory.countryparser.country;

import de.appsfactory.countryparser.CountryParserApplication;
import de.appsfactory.countryparser.util.DummyObjects;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = CountryParserApplication.class)
@RunWith(SpringRunner.class)
public class CountryH2RepositoryTest {

    @Autowired
    private CountryH2Repository countryH2Repository;

    @MockBean
    private CountryJpaRepository countryJpaRepository;

    @Before
    public void setup() {
        Assert.assertNotNull("countryH2Repository is not set", countryH2Repository);
        Assert.assertNotNull("countryJpaRepository is not set", countryJpaRepository);
        countryH2Repository.setCountryJpaRepository(countryJpaRepository);
    }

    @Test
    public void whenGetAllCountries_thenCountriesShouldBeFetched() {
        Country country = DummyObjects.getCountry();
        List<Country> countryList = Arrays.asList(country);
        doReturn(countryList).when(countryJpaRepository).findAll();

        List<Country> found = countryH2Repository.getAllCountries();
        assertNotNull(found);
        assertEquals(found.size(), countryList.size());
    }

    @Test
    public void whenGetCountry_thenCountryShouldBeFetched() {
        Country country = DummyObjects.getCountry();
        Mockito.when(countryJpaRepository.findById(anyLong()))
                .thenReturn(Optional.of(country));

        Optional<Country> found = countryH2Repository.getCountryById(country.getId());
        assertTrue(found.isPresent());
        assertEquals(found.get().getNameEN(), country.getNameEN());
    }

    @Test
    public void whenAddCountry_thenCountryShouldBeAdded() {
        Country country = DummyObjects.getCountry();
        doReturn(country).when(countryJpaRepository).saveAndFlush(any(Country.class));

        Country found = countryH2Repository.saveOrUpdateCountry(country);
        assertNotNull(found);
        assertThat(found.getNameEN()).isEqualTo(country.getNameEN());
    }

    @Test
    public void whenDeleteCountry_thenCountryShouldBeDeeleted() {
        doNothing().when(countryJpaRepository).deleteById(anyLong());
        countryH2Repository.deleteCountry(1L);
    }

    @Test
    public void whenSaveAllCountries_thenCountriesShouldBeAdded() {
        Country country = DummyObjects.getCountry();
        List<Country> countryList = Arrays.asList(country);
        doReturn(countryList).when(countryJpaRepository).saveAll(anyList());

        List<Country> found = countryH2Repository.saveAllCountries(countryList);
        assertNotNull(found);
        assertThat(found.size()).isEqualTo(countryList.size());
        assertThat(found.get(0).getNameEN()).isEqualTo(countryList.get(0).getNameEN());
    }

    @Test
    public void checkCountryExist_thenCountryStatusBeChecked() {
        final boolean isExists = true;
        Country country = DummyObjects.getCountry();
        doReturn(isExists).when(countryJpaRepository).existsById(anyLong());

        boolean exists = countryH2Repository.countryExistsById(country.getId());
        assertThat(exists).isEqualTo(isExists);
    }
}
