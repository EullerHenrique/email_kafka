package com.kafka.producer.service;

import com.kafka.producer.domain.dto.EmailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailProducerService {
    @Value(value = "${topic.name}")
    private String topic;
    private final KafkaTemplate<String, EmailDTO> template;
    public void send(EmailDTO message) {
        template.send(topic, message);
    }
}
