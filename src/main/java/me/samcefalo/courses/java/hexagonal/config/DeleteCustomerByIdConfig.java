package me.samcefalo.courses.java.hexagonal.config;

import me.samcefalo.courses.java.hexagonal.application.core.usecase.DeleteCustomerByIdUseCase;
import me.samcefalo.courses.java.hexagonal.application.ports.in.FindCustomerByIdInputPort;
import me.samcefalo.courses.java.hexagonal.application.ports.out.DeleteCustomerByIdOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeleteCustomerByIdConfig {

    @Bean
    public DeleteCustomerByIdUseCase deleteCustomerByIdUseCase(FindCustomerByIdInputPort findCustomerByIdInputPort, DeleteCustomerByIdOutputPort deleteCustomerByIdOutputPort) {
        return new DeleteCustomerByIdUseCase(findCustomerByIdInputPort, deleteCustomerByIdOutputPort);
    }

}
