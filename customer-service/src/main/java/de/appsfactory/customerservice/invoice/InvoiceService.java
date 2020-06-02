package de.appsfactory.customerservice.invoice;

import org.springframework.http.ResponseEntity;

public interface InvoiceService {

    ResponseEntity<Invoice> findInvoicesById(Long id);

    ResponseEntity<Invoice> createInvoice(Long customerId, Invoice invoice);

    ResponseEntity<Invoice> updateInvoice(Invoice invoice);

    void deleteInvoice(Long id);
}
