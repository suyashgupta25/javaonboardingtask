package de.appsfactory.emailservice.email;

import de.appsfactory.emailservice.util.DummyObjects;
import de.appsfactory.emailservice.util.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmailController.class)
public class EmailControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmailService service;

    @Test
    public void whenSendEmail_thenReturnEmailObjectJson() throws Exception {
        Email email = DummyObjects.getEmail();
        given(service.sendEmail(email)).willReturn(email);

        mvc.perform(post("/sendEmail")
                .content(JsonUtil.toJson(email))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
