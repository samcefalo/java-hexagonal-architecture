package me.samcefalo.courses.java.hexagonal.config;

import me.samcefalo.courses.java.hexagonal.application.core.usecase.InsertCustomerUseCase;
import me.samcefalo.courses.java.hexagonal.application.ports.out.FindAddressByZipCodeOutputPort;
import me.samcefalo.courses.java.hexagonal.application.ports.out.InsertCustomerOutputPort;
import me.samcefalo.courses.java.hexagonal.application.ports.out.SendCpfForValidationOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InsertCustomerConfig {

    @Bean
    public InsertCustomerUseCase insertCustomerUseCase(FindAddressByZipCodeOutputPort findAddressByZipCodeOutputPort, InsertCustomerOutputPort insertCustomerOutputPort, SendCpfForValidationOutputPort sendCpfForValidationOutputPort) {
        return new InsertCustomerUseCase(findAddressByZipCodeOutputPort, insertCustomerOutputPort, sendCpfForValidationOutputPort);
    }

}
