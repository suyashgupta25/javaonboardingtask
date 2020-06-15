package de.appsfactory.emailservice.email;

import de.appsfactory.emailservice.util.DummyObjects;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

@RunWith(SpringRunner.class)
public class EmailServiceImplTest {

    @TestConfiguration
    static class EmailServiceImplTestContextConfiguration {
        @MockBean
        private JavaMailSender mailSender;

        @Bean
        public EmailService employeeService() {
            javaMailSender = mailSender;
            return new EmailServiceImpl(mailSender);
        }
    }

    @Autowired
    private EmailService emailService;
    private static JavaMailSender javaMailSender;

    @Test
    public void whenSendEmail_thenItShouldWork() {
        Email email = DummyObjects.getEmail();
        doNothing().when(javaMailSender).send(any(SimpleMailMessage.class));

        Email result = emailService.sendEmail(email);
        assertThat(result.getReceiverEmailAddress()).isEqualTo(email.getReceiverEmailAddress());
    }

    @AfterAll
    static void clear() {
        javaMailSender = null;
    }
}
