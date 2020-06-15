package de.appsfactory.customerservice.customer;

import de.appsfactory.customerservice.util.DummyObjects;
import de.appsfactory.customerservice.util.JsonUtil;
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
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerService service;

    @Test
    public void whenGetCustomers_thenReturnJsonArray()
            throws Exception {
        Customer customer = DummyObjects.getCustomer();
        List<Customer> customers = Arrays.asList(customer);
        given(service.allCustomers()).willReturn(ResponseEntity.ok(customers));

        mvc.perform(get("/customer")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].email", is(customer.getEmail())));
    }

    @Test
    public void whenCreateCustomer_thenReturnJson()
            throws Exception {
        Customer customer = DummyObjects.getCustomer();
        given(service.createCustomer(customer)).willReturn(ResponseEntity.ok(customer));

        mvc.perform(post("/customer")
                .content(JsonUtil.toJson(customer))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is(customer.getEmail())));
    }

    @Test
    public void whenUpdateCustomer_thenReturnJson()
            throws Exception {
        Customer customer = DummyObjects.getCustomer();
        given(service.updateCustomer(customer)).willReturn(ResponseEntity.ok(customer));

        mvc.perform(put("/customer")
                .content(JsonUtil.toJson(customer))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is(customer.getEmail())));
    }

    @Test
    public void whenDeleteCustomer_returnSuccess()
            throws Exception {
        doNothing().when(service).deleteCustomer(anyLong());

        mvc.perform(delete("/customer/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
