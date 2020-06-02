package de.appsfactory.customerservice.invoice;

import de.appsfactory.customerservice.customer.Customer;
import de.appsfactory.customerservice.customer.CustomerRepository;
import de.appsfactory.customerservice.error.exception.EntityNotFoundException;
import de.appsfactory.customerservice.utils.NullAwareBeansUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
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
    public ResponseEntity<Invoice> findInvoicesById(Long id) {
        Optional<Invoice> invoiceOptional = invoiceRepository.findById(id);
        if (invoiceOptional.isPresent()) {
            return ResponseEntity.ok(invoiceOptional.get());
        } else {
            log.error("invoice not found:"+id);
            throw new EntityNotFoundException(Invoice.class, "id", Objects.toString(id));
        }
    }

    @Override
    public ResponseEntity<Invoice> createInvoice(Long customerId, Invoice invoice) {
        if (customerId == null) {
            log.error("customer id not found");
            throw new EntityNotFoundException(Invoice.class, "id", Objects.toString(customerId));
        }
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            invoice.setCustomer(customer);
            return ResponseEntity.ok(invoiceRepository.saveAndFlush(invoice));
        } else {
            log.error("customer not found:"+customerId);
            throw new EntityNotFoundException(Invoice.class, "id", Objects.toString(customerId));
        }
    }

    @Override
    public ResponseEntity<Invoice> updateInvoice(Invoice invoice) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(invoice.getId());
        if (optionalInvoice.isPresent()) {
            Invoice existingInvoice = optionalInvoice.get();
            Invoice copiedInvoice = invoiceNullAwareBeansUtil.copyNonNullProperties(existingInvoice, invoice);
            return ResponseEntity.ok(invoiceRepository.saveAndFlush(copiedInvoice));
        } else {
            log.error("invoice not found:"+invoice);
            throw new EntityNotFoundException(Invoice.class, "id", Objects.toString(invoice.getId()));
        }
    }

    @Override
    public void deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }
}
