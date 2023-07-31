package me.samcefalo.courses.java.hexagonal.adapters.out.repository.entity;

import java.util.UUID;

public class CustomerEntityMother {

    public static CustomerEntity create() {
        CustomerEntity customer = new CustomerEntity();
        customer.setId(UUID.randomUUID().toString());
        customer.setName("test");
        customer.setCpf("12345678901");
        customer.setValidCpf(false);
        customer.setAddress(AddressEntityMother.create());
        return customer;
    }
}