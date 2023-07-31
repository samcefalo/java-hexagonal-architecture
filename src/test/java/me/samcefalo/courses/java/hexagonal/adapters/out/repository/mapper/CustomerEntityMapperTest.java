package me.samcefalo.courses.java.hexagonal.adapters.out.repository.mapper;

import me.samcefalo.courses.java.hexagonal.adapters.out.repository.entity.CustomerEntity;
import me.samcefalo.courses.java.hexagonal.adapters.out.repository.entity.CustomerEntityMother;
import me.samcefalo.courses.java.hexagonal.application.core.domain.Customer;
import me.samcefalo.courses.java.hexagonal.application.core.domain.CustomerMother;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerEntityMapperTest {

    private CustomerEntityMapper customerEntityMapper = Mappers.getMapper(CustomerEntityMapper.class);

    @Test
    public void should_map_entity_to_domain() {
        CustomerEntity customerEntity = CustomerEntityMother.create();

        Customer customer = customerEntityMapper.toCustomer(customerEntity);

        assertEquals(customerEntity.getId(), customer.getId());
        assertEquals(customerEntity.getName(), customer.getName());
        assertEquals(customerEntity.getCpf(), customer.getCpf());
        assertEquals(customerEntity.getAddress().getCity(), customer.getAddress().getCity());
        assertEquals(customerEntity.getAddress().getState(), customer.getAddress().getState());
        assertEquals(customerEntity.getAddress().getStreet(), customer.getAddress().getStreet());
    }

    @Test
    public void should_map_domain_to_entity() {
        Customer customer = CustomerMother.create();

        CustomerEntity customerEntity = customerEntityMapper.toCustomerEntity(customer);

        assertEquals(customer.getId(), customerEntity.getId());
        assertEquals(customer.getName(), customerEntity.getName());
        assertEquals(customer.getCpf(), customerEntity.getCpf());
        assertEquals(customer.getAddress().getCity(), customerEntity.getAddress().getCity());
        assertEquals(customer.getAddress().getState(), customerEntity.getAddress().getState());
        assertEquals(customer.getAddress().getStreet(), customerEntity.getAddress().getStreet());
    }
}