package de.appsfactory.countryparser.country;

import de.appsfactory.countryparser.util.DummyObjects;
import de.appsfactory.countryparser.util.JsonUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CountryController.class)
public class CountryControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CountryService service;

    @Before
    public void setUp() {
        Assert.assertNotNull("CountryService is not set", service);
    }

    @Test
    public void whenGetCountries_thenReturnJsonArray()
            throws Exception {
        Country country = DummyObjects.getCountry();
        List<Country> countries = Arrays.asList(country);
        given(service.allCountries()).willReturn(ResponseEntity.ok(countries));

        mvc.perform(get("/country")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nameEN", is(country.getNameEN())));
    }

    @Test
    public void whenGetCountry_thenReturnJson()
            throws Exception {
        Country country = DummyObjects.getCountry();
        given(service.getCountry(anyLong())).willReturn(ResponseEntity.ok(country));

        mvc.perform(get("/country/" + country.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nameEN", is(country.getNameEN())));
    }

    @Test
    public void whenAddCountry_thenReturnJson()
            throws Exception {
        Country country = DummyObjects.getCountry();
        given(service.addCountry(country)).willReturn(ResponseEntity.ok(country));

        mvc.perform(post("/country")
                .content(JsonUtil.toJson(country))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nameEN", is(country.getNameEN())));
    }

    @Test
    public void whenUpdateCountry_thenReturnJson()
            throws Exception {
        Country country = DummyObjects.getCountry();
        given(service.updateCountry(country)).willReturn(ResponseEntity.ok(country));

        mvc.perform(put("/country")
                .content(JsonUtil.toJson(country))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nameEN", is(country.getNameEN())));
    }

    @Test
    public void whenDeleteCountry_returnSuccess()
            throws Exception {
        doNothing().when(service).deleteCountry(anyLong());

        mvc.perform(delete("/country/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
