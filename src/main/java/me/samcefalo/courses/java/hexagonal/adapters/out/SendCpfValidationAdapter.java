package me.samcefalo.courses.java.hexagonal.adapters.out;

import me.samcefalo.courses.java.hexagonal.application.ports.out.SendCpfForValidationOutputPort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class SendCpfValidationAdapter implements SendCpfForValidationOutputPort {

    private final String TOPIC = "tp-cpf-validation";
    private final KafkaTemplate<String, String> kafkaTemplate;

    public SendCpfValidationAdapter(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(String cpf) {
        kafkaTemplate.send(TOPIC, cpf);
    }
}
