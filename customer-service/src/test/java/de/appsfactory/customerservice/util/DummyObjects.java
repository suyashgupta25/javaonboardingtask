package de.appsfactory.customerservice.util;

import de.appsfactory.customerservice.customer.Customer;
import de.appsfactory.customerservice.email.Email;
import de.appsfactory.customerservice.invoice.Invoice;

public class DummyObjects {

    public static String CUSTOMER_EMAIL = "alex123@gmail.com";
    public static String INVOICE_TITLE = "Invoice Title";

    public static Customer getCustomer() {
        Customer obj = new Customer();
        obj.setEmail(CUSTOMER_EMAIL);
        obj.setFullName("Alex Mitchel");
        obj.setAddress("21st street downtown");
        obj.setPhone("1234567890");
        return obj;
    }

    public static Invoice getInvoice() {
        Invoice obj = new Invoice();
        obj.setId(1L);
        obj.setAmount(100.12f);
        obj.setDescription("product details");
        obj.setCustomer(getCustomer());
        obj.setTitle(INVOICE_TITLE);
        return obj;
    }

    public static Email getEmail() {
        Email obj = new Email();
        obj.setBody("This is a test email");
        obj.setReceiverEmailAddress(CUSTOMER_EMAIL);
        obj.setSubject("Testing in progress");
        return obj;
    }
}
