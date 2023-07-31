package me.samcefalo.courses.java.hexagonal.adapters.in.consumer.message;

import java.util.UUID;

public class CustomerMessageMother {

    public static CustomerMessage create() {
        CustomerMessage customerMessage = new CustomerMessage();
        customerMessage.setName("Samuel");
        customerMessage.setCpf("12345678900");
        customerMessage.setId(UUID.randomUUID().toString());
        customerMessage.setValidCpf(true);
        customerMessage.setZipCode("12345678");
        return customerMessage;
    }

}