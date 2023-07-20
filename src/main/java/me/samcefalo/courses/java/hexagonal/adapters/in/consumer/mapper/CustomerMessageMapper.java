package me.samcefalo.courses.java.hexagonal.adapters.in.consumer.mapper;

import me.samcefalo.courses.java.hexagonal.adapters.in.consumer.message.CustomerMessage;
import me.samcefalo.courses.java.hexagonal.application.core.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMessageMapper {

    @Mapping(target = "address", ignore = true)
    Customer toCustomer(CustomerMessage customerMessage);

}
