package me.samcefalo.courses.java.hexagonal.adapters.out.repository.mapper;

import me.samcefalo.courses.java.hexagonal.adapters.out.repository.entity.CustomerEntity;
import me.samcefalo.courses.java.hexagonal.application.core.domain.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerEntityMapper {

    CustomerEntity toCustomerEntity(Customer customer);

    Customer toCustomer(CustomerEntity customerEntity);

}
