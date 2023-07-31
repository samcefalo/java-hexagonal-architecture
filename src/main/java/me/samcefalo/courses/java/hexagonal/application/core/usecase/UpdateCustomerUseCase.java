package me.samcefalo.courses.java.hexagonal.application.core.usecase;

import me.samcefalo.courses.java.hexagonal.application.core.domain.Address;
import me.samcefalo.courses.java.hexagonal.application.core.domain.Customer;
import me.samcefalo.courses.java.hexagonal.application.ports.in.UpdateCustomerInputPort;
import me.samcefalo.courses.java.hexagonal.application.ports.out.FindAddressByZipCodeOutputPort;
import me.samcefalo.courses.java.hexagonal.application.ports.out.UpdateCustomerOutputPort;

public class UpdateCustomerUseCase implements UpdateCustomerInputPort {

    private final FindAddressByZipCodeOutputPort findAddressByZipCodeOutputPort;
    private final UpdateCustomerOutputPort updateCustomerOutputPort;

    public UpdateCustomerUseCase(FindAddressByZipCodeOutputPort findAddressByZipCodeOutputPort, UpdateCustomerOutputPort updateCustomerOutputPort) {
        this.findAddressByZipCodeOutputPort = findAddressByZipCodeOutputPort;
        this.updateCustomerOutputPort = updateCustomerOutputPort;
    }

    public void update(Customer customer, String zipCode) {
        Address address = findAddressByZipCodeOutputPort.find(zipCode);

        customer.setAddress(address);

        updateCustomerOutputPort.update(customer);
    }

}
