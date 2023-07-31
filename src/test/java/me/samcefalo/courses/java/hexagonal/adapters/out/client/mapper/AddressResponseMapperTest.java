package me.samcefalo.courses.java.hexagonal.adapters.out.client.mapper;

import me.samcefalo.courses.java.hexagonal.adapters.out.client.response.AddressResponse;
import me.samcefalo.courses.java.hexagonal.adapters.out.client.response.AddressResponseMother;
import me.samcefalo.courses.java.hexagonal.application.core.domain.Address;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddressResponseMapperTest {

    private AddressResponseMapper addressResponseMapper = Mappers.getMapper(AddressResponseMapper.class);

    @Test
    void should_map_response_to_domain() {
        AddressResponse addressResponse = AddressResponseMother.create();

        Address address = addressResponseMapper.toAddress(addressResponse);

        assertEquals(addressResponse.getStreet(), address.getStreet());
        assertEquals(addressResponse.getCity(), address.getCity());
        assertEquals(addressResponse.getState(), address.getState());
    }

}