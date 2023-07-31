package me.samcefalo.courses.java.hexagonal.adapters.out;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.Mockito.*;

class SendCpfValidationAdapterTest {

    private KafkaTemplate<String, String> kafkaTemplate;
    private SendCpfValidationAdapter sendCpfValidationAdapter;

    @BeforeEach
    void setUp() {
        kafkaTemplate = mock(KafkaTemplate.class);
        sendCpfValidationAdapter = new SendCpfValidationAdapter(kafkaTemplate);
    }

    @Test
    public void should_send_cpf_to_kafka() {
        String topic = "tp-cpf-validation";
        String cpf = "12345678900";

        when(kafkaTemplate.send(topic, cpf)).thenReturn(null);

        sendCpfValidationAdapter.send(cpf);

        verify(kafkaTemplate, times(1)).send(topic, cpf);
    }
}