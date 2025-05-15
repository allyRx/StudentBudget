package org.allyrx.studentbudget.Services;

import lombok.AllArgsConstructor;
import org.allyrx.studentbudget.Entites.Role;
import org.allyrx.studentbudget.Entites.User;
import org.allyrx.studentbudget.Entites.Validation;
import org.allyrx.studentbudget.Enum.RoleEnum;
import org.allyrx.studentbudget.Repository.AuthenticationRepository;
import org.allyrx.studentbudget.Repository.ValidationRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;


@Service
@AllArgsConstructor
public class AuthenticationService {

    private final AuthenticationRepository authenticationRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ValidationRepository validationRepository;
    private validationService validationService;



    public void register(User user){

        //verification que l'email est bien valide
        if(!user.getEmail().contains("@")||!user.getEmail().contains(".")){
            throw new RuntimeException("Invalid email format");
        }

        //verifions si l'email est deja existe
        Optional<User> isUserFound = authenticationRepository.findByEmail(user.getEmail());
        if(isUserFound.isPresent()){throw new RuntimeException("User already exists");}

        //Mettons le role pardefaut en etudiant
        Role role = new Role();
        role.setRoleName(RoleEnum.STUDENT);
        user.setRole(role);
        user.setEnabled(false);

        //cryptage du mot de passe
        String mdpCrypter = passwordEncoder.encode(user.getPassword());
        user.setPassword(mdpCrypter);

        authenticationRepository.save(user);

        //envoyer la validation
        validationService.createValidation(user);

    }


    //Validation du code (activation)
    public void activate(Map<String , String> activation){
        Validation validation = validationService.VerifyValidationCode(activation.get("code"));

        //verify l'expiration
        if(Instant.now().isAfter(validation.getExpiredAt())){
            throw new RuntimeException("activation code expired , try another one");
        }

        User userActived = authenticationRepository.findById(validation.getUser().getId()).orElseThrow(()->new RuntimeException("User not found"));
        //enabled user should true
        userActived.setEnabled(true);
        authenticationRepository.save(userActived);
        //reinitialiser le code
        validation.setCode(null);
        validation.setActivateAt(Instant.now());
        validationRepository.save(validation);
    }
}
