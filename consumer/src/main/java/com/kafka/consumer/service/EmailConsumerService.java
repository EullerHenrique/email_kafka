package com.kafka.consumer.service;
import com.kafka.consumer.domain.dto.EmailDTO;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailConsumerService {

    private static final Logger log = LoggerFactory.getLogger(EmailConsumerService.class);
    private  final EmailSendService emailSendService;
    @KafkaListener(topics = "${topic.name}", groupId = "${spring.kafka.group-id}", containerFactory = "emailKafkaListenerContainerFactory")
    public void listenTopicCar(ConsumerRecord<String, EmailDTO> record){
        log.info("Received Message " + record.partition());
        log.info("Received Message " + record.value());
        log.info(emailSendService.sendEmail(record.value()));
    }


}
