package org.allyrx.studentbudget.Services;


/*
* L'obejectif de cette code est de faire en sorte que tous les roles sont deja existe dans la base des donnes et recreer s'il n'a pas.
* Cela permet de préparer la base de données avec des rôles nécessaires à la gestion des utilisateurs, sans devoir les ajouter manuellement.
* */
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.allyrx.studentbudget.Entites.Role;
import org.allyrx.studentbudget.Enum.RoleEnum;
import org.allyrx.studentbudget.Repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class RoleService {

    //Logger pour afficher des messages dasn la console
    private final Logger logger = Logger.getLogger(RoleService.class.getName());
    private final RoleRepository roleRepository;

    //Methode executer automatiquement apres l'initialisation du bean spring(l'application)
    @PostConstruct
    void  init(){
        Map<RoleEnum , String> roleDescriptionMap = Map.of(
                RoleEnum.ADMIN, "Admin role" ,
                RoleEnum.STUDENT , "etudiant role",
                RoleEnum.PARENT , "parent role"
        );

        //Parcouris chaque role a verifier / creer
        roleDescriptionMap.forEach((roleName , description) ->
                //on cherche le role dans la base des donnes
                roleRepository.findByRoleName(roleName).ifPresentOrElse(
                        //si le role existe , on se notifie
                        role -> logger.info("Role already exists" + role) ,
                        //si non on le cree , et on le sauvegarde au base des donnes
                        ()->{
                            Role roleToCreate = new Role();
                            roleToCreate.setRoleName(roleName);

                            roleRepository.save(roleToCreate);

                            //Message de configuration dans le log
                            logger.info("Role created:"+roleName);
                        }
                        ));

    }


    //Methode publique pour retrouver un role selon son nom
    public Optional<Role> findByRoleName(RoleEnum roleName) {
        return roleRepository.findByRoleName(roleName);
    }
}
