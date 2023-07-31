package me.samcefalo.courses.java.hexagonal.adapters.in.controller.request;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomerRequestTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void should_validate_null_name() {
        CustomerRequest customerRequest = CustomerRequestMother.create();
        customerRequest.setName(null);

        Set<ConstraintViolation<CustomerRequest>> violations = validator.validate(customerRequest);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(violation -> violation.getPropertyPath().toString().equals("name")));
    }

    @Test
    public void should_validate_empty_name() {
        CustomerRequest customerRequest = CustomerRequestMother.create();
        customerRequest.setName("");

        Set<ConstraintViolation<CustomerRequest>> violations = validator.validate(customerRequest);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(violation -> violation.getPropertyPath().toString().equals("name")));
    }

    @Test
    public void should_validate_name() {
        CustomerRequest customerRequest = CustomerRequestMother.create();

        Set<ConstraintViolation<CustomerRequest>> violations = validator.validate(customerRequest);

        assertTrue(violations.isEmpty());
    }

    @Test
    public void should_validate_null_cpf() {
        CustomerRequest customerRequest = CustomerRequestMother.create();
        customerRequest.setCpf(null);

        Set<ConstraintViolation<CustomerRequest>> violations = validator.validate(customerRequest);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(violation -> violation.getPropertyPath().toString().equals("cpf")));
    }

    @Test
    public void should_validate_empty_cpf() {
        CustomerRequest customerRequest = CustomerRequestMother.create();
        customerRequest.setCpf("");

        Set<ConstraintViolation<CustomerRequest>> violations = validator.validate(customerRequest);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(violation -> violation.getPropertyPath().toString().equals("cpf")));
    }

    @Test
    public void should_validate_cpf() {
        CustomerRequest customerRequest = CustomerRequestMother.create();

        Set<ConstraintViolation<CustomerRequest>> violations = validator.validate(customerRequest);

        assertTrue(violations.isEmpty());
    }

    @Test
    public void should_validate_null_zipCode() {
        CustomerRequest customerRequest = CustomerRequestMother.create();
        customerRequest.setZipCode(null);

        Set<ConstraintViolation<CustomerRequest>> violations = validator.validate(customerRequest);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(violation -> violation.getPropertyPath().toString().equals("zipCode")));
    }

    @Test
    public void should_validate_empty_zipCode() {
        CustomerRequest customerRequest = CustomerRequestMother.create();
        customerRequest.setZipCode("");

        Set<ConstraintViolation<CustomerRequest>> violations = validator.validate(customerRequest);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(violation -> violation.getPropertyPath().toString().equals("zipCode")));
    }

    @Test
    public void should_validate_zipCode() {
        CustomerRequest customerRequest = CustomerRequestMother.create();

        Set<ConstraintViolation<CustomerRequest>> violations = validator.validate(customerRequest);

        assertTrue(violations.isEmpty());
    }

}