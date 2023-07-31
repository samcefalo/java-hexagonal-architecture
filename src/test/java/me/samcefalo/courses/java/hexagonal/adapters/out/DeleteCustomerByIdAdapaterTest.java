package me.samcefalo.courses.java.hexagonal.adapters.out;

import me.samcefalo.courses.java.hexagonal.adapters.out.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class DeleteCustomerByIdAdapaterTest {

    private CustomerRepository customerRepository;
    private DeleteCustomerByIdAdapater deleteCustomerByIdAdapater;

    @BeforeEach
    void setUp() {
        customerRepository = mock(CustomerRepository.class);
        deleteCustomerByIdAdapater = new DeleteCustomerByIdAdapater(customerRepository);
    }

    @Test
    public void should_delete_customer_by_id() {
        String id = "1";

        doNothing().when(customerRepository).deleteById(id);

        deleteCustomerByIdAdapater.delete("1");

        verify(customerRepository, times(1)).deleteById(id);
    }
}