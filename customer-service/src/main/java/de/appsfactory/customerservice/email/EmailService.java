package de.appsfactory.customerservice.email;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "email-service")
@RibbonClient(name = "email-service")
public interface EmailService {

    @PostMapping("/sendEmail")
    String sendEmail(@RequestBody Email email);

}

