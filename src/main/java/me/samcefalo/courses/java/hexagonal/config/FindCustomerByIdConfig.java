package me.samcefalo.courses.java.hexagonal.config;

import me.samcefalo.courses.java.hexagonal.application.core.usecase.FindCustomerByIdUseCase;
import me.samcefalo.courses.java.hexagonal.application.ports.out.FindCustomerByIdOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindCustomerByIdConfig {

    @Bean
    public FindCustomerByIdUseCase findCustomerByIdUseCase(FindCustomerByIdOutputPort findCustomerByIdOutputPort) {
        return new FindCustomerByIdUseCase(findCustomerByIdOutputPort);
    }

}
