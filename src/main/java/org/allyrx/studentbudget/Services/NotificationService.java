package org.allyrx.studentbudget.Services;

import lombok.AllArgsConstructor;
import org.allyrx.studentbudget.Entites.Budget;
import org.allyrx.studentbudget.Entites.Validation;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationService {
    JavaMailSender mailSender;

    //notification pour le code d'activation
    public void sendNotificationValidation(Validation validation) {
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

    //NOtification pour le budget
    public void sendBudget(String emailParent , Budget budget) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailParent);
        message.setTo(budget.getUser().getEmail());
        message.setSubject("Votre budget");

        message.setText(String.format("Bonjour %s, <br /> Voici votre budget pour le motif %S et avec le montant %S <br /> Merci beaucoup", budget.getUser().getUsername() , budget.getMotif() , budget.getAmount()));


        mailSender.send(message);

    }
}
