package me.samcefalo.courses.java.hexagonal.application.ports.out;

import me.samcefalo.courses.java.hexagonal.application.core.domain.Address;

public interface FindAddressByZipCodeOutputPort {

    Address find(String zipCode);

}
