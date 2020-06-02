package de.appsfactory.emailservice.email;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Email {

    @NonNull
    private String receiverEmailAddress;

    @NonNull
    private String subject;

    @NonNull
    private String body;

}
