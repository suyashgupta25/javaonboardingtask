package de.appsfactory.customerservice.invoice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @RequestMapping(value = "/invoice/{id}", method = RequestMethod.GET)
    public ResponseEntity<Invoice> invoiceById(@PathVariable Long id) {
        log.debug("getting Invoice");
        return invoiceService.findInvoicesById(id);
    }

    @PostMapping("/invoice/{id}")
    public ResponseEntity<Invoice> createInvoice(@RequestBody @Valid Invoice invoice, @PathVariable Long id) {
        log.debug("creating Invoice={}", invoice);
        return invoiceService.createInvoice(id, invoice);
    }

    @PutMapping(value = "/invoice")
    public ResponseEntity<Invoice> updateInvoice(@RequestBody @Valid Invoice invoice) {
        log.debug("updating Customer={}", invoice);
        return invoiceService.updateInvoice(invoice);
    }

    @DeleteMapping(value = "/invoice/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteInvoice(@PathVariable Long id) {
        log.debug("deleting Customer");
        invoiceService.deleteInvoice(id);
    }

}
