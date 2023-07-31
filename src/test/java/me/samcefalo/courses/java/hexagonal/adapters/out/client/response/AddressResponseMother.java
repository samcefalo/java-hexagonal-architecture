package me.samcefalo.courses.java.hexagonal.adapters.out.client.response;

public class AddressResponseMother {

    public static AddressResponse create() {
        AddressResponse addressResponse = new AddressResponse();
        addressResponse.setCity("São Paulo");
        addressResponse.setStreet("Vila Mariana");
        addressResponse.setState("SP");
        return addressResponse;
    }

}