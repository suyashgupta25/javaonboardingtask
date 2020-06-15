package de.appsfactory.emailservice.util;

import de.appsfactory.emailservice.email.Email;

public class DummyObjects {

    public static String CUSTOMER_EMAIL = "alex123@gmail.com";

    public static Email getEmail() {
        Email obj = new Email();
        obj.setBody("This is a test email");
        obj.setReceiverEmailAddress(CUSTOMER_EMAIL);
        obj.setSubject("Testing in progress");
        return obj;
    }
}
