package org.allyrx.studentbudget.Repository;

import org.allyrx.studentbudget.Entites.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BudgetRepository extends JpaRepository<Budget , Long> {
    Optional<Budget> findByMotif(String motif);
}
