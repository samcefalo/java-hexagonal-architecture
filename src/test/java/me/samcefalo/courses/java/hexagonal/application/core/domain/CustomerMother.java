package me.samcefalo.courses.java.hexagonal.application.core.domain;

import java.util.UUID;

public class CustomerMother {

    public static Customer create() {
        Customer customer = new Customer();
        customer.setId(UUID.randomUUID().toString());
        customer.setName("Samuel");
        customer.setCpf("12345678901");
        customer.setAddress(AddressMother.create());
        customer.setValidCpf(false);
        return customer;
    }

}