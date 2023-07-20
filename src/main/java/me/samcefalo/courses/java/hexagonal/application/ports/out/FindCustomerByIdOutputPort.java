package me.samcefalo.courses.java.hexagonal.application.ports.out;

import me.samcefalo.courses.java.hexagonal.application.core.domain.Customer;

import java.util.Optional;

public interface FindCustomerByIdOutputPort {

    Optional<Customer> find(String id);

}
