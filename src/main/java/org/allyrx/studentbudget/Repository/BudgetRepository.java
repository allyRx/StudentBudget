package org.allyrx.studentbudget.Repository;

import org.allyrx.studentbudget.Entites.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget , Long> {
}
