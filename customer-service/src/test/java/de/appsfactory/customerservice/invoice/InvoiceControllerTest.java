package de.appsfactory.customerservice.invoice;

import de.appsfactory.customerservice.util.DummyObjects;
import de.appsfactory.customerservice.util.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(InvoiceController.class)
public class InvoiceControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private InvoiceService service;

    @Test
    public void whenGetInvoice_thenReturnJson()
            throws Exception {
        Long id = 1L;
        Invoice invoice = DummyObjects.getInvoice();
        given(service.findInvoicesById(id)).willReturn(ResponseEntity.ok(invoice));

        mvc.perform(get("/invoice/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(invoice.getTitle())));
    }

    @Test
    public void whenCreateInvoice_thenReturnJson()
            throws Exception {
        Long customerId = 1L;
        Invoice invoice = DummyObjects.getInvoice();
        doReturn(ResponseEntity.ok(invoice)).when(service).createInvoice(anyLong(), ArgumentMatchers.any(Invoice.class));

        mvc.perform(post("/invoice/" + customerId)
                .content(JsonUtil.toJson(invoice))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(invoice.getTitle())));
    }

    @Test
    public void whenUpdateInvoice_thenReturnJson()
            throws Exception {
        Invoice invoice = DummyObjects.getInvoice();
        doReturn(ResponseEntity.ok(invoice)).when(service).updateInvoice(ArgumentMatchers.any(Invoice.class));

        mvc.perform(put("/invoice")
                .content(JsonUtil.toJson(invoice))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(invoice.getTitle())));
    }

    @Test
    public void whenDeleteInvoice_returnSuccess()
            throws Exception {
        doNothing().when(service).deleteInvoice(anyLong());

        mvc.perform(delete("/invoice/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
