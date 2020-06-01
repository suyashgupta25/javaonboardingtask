package de.appsfactory.customerservice.invoice;

import de.appsfactory.customerservice.customer.Customer;
import de.appsfactory.customerservice.customer.CustomerRepository;
import de.appsfactory.customerservice.utils.NullAwareBeansUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class InvoiceServiceImpl implements InvoiceService {

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
    public Invoice createInvoice(Long customerId, Invoice invoice) {
        if (customerId == null) {
            log.error("customer id not found");
            throw new IllegalArgumentException("customer id not found");
        }
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            invoice.setCustomer(customer);
            return invoiceRepository.saveAndFlush(invoice);
        } else {
            log.error("customer not found:"+customerId);
            throw new IllegalStateException("customer not found:"+customerId);
        }
    }

    @Override
    public Invoice updateInvoice(Invoice invoice) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(invoice.getId());
        if (optionalInvoice.isPresent()) {
            Invoice existingInvoice = optionalInvoice.get();
            return invoiceRepository.saveAndFlush(invoiceNullAwareBeansUtil.copyNonNullProperties(existingInvoice, invoice));
        } else {
            log.error("invoice not found:"+invoice);
            throw new IllegalStateException("customer not found:{}"+invoice);
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
