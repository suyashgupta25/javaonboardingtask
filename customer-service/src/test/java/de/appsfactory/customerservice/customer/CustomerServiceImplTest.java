package de.appsfactory.customerservice.customer;

import de.appsfactory.customerservice.util.DummyObjects;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static de.appsfactory.customerservice.util.DummyObjects.CUSTOMER_EMAIL;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class CustomerServiceImplTest {

    @TestConfiguration
    static class CustomerServiceImplTestContextConfiguration {

        @MockBean
        private CustomerRepository repository;

        @Bean
        public CustomerService employeeService() {
            customerRepository = repository;
            return new CustomerServiceImpl(customerRepository);
        }
    }

    @Autowired
    private CustomerService customerService;
    private static CustomerRepository customerRepository;

    @Test
    public void whenValidEmail_thenCustomerShouldBeFound() {
        Customer customer = DummyObjects.getCustomer();
        Mockito.when(customerRepository.findByEmail(customer.getEmail()))
                .thenReturn(customer);

        ResponseEntity<Customer> found = customerService.findUserByEmail(CUSTOMER_EMAIL);
        assertThat(found.getBody().getEmail()).isEqualTo(CUSTOMER_EMAIL);
    }

    @AfterAll
    static void clear() {
        customerRepository = null;
    }
}
