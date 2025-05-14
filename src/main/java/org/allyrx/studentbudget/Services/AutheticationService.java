package org.allyrx.studentbudget.Services;

import lombok.AllArgsConstructor;
import org.allyrx.studentbudget.Entites.Role;
import org.allyrx.studentbudget.Entites.User;
import org.allyrx.studentbudget.Enum.RoleEnum;
import org.allyrx.studentbudget.Repository.AuthenticationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class AutheticationService {
    private final AuthenticationRepository authenticationRepository;

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

        authenticationRepository.save(user);
    }
}
