package me.samcefalo.courses.java.hexagonal.application.core.domain;

public class AddressMother {

    public static Address create() {
        Address address = new Address();
        address.setStreet("Street");
        address.setState("State");
        address.setCity("City");
        return address;
    }

}