package me.samcefalo.courses.java.hexagonal.application.exceptions;

public class CustomerNotFoundException extends Exception {

    private String id;

    public CustomerNotFoundException(String id) {
        super("Customer not found with id: " + id);
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
