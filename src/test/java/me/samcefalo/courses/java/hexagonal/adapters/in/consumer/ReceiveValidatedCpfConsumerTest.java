package me.samcefalo.courses.java.hexagonal.adapters.in.consumer;

import me.samcefalo.courses.java.hexagonal.adapters.in.consumer.mapper.CustomerMessageMapper;
import me.samcefalo.courses.java.hexagonal.adapters.in.consumer.message.CustomerMessage;
import me.samcefalo.courses.java.hexagonal.adapters.in.consumer.message.CustomerMessageMother;
import me.samcefalo.courses.java.hexagonal.application.core.domain.Customer;
import me.samcefalo.courses.java.hexagonal.application.core.domain.CustomerMother;
import me.samcefalo.courses.java.hexagonal.application.exceptions.CustomerNotFoundException;
import me.samcefalo.courses.java.hexagonal.application.ports.in.UpdateCustomerInputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ReceiveValidatedCpfConsumerTest {

    private UpdateCustomerInputPort updateCustomerInputPort;
    private CustomerMessageMapper customerMessageMapper;
    private ReceiveValidatedCpfConsumer receiveValidatedCpfConsumer;

    @BeforeEach
    void setUp() {
        updateCustomerInputPort = mock(UpdateCustomerInputPort.class);
        customerMessageMapper = mock(CustomerMessageMapper.class);
        receiveValidatedCpfConsumer = new ReceiveValidatedCpfConsumer(updateCustomerInputPort, customerMessageMapper);
    }

    @Test
    public void should_receive() throws CustomerNotFoundException {
        CustomerMessage customerMessage = CustomerMessageMother.create();
        Customer expectedCustomer = CustomerMother.create();

        when(customerMessageMapper.toCustomer(customerMessage)).thenReturn(expectedCustomer);
        doNothing().when(updateCustomerInputPort).update(expectedCustomer, customerMessage.getZipCode());

        assertDoesNotThrow(() -> receiveValidatedCpfConsumer.receive(customerMessage));

        verify(customerMessageMapper, times(1)).toCustomer(customerMessage);
        verify(updateCustomerInputPort, times(1)).update(expectedCustomer, customerMessage.getZipCode());
    }

    @Test
    public void should_receive_with_customer_not_found_exception() throws CustomerNotFoundException {
        CustomerMessage customerMessage = CustomerMessageMother.create();
        Customer expectedCustomer = CustomerMother.create();

        when(customerMessageMapper.toCustomer(customerMessage)).thenReturn(expectedCustomer);
        doThrow(CustomerNotFoundException.class).when(updateCustomerInputPort).update(expectedCustomer, customerMessage.getZipCode());

        assertThrows(CustomerNotFoundException.class, () -> receiveValidatedCpfConsumer.receive(customerMessage));

        verify(customerMessageMapper, times(1)).toCustomer(customerMessage);
    }
}