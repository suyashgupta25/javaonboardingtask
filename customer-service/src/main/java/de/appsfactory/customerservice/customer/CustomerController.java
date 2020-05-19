package de.appsfactory.customerservice.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    private final static Logger log = LoggerFactory.getLogger(CustomerController.class);

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
    public String createCustomer(@RequestBody Customer customer){
        log.debug("creating Customer="+customer.toString());
        customerService.createCustomer(customer);
        return "Created successfully";
    }

    @RequestMapping(value = "/customer", method = RequestMethod.PUT)
    public String updateCustomer(@RequestBody Customer customer){
        log.debug("updating Customer="+customer.toString());
        customerService.updateCustomer(customer);
        return "Updated successfully";
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
    public String deleteCustomer(@PathVariable Long id){
        log.debug("deleting Customer");
        customerService.deleteCustomer(id);
        return "Deleted successfully";
    }

}
