package me.samcefalo.courses.java.hexagonal.application.ports.in;

import me.samcefalo.courses.java.hexagonal.application.exceptions.CustomerNotFoundException;

public interface DeleteCustomerByIdInputPort {

    void delete(String id) throws CustomerNotFoundException;

}
