package de.appsfactory.emailservice.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements EmailService {

    private JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(Email email) {
        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo(email.getReceiverEmailAddress());
        mail.setSubject(email.getSubject());
        mail.setText(email.getBody());
        javaMailSender.send(mail);
    }
}
