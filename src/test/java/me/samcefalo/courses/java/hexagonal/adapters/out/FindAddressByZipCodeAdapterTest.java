package me.samcefalo.courses.java.hexagonal.adapters.out;

import me.samcefalo.courses.java.hexagonal.adapters.out.client.FindAddressByZipCodeClient;
import me.samcefalo.courses.java.hexagonal.adapters.out.client.mapper.AddressResponseMapper;
import me.samcefalo.courses.java.hexagonal.adapters.out.client.response.AddressResponse;
import me.samcefalo.courses.java.hexagonal.adapters.out.client.response.AddressResponseMother;
import me.samcefalo.courses.java.hexagonal.application.core.domain.Address;
import me.samcefalo.courses.java.hexagonal.application.core.domain.AddressMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FindAddressByZipCodeAdapterTest {

    private FindAddressByZipCodeClient findAddressByZipCodeClient;
    private AddressResponseMapper addressResponseMapper;
    private FindAddressByZipCodeAdapter findAddressByZipCodeAdapter;

    @BeforeEach
    void setUp() {
        addressResponseMapper = mock(AddressResponseMapper.class);
        findAddressByZipCodeClient = mock(FindAddressByZipCodeClient.class);
        findAddressByZipCodeAdapter = new FindAddressByZipCodeAdapter(findAddressByZipCodeClient, addressResponseMapper);
    }

    @Test
    public void should_find_address_by_zip_code() {
        String zipCode = "12345678";
        AddressResponse addressResponse = AddressResponseMother.create();
        Address expected = AddressMother.create();

        when(findAddressByZipCodeClient.find(zipCode)).thenReturn(addressResponse);
        when(addressResponseMapper.toAddress(addressResponse)).thenReturn(expected);

        Address actual = findAddressByZipCodeAdapter.find(zipCode);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }
}