package org.allyrx.studentbudget.Services;

import lombok.AllArgsConstructor;
import org.allyrx.studentbudget.Entites.Validation;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.management.Notification;

@Service
@AllArgsConstructor
public class NotificationService {
    JavaMailSender mailSender;
    public void sendNotification(Validation validation) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("no-reply@allyrx.org");
        message.setTo(validation.getUser().getEmail());
        message.setSubject("Votre code d'activation");

        String texte = String.format(
                "Bonjour %s ,<br /> votre code de confirmation est %s",validation.getUser().getUsername(), validation.getCode()
        );
        message.setText(texte);

        mailSender.send(message);

    }
}
