package me.samcefalo.courses.java.hexagonal.application.ports.in;

import me.samcefalo.courses.java.hexagonal.application.core.domain.Customer;

public interface InsertCustomerInputPort {

    void insert(Customer customer, String zipCode);

}
