package me.samcefalo.courses.java.hexagonal.application.ports.out;

import me.samcefalo.courses.java.hexagonal.application.core.domain.Customer;

public interface InsertCustomerOutputPort {

    void insert(Customer customer);

}
