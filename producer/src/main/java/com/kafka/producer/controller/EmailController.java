package com.kafka.producer.controller;

import com.kafka.producer.domain.dto.EmailDTO;
import com.kafka.producer.service.EmailProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController("/v1/email")
@RequiredArgsConstructor
public class EmailController {
    private final EmailProducerService kafkaProducerService;

    @PostMapping("/send")
    public void sendProjectStatusEmail(@RequestBody EmailDTO emailDTO) {
        kafkaProducerService.send(emailDTO);
    }

}