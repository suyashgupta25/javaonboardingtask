package de.appsfactory.customerservice.customer;

import de.appsfactory.customerservice.error.exception.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public ResponseEntity<Customer> findUserByEmail(String email) {
        Optional<Customer> customer = Optional.ofNullable(customerRepository.findByEmail(email));
        if (customer.isPresent()) {
            return ResponseEntity.ok(customer.get());
        } else {
            throw new EntityNotFoundException(Customer.class, "email", email);
        }
    }

    @Override
    public ResponseEntity<Customer> findCustomer(Long id) {
        return ResponseEntity.ok(customerRepository.getOne(id));
    }

    @Override
    public ResponseEntity<List<Customer>> allCustomers() {
        return ResponseEntity.ok(customerRepository.findAll());
    }

    @Override
    public ResponseEntity<Customer> createCustomer(Customer customer) {
        Customer savedCustomer = customerRepository.saveAndFlush(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Customer> updateCustomer(Customer customer) {
        Long id = customer.getId();
        if (id == null || !customerRepository.existsById(id)) {
            log.error("customer not found={}", customer);
            throw new EntityNotFoundException(Customer.class, "id", Objects.toString(id));
        }
        Customer updatedCustomer = customerRepository.saveAndFlush(customer);
        return ResponseEntity.ok(updatedCustomer);
    }

    @Override
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            log.error("id not found");
            throw new EntityNotFoundException(Customer.class, "id", Objects.toString(id));
        }
        customerRepository.deleteById(id);
    }
}
