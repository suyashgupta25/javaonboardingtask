package de.appsfactory.customerservice.invoice;

import de.appsfactory.customerservice.customer.Customer;
import de.appsfactory.customerservice.customer.CustomerRepository;
import de.appsfactory.customerservice.util.DummyObjects;
import de.appsfactory.customerservice.utils.NullAwareBeansUtil;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static de.appsfactory.customerservice.util.DummyObjects.INVOICE_TITLE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
public class InvoiceServiceImplTest {

    @TestConfiguration
    static class InvoiceServiceImplTestContextConfiguration {

        @MockBean
        private InvoiceRepository repository;

        @MockBean
        private CustomerRepository mockCustomerRepository;

        @MockBean
        private NullAwareBeansUtil<Invoice> mockInvoiceNullAwareBeansUtil;

        @Bean
        public InvoiceService employeeService() {
            invoiceRepository = repository;
            customerRepository = mockCustomerRepository;
            invoiceNullAwareBeansUtil = mockInvoiceNullAwareBeansUtil;
            return new InvoiceServiceImpl(invoiceRepository, mockCustomerRepository, mockInvoiceNullAwareBeansUtil);
        }
    }

    @Autowired
    private InvoiceService invoiceService;
    private static InvoiceRepository invoiceRepository;
    private static CustomerRepository customerRepository;
    private static NullAwareBeansUtil<Invoice> invoiceNullAwareBeansUtil;

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

    @AfterAll
    static void clear() {
        customerRepository = null;
        invoiceRepository = null;
    }

}
