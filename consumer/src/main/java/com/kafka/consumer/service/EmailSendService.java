package com.kafka.consumer.service;


import com.kafka.consumer.domain.dto.EmailDTO;
import com.kafka.consumer.domain.enums.StatusEmail;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class EmailSendService {

    private final JavaMailSender emailSender;

    public String sendEmail(EmailDTO email) {
        email.setSendDateEmail(LocalDateTime.now());
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(email.getEmailFrom());
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());
            message.setText(email.getText());
            emailSender.send(message);
            email.setStatusEmail(StatusEmail.SENT);
        } catch (MailException e){
            email.setStatusEmail(StatusEmail.ERROR);
            return "Ocorreu um erro ao enviar o email: "+e.getMessage();
        }
        return "Email enviado com sucesso";
    }

}


