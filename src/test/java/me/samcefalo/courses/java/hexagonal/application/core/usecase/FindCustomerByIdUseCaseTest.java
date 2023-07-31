package me.samcefalo.courses.java.hexagonal.application.core.usecase;

import me.samcefalo.courses.java.hexagonal.application.core.domain.Customer;
import me.samcefalo.courses.java.hexagonal.application.core.domain.CustomerMother;
import me.samcefalo.courses.java.hexagonal.application.exceptions.CustomerNotFoundException;
import me.samcefalo.courses.java.hexagonal.application.ports.out.FindCustomerByIdOutputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class FindCustomerByIdUseCaseTest {

    private FindCustomerByIdOutputPort findCustomerByIdOutputPort;
    private FindCustomerByIdUseCase findCustomerByIdUseCase;

    @BeforeEach
    void setUp() {
        findCustomerByIdOutputPort = mock(FindCustomerByIdOutputPort.class);
        findCustomerByIdUseCase = new FindCustomerByIdUseCase(findCustomerByIdOutputPort);
    }

    @Test
    public void should_find_customer_by_id() throws CustomerNotFoundException {
        String id = "1";
        Customer expected = CustomerMother.create();

        when(findCustomerByIdOutputPort.find(id)).thenReturn(Optional.of(expected));

        Customer actual = findCustomerByIdUseCase.find(id);

        assertEquals(expected, actual);
        verify(findCustomerByIdOutputPort, times(1)).find(id);
    }

    @Test
    public void should_find_customer_by_id_with_customer_not_found_exception() {
        String id = "1";

        when(findCustomerByIdOutputPort.find(id)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> findCustomerByIdUseCase.find(id));

        verify(findCustomerByIdOutputPort, times(1)).find(id);
    }

}