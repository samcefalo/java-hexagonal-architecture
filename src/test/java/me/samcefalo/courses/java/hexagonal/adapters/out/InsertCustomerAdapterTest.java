package me.samcefalo.courses.java.hexagonal.adapters.out;

import me.samcefalo.courses.java.hexagonal.adapters.out.repository.CustomerRepository;
import me.samcefalo.courses.java.hexagonal.adapters.out.repository.entity.CustomerEntity;
import me.samcefalo.courses.java.hexagonal.adapters.out.repository.entity.CustomerEntityMother;
import me.samcefalo.courses.java.hexagonal.adapters.out.repository.mapper.CustomerEntityMapper;
import me.samcefalo.courses.java.hexagonal.application.core.domain.Customer;
import me.samcefalo.courses.java.hexagonal.application.core.domain.CustomerMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class InsertCustomerAdapterTest {

    private CustomerRepository customerRepository;
    private CustomerEntityMapper customerEntityMapper;
    private InsertCustomerAdapter insertCustomerAdapter;

    @BeforeEach
    void setup() {
        customerRepository = mock(CustomerRepository.class);
        customerEntityMapper = mock(CustomerEntityMapper.class);
        insertCustomerAdapter = new InsertCustomerAdapter(customerRepository, customerEntityMapper);
    }

    @Test
    public void should_insert_customer() {
        Customer customer = CustomerMother.create();
        CustomerEntity customerEntity = CustomerEntityMother.create();

        when(customerEntityMapper.toCustomerEntity(customer)).thenReturn(customerEntity);
        when(customerRepository.save(customerEntity)).thenReturn(customerEntity);

        insertCustomerAdapter.insert(customer);

        verify(customerRepository, times(1)).save(customerEntity);
        verify(customerEntityMapper, times(1)).toCustomerEntity(customer);
    }
}