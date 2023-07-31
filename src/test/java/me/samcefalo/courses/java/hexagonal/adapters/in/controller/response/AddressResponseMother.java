package me.samcefalo.courses.java.hexagonal.adapters.in.controller.response;

public class AddressResponseMother {


    public static AddressResponse create() {
        AddressResponse addressResponse = new AddressResponse();
        addressResponse.setStreet("street");
        addressResponse.setState("state");
        addressResponse.setCity("city");
        return addressResponse;
    }
}