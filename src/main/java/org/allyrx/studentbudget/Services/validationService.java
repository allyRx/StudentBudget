package org.allyrx.studentbudget.Services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.allyrx.studentbudget.Entites.User;
import org.allyrx.studentbudget.Entites.Validation;
import org.allyrx.studentbudget.Repository.ValidationRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;

@Service
@AllArgsConstructor
public class validationService {

    private  ValidationRepository validationRepository;
    private  NotificationService notificationService;

    //creation du validation comme le code , la date d'expiration
    public void createValidation(User user){
        Validation validation = new Validation();
        validation.setUser(user);
        //creation
        Instant createdAt = Instant.now();
        validation.setCreatedAt(createdAt);
        //expiration
        Instant expiresAt = Instant.now().plus(10, ChronoUnit.MINUTES);
        validation.setExpiredAt(expiresAt);

        //code
        Random random = new Random();
        int randomInt = random.nextInt(999999);
        String code = String.format("%06d", randomInt);
        validation.setCode(code);

        validationRepository.save(validation);

        notificationService.sendNotification(validation);
    }

    //pour verifier le code dans la table validation
    public Validation VerifyValidationCode(String code){
        return validationRepository.findByCode(code).orElseThrow(()->new RuntimeException("Invalid validation code"));
    }
}
