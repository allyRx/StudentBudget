package org.allyrx.studentbudget.Repository;

import org.allyrx.studentbudget.Entites.Validation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ValidationRepository extends JpaRepository<Validation , Long> {
  Optional<Validation> findByCode(String code);
}
