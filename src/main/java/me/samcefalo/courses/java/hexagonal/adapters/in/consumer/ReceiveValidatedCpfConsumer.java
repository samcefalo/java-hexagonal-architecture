package me.samcefalo.courses.java.hexagonal.adapters.in.consumer;

import me.samcefalo.courses.java.hexagonal.adapters.in.consumer.mapper.CustomerMessageMapper;
import me.samcefalo.courses.java.hexagonal.adapters.in.consumer.message.CustomerMessage;
import me.samcefalo.courses.java.hexagonal.application.core.domain.Customer;
import me.samcefalo.courses.java.hexagonal.application.exceptions.CustomerNotFoundException;
import me.samcefalo.courses.java.hexagonal.application.ports.in.UpdateCustomerInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ReceiveValidatedCpfConsumer {

    private final UpdateCustomerInputPort updateCustomerInputPort;
    private final CustomerMessageMapper customerMessageMapper;

    @Autowired
    public ReceiveValidatedCpfConsumer(UpdateCustomerInputPort updateCustomerInputPort, CustomerMessageMapper customerMessageMapper) {
        this.updateCustomerInputPort = updateCustomerInputPort;
        this.customerMessageMapper = customerMessageMapper;
    }

    @KafkaListener(topics = "tp-cpf-validated", groupId = "cefalo")
    public void receive(CustomerMessage customerMessage) throws CustomerNotFoundException {
        Customer customer = customerMessageMapper.toCustomer(customerMessage);
        updateCustomerInputPort.update(customer, customerMessage.getZipCode());
    }

}
