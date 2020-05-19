package de.appsfactory.customerservice.invoice;

public interface InvoiceService {

    Invoice findInvoicesById(Long id);

    void createInvoice(Long customerId, Invoice invoice);

    void updateInvoice(Invoice invoice);

    void deleteInvoice(Long id);
}
