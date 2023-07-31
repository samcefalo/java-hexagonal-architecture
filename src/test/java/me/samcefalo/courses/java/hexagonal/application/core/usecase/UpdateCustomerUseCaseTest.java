package me.samcefalo.courses.java.hexagonal.application.core.usecase;

import me.samcefalo.courses.java.hexagonal.application.core.domain.Address;
import me.samcefalo.courses.java.hexagonal.application.core.domain.AddressMother;
import me.samcefalo.courses.java.hexagonal.application.core.domain.Customer;
import me.samcefalo.courses.java.hexagonal.application.core.domain.CustomerMother;
import me.samcefalo.courses.java.hexagonal.application.ports.out.FindAddressByZipCodeOutputPort;
import me.samcefalo.courses.java.hexagonal.application.ports.out.UpdateCustomerOutputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UpdateCustomerUseCaseTest {

    private FindAddressByZipCodeOutputPort findAddressByZipCodeOutputPort;
    private UpdateCustomerOutputPort updateCustomerOutputPort;
    private UpdateCustomerUseCase updateCustomerUseCase;

    @BeforeEach
    void setup() {
        findAddressByZipCodeOutputPort = mock(FindAddressByZipCodeOutputPort.class);
        updateCustomerOutputPort = mock(UpdateCustomerOutputPort.class);
        updateCustomerUseCase = new UpdateCustomerUseCase(findAddressByZipCodeOutputPort, updateCustomerOutputPort);
    }

    @Test
    public void should_insert_customer() {
        String zipCode = "12345678";
        Customer customer = CustomerMother.create();
        Address address = AddressMother.create();

        when(findAddressByZipCodeOutputPort.find(zipCode)).thenReturn(address);
        doNothing().when(updateCustomerOutputPort).update(customer);

        updateCustomerUseCase.update(customer, zipCode);

        assertEquals(address, customer.getAddress());
        verify(findAddressByZipCodeOutputPort, times(1)).find(zipCode);
        verify(updateCustomerOutputPort, times(1)).update(customer);
    }

}