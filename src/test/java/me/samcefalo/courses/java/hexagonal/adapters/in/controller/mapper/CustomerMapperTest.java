package me.samcefalo.courses.java.hexagonal.adapters.in.controller.mapper;

import me.samcefalo.courses.java.hexagonal.adapters.in.controller.request.CustomerRequest;
import me.samcefalo.courses.java.hexagonal.adapters.in.controller.request.CustomerRequestMother;
import me.samcefalo.courses.java.hexagonal.adapters.in.controller.response.CustomerResponse;
import me.samcefalo.courses.java.hexagonal.application.core.domain.Customer;
import me.samcefalo.courses.java.hexagonal.application.core.domain.CustomerMother;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerMapperTest {

    private CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);

    @Test
    public void should_map_request_to_domain() {
        CustomerRequest customerRequest = CustomerRequestMother.create();

        Customer customer = customerMapper.toCustomer(customerRequest);

        assertEquals(customerRequest.getName(), customer.getName());
        assertEquals(customerRequest.getCpf(), customer.getCpf());
    }

    @Test
    public void should_map_domain_to_response() {
        Customer customer = CustomerMother.create();

        CustomerResponse customerResponse = customerMapper.toCustomerResponse(customer);

        assertEquals(customer.getName(), customerResponse.getName());
        assertEquals(customer.getCpf(), customerResponse.getCpf());
        assertEquals(customer.isValidCpf(), customerResponse.isValidCpf());
        assertEquals(customer.getAddress().getCity(), customerResponse.getAddress().getCity());
        assertEquals(customer.getAddress().getState(), customerResponse.getAddress().getState());
        assertEquals(customer.getAddress().getStreet(), customerResponse.getAddress().getStreet());
    }

}