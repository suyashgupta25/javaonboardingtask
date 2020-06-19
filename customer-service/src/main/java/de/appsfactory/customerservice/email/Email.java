package de.appsfactory.customerservice.email;

import lombok.*;

@Data
@NoArgsConstructor
public class Email {

    @NonNull
    private String receiverEmailAddress;

    @NonNull
    private String subject;

    @NonNull
    private String body;

}
