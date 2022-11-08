package com.kafka.producer.service;

import com.kafka.producer.domain.dto.EmailDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailProducerService {

    private static final Logger logger = LoggerFactory.getLogger(EmailProducerService.class);

    @Value(value = "${topic.name}")
    private String topic;
    private final KafkaTemplate<String, EmailDTO> template;
    public void send(EmailDTO message) {
        template.send(topic, message).addCallback(
                success -> {
                    assert success != null;
                    logger.info("Messagem send: " + success.getProducerRecord().value());
                },
                failure -> logger.info("Message failure: " + failure.getMessage())
        );
    }
}
