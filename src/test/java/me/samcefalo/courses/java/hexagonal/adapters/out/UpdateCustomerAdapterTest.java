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

class UpdateCustomerAdapterTest {

    private CustomerRepository customerRepository;
    private CustomerEntityMapper customerMapper;
    private UpdateCustomerAdapter updateCustomerAdapter;

    @BeforeEach
    void setUp() {
        customerRepository = mock(CustomerRepository.class);
        customerMapper = mock(CustomerEntityMapper.class);
        updateCustomerAdapter = new UpdateCustomerAdapter(customerRepository, customerMapper);
    }

    @Test
    public void should_update_customer() {
        Customer customer = CustomerMother.create();
        CustomerEntity customerEntity = CustomerEntityMother.create();

        when(customerMapper.toCustomerEntity(customer)).thenReturn(customerEntity);
        when(customerRepository.save(customerEntity)).thenReturn(customerEntity);

        updateCustomerAdapter.update(customer);

        verify(customerRepository, times(1)).save(customerEntity);
        verify(customerMapper, times(1)).toCustomerEntity(customer);
    }
}