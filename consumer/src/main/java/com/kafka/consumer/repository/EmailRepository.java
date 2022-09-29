package com.kafka.consumer.repository;

import com.kafka.consumer.domain.model.EmailModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface EmailRepository extends JpaRepository<EmailModel, UUID> {
}