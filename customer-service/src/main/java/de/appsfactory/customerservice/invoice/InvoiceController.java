package de.appsfactory.customerservice.invoice;

import lombok.extern.slf4j.Slf4j;
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
    public Invoice invoiceById(@PathVariable Long id) {
        log.debug("getting Invoice");
        return invoiceService.findInvoicesById(id);
    }

    @PostMapping("/invoice/{id}")
    public Invoice createInvoice(@RequestBody @Valid Invoice invoice, @PathVariable Long id){
        log.debug("creating Invoice={}"+invoice);
        return invoiceService.createInvoice(id, invoice);
    }

    @RequestMapping(value = "/invoice", method = RequestMethod.PUT)
    public Invoice updateInvoice(@RequestBody @Valid Invoice invoice){
        log.debug("updating Customer={}"+invoice);
        return invoiceService.updateInvoice(invoice);
    }

    @RequestMapping(value = "/invoice/{id}", method = RequestMethod.DELETE)
    public void deleteInvoice(@PathVariable Long id){
        log.debug("deleting Customer");
        invoiceService.deleteInvoice(id);
    }

}
