package de.appsfactory.countryparser.integration;

import de.appsfactory.countryparser.CountryParserApplication;
import de.appsfactory.countryparser.country.Country;
import de.appsfactory.countryparser.country.CountryRepository;
import de.appsfactory.countryparser.util.DummyObjects;
import de.appsfactory.countryparser.util.JsonUtil;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = CountryParserApplication.class)
@AutoConfigureMockMvc
public class CountryControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    @Qualifier("countryRepository")
    private CountryRepository countryRepository;

    @Test
    public void whenGetAllCountries_thenStatus200() throws Exception {
        createTestCountry();

        mvc.perform(get("/country").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))));
    }

    @Test
    public void whenCreateCountry_thenStatus201() throws Exception {
        Country country = DummyObjects.getCountry();

        mvc.perform(post("/country").content(JsonUtil.toJson(country)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nameEN", is(country.getNameEN())));
    }

    @Test
    public void whenUpdateCountry_thenStatus200() throws Exception {
        Country testCountry = createTestCountry();

        mvc.perform(put("/country").content(JsonUtil.toJson(testCountry)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nameEN", is(testCountry.getNameEN())));
    }

    @Test
    public void whenDeleteCountry_thenStatus200() throws Exception {
        Country testCountry = createTestCountry();

        mvc.perform(delete("/country/" + testCountry.getId()).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    private Country createTestCountry() {
        Country country = DummyObjects.getCountry();
        return countryRepository.saveOrUpdateCountry(country);
    }

    @After
    public void resetDb() {
        Country country = DummyObjects.getCountry();
        countryRepository.deleteCountry(country.getId());
    }
}
