package me.samcefalo.courses.java.hexagonal.adapters.out.client.mapper;

import me.samcefalo.courses.java.hexagonal.adapters.out.client.response.AddressResponse;
import me.samcefalo.courses.java.hexagonal.application.core.domain.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressResponseMapper {

    Address toAddress(AddressResponse addressResponse);

}
