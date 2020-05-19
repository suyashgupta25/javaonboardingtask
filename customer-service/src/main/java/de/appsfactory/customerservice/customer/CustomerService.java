package de.appsfactory.customerservice.customer;

import java.util.List;

public interface CustomerService {

    Customer findUserByEmail(String email);

    Customer findCustomer(Long id);

    List<Customer> allCustomers();

    void createCustomer(Customer customer);

    void updateCustomer(Customer customer);

    void deleteCustomer(Long id);
}
