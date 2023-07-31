package me.samcefalo.courses.java.hexagonal.adapters.in.controller.request;

public class CustomerRequestMother {


    public static CustomerRequest create() {
        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setCpf("12345678901");
        customerRequest.setName("Samuel");
        customerRequest.setZipCode("12345678");
        return customerRequest;
    }
}