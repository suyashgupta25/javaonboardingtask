package de.appsfactory.customerservice.invoice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InvoiceController {

    private final static Logger log = LoggerFactory.getLogger(InvoiceController.class);

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
    public String createInvoice(@RequestBody Invoice invoice, @PathVariable Long id){
        log.debug("creating Invoice="+invoice.toString());
        invoiceService.createInvoice(id, invoice);
        return "Created successfully";
    }

    @RequestMapping(value = "/invoice", method = RequestMethod.PUT)
    public String updateInvoice(@RequestBody Invoice invoice){
        log.debug("updating Customer="+invoice.toString());
        invoiceService.updateInvoice(invoice);
        return "Updated successfully";
    }

    @RequestMapping(value = "/invoice/{id}", method = RequestMethod.DELETE)
    public String deleteInvoice(@PathVariable Long id){
        log.debug("deleting Customer");
        invoiceService.deleteInvoice(id);
        return "Deleted successfully";
    }

}
