package me.samcefalo.courses.java.hexagonal.application.core.usecase;

import me.samcefalo.courses.java.hexagonal.application.core.domain.Address;
import me.samcefalo.courses.java.hexagonal.application.core.domain.AddressMother;
import me.samcefalo.courses.java.hexagonal.application.core.domain.Customer;
import me.samcefalo.courses.java.hexagonal.application.core.domain.CustomerMother;
import me.samcefalo.courses.java.hexagonal.application.ports.out.FindAddressByZipCodeOutputPort;
import me.samcefalo.courses.java.hexagonal.application.ports.out.InsertCustomerOutputPort;
import me.samcefalo.courses.java.hexagonal.application.ports.out.SendCpfForValidationOutputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class InsertCustomerUseCaseTest {

    private FindAddressByZipCodeOutputPort findAddressByZipCodeOutputPort;
    private InsertCustomerOutputPort insertCustomerOutputPort;
    private SendCpfForValidationOutputPort sendCpfForValidationOutputPort;
    private InsertCustomerUseCase insertCustomerUseCase;

    @BeforeEach
    void setup() {
        findAddressByZipCodeOutputPort = mock(FindAddressByZipCodeOutputPort.class);
        insertCustomerOutputPort = mock(InsertCustomerOutputPort.class);
        sendCpfForValidationOutputPort = mock(SendCpfForValidationOutputPort.class);
        insertCustomerUseCase = new InsertCustomerUseCase(findAddressByZipCodeOutputPort, insertCustomerOutputPort, sendCpfForValidationOutputPort);
    }

    @Test
    public void should_insert_customer() {
        String zipCode = "12345678";
        Customer customer = CustomerMother.create();
        Address address = AddressMother.create();

        when(findAddressByZipCodeOutputPort.find(zipCode)).thenReturn(address);
        doNothing().when(insertCustomerOutputPort).insert(customer);
        doNothing().when(sendCpfForValidationOutputPort).send(customer.getCpf());

        insertCustomerUseCase.insert(customer, zipCode);

        assertEquals(address, customer.getAddress());
        verify(findAddressByZipCodeOutputPort, times(1)).find(zipCode);
        verify(insertCustomerOutputPort, times(1)).insert(customer);
        verify(sendCpfForValidationOutputPort, times(1)).send(customer.getCpf());
    }
}