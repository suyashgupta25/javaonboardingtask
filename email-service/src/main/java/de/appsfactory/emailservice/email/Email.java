package de.appsfactory.emailservice.email;

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
