package de.appsfactory.customerservice.invoice;

import de.appsfactory.customerservice.customer.Customer;
import de.appsfactory.customerservice.customer.CustomerRepository;
import de.appsfactory.customerservice.utils.NullAwareBeansUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class InvoiceServiceImpl implements InvoiceService {

    private final static Logger log = LoggerFactory.getLogger(InvoiceServiceImpl.class);

    private final NullAwareBeansUtil<Invoice> invoiceNullAwareBeansUtil;
    private final CustomerRepository customerRepository;
    private final InvoiceRepository invoiceRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, CustomerRepository customerRepository, NullAwareBeansUtil<Invoice> invoiceNullAwareBeansUtil) {
        this.invoiceRepository = invoiceRepository;
        this.customerRepository = customerRepository;
        this.invoiceNullAwareBeansUtil = invoiceNullAwareBeansUtil;
    }

    @Override
    public Invoice findInvoicesById(Long id) {
        Optional<Invoice> invoiceOptional = invoiceRepository.findById(id);
        if (invoiceOptional.isPresent()) {
            return invoiceOptional.get();
        } else {
            log.error("invoice not found:"+id);
            throw new IllegalStateException("invoice not found:"+id);
        }
    }

    @Override
    public void createInvoice(Long customerId, Invoice invoice) {
        if (customerId == null) {
            log.error("customer id not found");
            throw new IllegalArgumentException("customer id not found");
        }
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            invoice.setCustomer(customer);
            invoiceRepository.saveAndFlush(invoice);
        } else {
            log.error("customer not found:"+customerId);
            throw new IllegalStateException("customer not found:"+customerId);
        }
    }

    @Override
    public void updateInvoice(Invoice invoice) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(invoice.getId());
        if (optionalInvoice.isPresent()) {
            Invoice existingInvoice = optionalInvoice.get();
            invoiceRepository.saveAndFlush(invoiceNullAwareBeansUtil.copyNonNullProperties(existingInvoice, invoice));
        } else {
            log.error("invoice not found:"+invoice.toString());
            throw new IllegalStateException("customer not found:"+invoice.toString());
        }
    }

    @Override
    public void deleteInvoice(Long id) {
        if (id == null) {
            log.error("id not found");
            throw new IllegalArgumentException("id not found");
        }
        invoiceRepository.deleteById(id);
    }
}
