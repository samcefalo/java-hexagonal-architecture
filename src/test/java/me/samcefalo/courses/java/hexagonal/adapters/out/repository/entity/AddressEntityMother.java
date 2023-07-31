package me.samcefalo.courses.java.hexagonal.adapters.out.repository.entity;

public class AddressEntityMother {

    public static AddressEntity create() {
        AddressEntity address = new AddressEntity();
        address.setCity("City");
        address.setState("State");
        address.setStreet("Street");
        return address;
    }

}