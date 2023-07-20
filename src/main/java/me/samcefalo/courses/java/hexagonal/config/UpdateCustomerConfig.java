package me.samcefalo.courses.java.hexagonal.config;

import me.samcefalo.courses.java.hexagonal.application.core.usecase.UpdateCustomerUseCase;
import me.samcefalo.courses.java.hexagonal.application.ports.in.FindCustomerByIdInputPort;
import me.samcefalo.courses.java.hexagonal.application.ports.out.FindAddressByZipCodeOutputPort;
import me.samcefalo.courses.java.hexagonal.application.ports.out.UpdateCustomerOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdateCustomerConfig {

    @Bean
    public UpdateCustomerUseCase updateCustomerUseCase(FindCustomerByIdInputPort findCustomerByIdInputPort, FindAddressByZipCodeOutputPort findAddressByZipCodeOutputPort, UpdateCustomerOutputPort updateCustomerOutputPort) {
        return new UpdateCustomerUseCase(findCustomerByIdInputPort, findAddressByZipCodeOutputPort, updateCustomerOutputPort);
    }

}
