package de.appsfactory.customerservice.integration;

import de.appsfactory.customerservice.CustomerServiceApplication;
import de.appsfactory.customerservice.customer.Customer;
import de.appsfactory.customerservice.customer.CustomerRepository;
import de.appsfactory.customerservice.util.DummyObjects;
import de.appsfactory.customerservice.util.JsonUtil;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static de.appsfactory.customerservice.util.DummyObjects.CUSTOMER_EMAIL;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = CustomerServiceApplication.class)
@AutoConfigureMockMvc
public class CustomerControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CustomerRepository repository;

    @Test
    public void whenGetCustomers_thenStatus200() throws Exception {
        Customer testCustomer = createTestCustomer();

        mvc.perform(get("/customer").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))))
                .andExpect(jsonPath("$[0].email", is(testCustomer.getEmail())));
    }

    @Test
    public void whenCreateCustomers_thenStatus201() throws Exception {
        Customer customer = DummyObjects.getCustomer();

        mvc.perform(post("/customer").content(JsonUtil.toJson(customer)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email", is(customer.getEmail())));
    }

    @Test
    public void whenUpdateCustomers_thenStatus200() throws Exception {
        Customer testCustomer = createTestCustomer();

        mvc.perform(put("/customer").content(JsonUtil.toJson(testCustomer)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email", is(testCustomer.getEmail())));
    }

    @Test
    public void whenDeleteCustomer_thenStatus200() throws Exception {
        Customer testCustomer = createTestCustomer();

        mvc.perform(delete("/customer/"+testCustomer.getId()).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    private Customer createTestCustomer() {
        Customer customer = DummyObjects.getCustomer();
        return repository.saveAndFlush(customer);
    }

    @After
    public void resetDb() {
        repository.deleteAll();
    }
}
