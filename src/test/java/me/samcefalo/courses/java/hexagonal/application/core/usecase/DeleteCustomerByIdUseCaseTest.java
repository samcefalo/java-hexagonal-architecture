package me.samcefalo.courses.java.hexagonal.application.core.usecase;

import me.samcefalo.courses.java.hexagonal.application.core.domain.Customer;
import me.samcefalo.courses.java.hexagonal.application.core.domain.CustomerMother;
import me.samcefalo.courses.java.hexagonal.application.exceptions.CustomerNotFoundException;
import me.samcefalo.courses.java.hexagonal.application.ports.in.FindCustomerByIdInputPort;
import me.samcefalo.courses.java.hexagonal.application.ports.out.DeleteCustomerByIdOutputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DeleteCustomerByIdUseCaseTest {

    private FindCustomerByIdInputPort findCustomerByIdInputPort;
    private DeleteCustomerByIdOutputPort deleteCustomerByIdOutputPort;
    private DeleteCustomerByIdUseCase deleteCustomerByIdUseCase;

    @BeforeEach
    void setUp() {
        findCustomerByIdInputPort = mock(FindCustomerByIdInputPort.class);
        deleteCustomerByIdOutputPort = mock(DeleteCustomerByIdOutputPort.class);
        deleteCustomerByIdUseCase = new DeleteCustomerByIdUseCase(findCustomerByIdInputPort, deleteCustomerByIdOutputPort);
    }

    @Test
    public void should_delete_customer() throws CustomerNotFoundException {
        String id = "123";
        Customer customer = CustomerMother.create();

        when(findCustomerByIdInputPort.find(id)).thenReturn(customer);
        doNothing().when(deleteCustomerByIdOutputPort).delete(customer.getId());

        assertDoesNotThrow(() -> deleteCustomerByIdUseCase.delete(id));

        verify(findCustomerByIdInputPort, times(1)).find(id);
        verify(deleteCustomerByIdOutputPort, times(1)).delete(customer.getId());
    }

    @Test
    public void should_delete_customer_with_customer_not_found_exception() throws CustomerNotFoundException {
        String id = "123";
        Customer customer = CustomerMother.create();

        doThrow(CustomerNotFoundException.class).when(findCustomerByIdInputPort).find(id);
        doNothing().when(deleteCustomerByIdOutputPort).delete(customer.getId());

        assertThrows(CustomerNotFoundException.class, () -> deleteCustomerByIdUseCase.delete(id));

        verify(findCustomerByIdInputPort, times(1)).find(id);
        verify(deleteCustomerByIdOutputPort, times(0)).delete(customer.getId());
    }

}