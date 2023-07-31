package me.samcefalo.courses.java.hexagonal.adapters.in.controller.mapper;

import me.samcefalo.courses.java.hexagonal.adapters.in.controller.request.CustomerRequest;
import me.samcefalo.courses.java.hexagonal.adapters.in.controller.response.CustomerResponse;
import me.samcefalo.courses.java.hexagonal.application.core.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "id" , ignore = true)
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "validCpf", ignore = true)
    Customer toCustomer(CustomerRequest customerRequest);

    CustomerResponse toCustomerResponse(Customer customer);

}
