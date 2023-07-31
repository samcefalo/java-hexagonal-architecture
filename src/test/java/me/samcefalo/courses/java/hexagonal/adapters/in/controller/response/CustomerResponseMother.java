package me.samcefalo.courses.java.hexagonal.adapters.in.controller.response;

public class CustomerResponseMother {

    public static CustomerResponse create() {
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setName("Samuel");
        customerResponse.setCpf("12345678910");
        customerResponse.setValidCpf(false);
        customerResponse.setAddress(AddressResponseMother.create());
        return customerResponse;
    }

}