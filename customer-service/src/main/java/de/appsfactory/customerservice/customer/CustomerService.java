package de.appsfactory.customerservice.customer;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {

    ResponseEntity<Customer> findUserByEmail(String email);

    ResponseEntity<Customer> findCustomer(Long id);

    ResponseEntity<List<Customer>> allCustomers();

    ResponseEntity<Customer> createCustomer(Customer customer);

    ResponseEntity<Customer> updateCustomer(Customer customer);

    void deleteCustomer(Long id);
}
