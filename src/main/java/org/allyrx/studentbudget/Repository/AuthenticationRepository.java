package org.allyrx.studentbudget.Repository;

import org.allyrx.studentbudget.Entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthenticationRepository extends JpaRepository<User , Long> {
    Optional<User> findByEmail(String email);
}
