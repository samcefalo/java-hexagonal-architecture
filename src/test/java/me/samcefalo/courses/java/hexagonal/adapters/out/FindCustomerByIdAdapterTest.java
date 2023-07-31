package me.samcefalo.courses.java.hexagonal.adapters.out;

import me.samcefalo.courses.java.hexagonal.adapters.out.repository.CustomerRepository;
import me.samcefalo.courses.java.hexagonal.adapters.out.repository.entity.CustomerEntity;
import me.samcefalo.courses.java.hexagonal.adapters.out.repository.entity.CustomerEntityMother;
import me.samcefalo.courses.java.hexagonal.adapters.out.repository.mapper.CustomerEntityMapper;
import me.samcefalo.courses.java.hexagonal.application.core.domain.Customer;
import me.samcefalo.courses.java.hexagonal.application.core.domain.CustomerMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FindCustomerByIdAdapterTest {

    private CustomerRepository customerRepository;
    private CustomerEntityMapper customerEntityMapper;
    private FindCustomerByIdAdapter findCustomerByIdAdapter;

    @BeforeEach
    void setup() {
        customerRepository = mock(CustomerRepository.class);
        customerEntityMapper = mock(CustomerEntityMapper.class);
        findCustomerByIdAdapter = new FindCustomerByIdAdapter(customerRepository, customerEntityMapper);
    }

    @Test
    public void should_find_customer_by_id() {
        String id = "123";
        CustomerEntity customerEntity = CustomerEntityMother.create();
        Customer expected = CustomerMother.create();

        when(customerRepository.findById(id)).thenReturn(Optional.of(customerEntity));
        when(customerEntityMapper.toCustomer(customerEntity)).thenReturn(expected);

        Optional<Customer> actual = findCustomerByIdAdapter.find(id);

        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }
}