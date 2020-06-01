package de.appsfactory.customerservice.customer;

import de.appsfactory.customerservice.error.exception.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public List<Customer> all() {
        log.debug("getting all Customers");
        return customerService.allCustomers();
    }

    @PostMapping("/customer")
    public Customer createCustomer(@RequestBody @Valid Customer customer) {
        log.debug("creating Customer={}" + customer);
        return customerService.createCustomer(customer);
    }

    @RequestMapping(value = "/customer", method = RequestMethod.PUT)
    public Customer updateCustomer(@RequestBody @Valid Customer customer) throws EntityNotFoundException {
        log.debug("updating Customer={}" + customer);
        return customerService.updateCustomer(customer);
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
    public void deleteCustomer(@PathVariable Long id) {
        log.debug("deleting Customer");
        customerService.deleteCustomer(id);
    }

}
