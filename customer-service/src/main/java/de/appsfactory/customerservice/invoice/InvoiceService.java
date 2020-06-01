package de.appsfactory.customerservice.invoice;

public interface InvoiceService {

    Invoice findInvoicesById(Long id);

    Invoice createInvoice(Long customerId, Invoice invoice);

    Invoice updateInvoice(Invoice invoice);

    void deleteInvoice(Long id);
}
