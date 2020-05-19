package de.appsfactory.customerservice.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomerServiceImpl implements CustomerService {

    private final static Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer findUserByEmail(String email) {
        Optional<Customer> user = Optional.ofNullable(customerRepository.findByEmail(email));
        return user.orElse(null);
    }

    @Override
    public Customer findCustomer(Long id) {
        return customerRepository.getOne(id);
    }

    @Override
    public List<Customer> allCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public void createCustomer(Customer customer) {
        customerRepository.saveAndFlush(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        if (customer.getId() == null) {
            log.error("customer id not found="+customer.toString());
            throw new IllegalArgumentException("customer id not found");
        }
        customerRepository.saveAndFlush(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        if (id == null) {
            log.error("id not found");
            throw new IllegalArgumentException("id not found");
        }
        customerRepository.deleteById(id);
    }
}
