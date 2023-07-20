package me.samcefalo.courses.java.hexagonal.application.ports.in;

import me.samcefalo.courses.java.hexagonal.application.core.domain.Customer;
import me.samcefalo.courses.java.hexagonal.application.exceptions.CustomerNotFoundException;

public interface FindCustomerByIdInputPort {

    Customer find(String id) throws CustomerNotFoundException;

}
