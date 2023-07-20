package me.samcefalo.courses.java.hexagonal.adapters.in.controller.response;

import lombok.Data;

@Data
public class CustomerResponse {

    private String name;
    private AddressResponse address;
    private String cpf;
    private boolean isValidCpf;

}
