package me.samcefalo.courses.java.hexagonal.adapters.in.consumer.mapper;

import me.samcefalo.courses.java.hexagonal.adapters.in.consumer.message.CustomerMessage;
import me.samcefalo.courses.java.hexagonal.adapters.in.consumer.message.CustomerMessageMother;
import me.samcefalo.courses.java.hexagonal.application.core.domain.Customer;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerMessageMapperTest {

    private CustomerMessageMapper customerMessageMapper = Mappers.getMapper(CustomerMessageMapper.class);
    ;

    @Test
    public void should_map_message_to_domain() {
        CustomerMessage customerMessage = CustomerMessageMother.create();

        Customer customer = customerMessageMapper.toCustomer(customerMessage);

        assertEquals(customerMessage.getName(), customer.getName());
        assertEquals(customerMessage.getCpf(), customer.getCpf());
        assertEquals(customerMessage.getId(), customer.getId());
    }

}