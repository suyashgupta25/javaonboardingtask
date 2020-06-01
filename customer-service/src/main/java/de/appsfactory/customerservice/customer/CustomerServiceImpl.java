package de.appsfactory.customerservice.customer;

import de.appsfactory.customerservice.error.exception.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer findUserByEmail(String email) {
        Optional<Customer> customer = Optional.ofNullable(customerRepository.findByEmail(email));
        if (customer.isPresent()) {
            return customer.get();
        } else {
            throw new EntityNotFoundException(Customer.class, "email", email);
        }
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
    public Customer createCustomer(Customer customer) {
        return customerRepository.saveAndFlush(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        if (customer.getId() == null) {
            log.error("customer id not found={}"+customer);
            throw new EntityNotFoundException(Customer.class, "id", "null");
        }
        Customer updatedCustomer = customerRepository.saveAndFlush(customer);
        if (updatedCustomer == null) {
            log.error("customer not found for id="+customer.getId());
            throw new EntityNotFoundException(Customer.class, "id", customer.getId());
        }
        return updatedCustomer;
    }

    @Override
    public void deleteCustomer(Long id) {
        if (id == null) {
            log.error("id not found");
            throw new EntityNotFoundException(Customer.class, "id", "null");
        }
        customerRepository.deleteById(id);
    }
}
