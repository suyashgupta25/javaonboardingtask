package de.appsfactory.customerservice.invoice;

import de.appsfactory.customerservice.CustomerServiceApplication;
import de.appsfactory.customerservice.customer.Customer;
import de.appsfactory.customerservice.customer.CustomerRepository;
import de.appsfactory.customerservice.util.DummyObjects;
import de.appsfactory.customerservice.utils.NullAwareBeansUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static de.appsfactory.customerservice.util.DummyObjects.INVOICE_TITLE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = CustomerServiceApplication.class)
@RunWith(SpringRunner.class)
public class InvoiceServiceImplTest {

    @MockBean
    private InvoiceRepository invoiceRepository;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private NullAwareBeansUtil<Invoice> invoiceNullAwareBeansUtil;

    @Autowired
    private InvoiceService invoiceService;

    @Before
    public void setUp() {
        Assert.assertNotNull("invoiceService is not set", invoiceService);
        Assert.assertNotNull("invoiceRepository is not set", invoiceRepository);
        Assert.assertNotNull("customerRepository is not set", customerRepository);
        Assert.assertNotNull("invoiceNullAwareBeansUtil is not set", invoiceNullAwareBeansUtil);
    }

    @Test
    public void whenFindInvoiceById_thenInvoiceShouldBeFound() {
        Invoice invoice = DummyObjects.getInvoice();
        doReturn(Optional.of(invoice)).when(invoiceRepository).findById(ArgumentMatchers.anyLong());

        ResponseEntity<Invoice> found = invoiceService.findInvoicesById(1L);
        assertThat(found.getBody().getTitle()).isEqualTo(INVOICE_TITLE);
    }

    @Test
    public void whenCreateInvoice_thenInvoiceShouldBeCreated() {
        Invoice invoice = DummyObjects.getInvoice();
        Customer customer = DummyObjects.getCustomer();
        doReturn(invoice).when(invoiceRepository).saveAndFlush(any(Invoice.class));
        doReturn(Optional.of(customer)).when(customerRepository).findById(anyLong());

        ResponseEntity<Invoice> found = invoiceService.createInvoice(1L, invoice);
        assertThat(found.getBody().getTitle()).isEqualTo(INVOICE_TITLE);
    }

    @Test
    public void whenUpdateInvoice_thenInvoiceShouldBeUpdated() {
        Invoice invoice = DummyObjects.getInvoice();
        doReturn(Optional.of(invoice)).when(invoiceRepository).findById(anyLong());
        doReturn(invoice).when(invoiceNullAwareBeansUtil).copyNonNullProperties(any(Invoice.class), any(Invoice.class));
        doReturn(invoice).when(invoiceRepository).saveAndFlush(any(Invoice.class));

        ResponseEntity<Invoice> found = invoiceService.updateInvoice(invoice);
        assertThat(found.getBody().getTitle()).isEqualTo(INVOICE_TITLE);
    }
}
